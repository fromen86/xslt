package xslt.service;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xslt.entity.Xml;
import xslt.repository.XmlRepository;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author makhramovich
 */
@Service
public class XsltService {
  private static final Logger LOGGER = LoggerFactory.getLogger(XsltService.class);
  @Autowired
  private XmlRepository xmlRepository;
  @Autowired
  private XsltTransformService xsltTransformService;

  public void process(File inputXmlFile) {
    try {
      String inputXml = FileUtils.readFileToString(inputXmlFile, "UTF-8");
      String outputXml = xsltTransformService.transform(inputXml);
      xmlRepository.save(new Xml(null, inputXmlFile.getName(), new Date(), inputXml, outputXml));
      FileUtils.deleteQuietly(inputXmlFile);
    } catch (Exception e) {
      LOGGER.warn("XML file {} transforming error: {}", inputXmlFile.getName(), e);
    }
  }

  public List<Xml> findAll() {
    return xmlRepository.findAll();
  }

  public String getSource(Long id) {
    return xmlRepository.getSource(id);
  }

  public String getTransformed(Long id) {
    return xmlRepository.getTransformed(id);
  }
}
