package xslt.service;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * @author makhramovich
 */
@Service
public class XmlSourcesWatchService {
  private static final Logger LOGGER = LoggerFactory.getLogger(XmlSourcesWatchService.class);

  @Autowired
  private XsltService xsltService;
  @Value("${xml.directory}")
  private String xmlSourcesDirectoryPath;

  @PostConstruct
  public void init() {
    try {
      Executors.newSingleThreadExecutor(r -> {
        Thread t = Executors.defaultThreadFactory().newThread(r);
        t.setDaemon(true);
        return t;
      }).execute(new Worker(xmlSourcesDirectoryPath));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private class Worker implements Runnable {
    private final WatchService watchService;
    private final File xmlSourcesDirectory;
    private final ExecutorService executorService;

    Worker(String xmlSourcesDirectoryPath) throws Exception {
      this.watchService = FileSystems.getDefault().newWatchService();
      this.xmlSourcesDirectory = new File(xmlSourcesDirectoryPath);
      if (!xmlSourcesDirectory.isDirectory()) {
        throw new RuntimeException(xmlSourcesDirectoryPath + " is not a directory");
      }
      xmlSourcesDirectory.toPath().register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
      executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Override
    public void run() {
      File[] files = xmlSourcesDirectory.listFiles();
      if (files != null) {
        Stream.of(files).forEach(file -> executorService.execute(() -> xsltService.process(file)));
      }

      WatchKey key;
      try {
        while ((key = watchService.take()) != null) {
          for (WatchEvent<?> event : key.pollEvents()) {
            executorService.execute(() -> xsltService.process(FileUtils.getFile(xmlSourcesDirectory, ((WatchEvent<Path>) event).context().toString())));
          }
          key.reset();
        }
      } catch (InterruptedException e) {
        LOGGER.info("XmlSourcesWatch in interrupted");
      }
    }
  }
}
