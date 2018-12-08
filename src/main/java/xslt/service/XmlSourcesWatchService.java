package xslt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.Executors;

/**
 * @author makhramovich
 */
public class XmlSourcesWatchService {
  private static final Logger LOGGER = LoggerFactory.getLogger(XmlSourcesWatchService.class);

  @Autowired
  private XsltService xsltService;

  public XmlSourcesWatchService(String xmlSourcesDirectoryPath) throws Exception {
    Executors.newSingleThreadExecutor(r -> {
      Thread t = Executors.defaultThreadFactory().newThread(r);
      t.setDaemon(true);
      return t;
    }).execute(new Worker(xmlSourcesDirectoryPath));
  }

  private class Worker implements Runnable {
    private final WatchService watchService;
    private final File xmlSourcesDirectory;

    Worker(String xmlSourcesDirectoryPath) throws Exception {
      this.xmlSourcesDirectory = new File(xmlSourcesDirectoryPath);
      if (!xmlSourcesDirectory.isDirectory()) {
        throw new RuntimeException(xmlSourcesDirectoryPath + " is not a directory");
      }
      this.watchService = FileSystems.getDefault().newWatchService();
      xmlSourcesDirectory.toPath().register(
              watchService,
              StandardWatchEventKinds.ENTRY_CREATE,
              StandardWatchEventKinds.ENTRY_MODIFY
      );
    }

    @Override
    public void run() {
      WatchKey key;
      try {
        while ((key = watchService.take()) != null) {
          for (WatchEvent<?> event : key.pollEvents()) {
            xsltService.process(((WatchEvent<Path>) event).context().toFile());
          }
          key.reset();
        }
      } catch (InterruptedException e) {
        LOGGER.info("XmlSourcesWatch in interrupted");
      }
    }
  }
}
