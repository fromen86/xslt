package xslt.service;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xslt.entity.Xml;
import xslt.repository.XmlRepository;

import java.io.File;
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

  public boolean process(File inputXmlFile) {
    try {
      String inputXml = FileUtils.readFileToString(inputXmlFile, "UTF-8");
      String outputXml = xsltTransformService.transform(inputXml);
      save(inputXmlFile.getName(), inputXml, outputXml);
      FileUtils.deleteQuietly(inputXmlFile);
      return true;
    } catch (Exception e) {
      LOGGER.warn("XML file {} transforming error: {}", inputXmlFile.getName(), e);
      return false;
    }
  }

  @Transactional
  void save(String fileName, String inputXml, String outputXml) {
    Xml xml = new Xml();
    xml.setSourceFileName(fileName);
    xml.setSourceXml(inputXml);
    xml.setTransformedXml(outputXml);
    xmlRepository.save(xml);
  }

  public List<Xml> findAll() {
    return xmlRepository.findAll();
  }

  public String getSource(Long id) {
    return xmlRepository.getOne(id).getSourceXml();
  }

  public String getTransformed(Long id) {
    return xmlRepository.getOne(id).getTransformedXml();
  }
}
