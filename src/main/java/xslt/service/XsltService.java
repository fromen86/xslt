package xslt.service;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xslt.entity.Xml;
import xslt.entity.XmlLink;
import xslt.repository.XmlLinkRepository;
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
  private XmlLinkRepository xmlLinkRepository;
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
  void save(String fileName, String inputContent, String outputContent) {
    Xml inputXml = new Xml();
    inputXml.setContent(inputContent);
    inputXml = xmlRepository.save(inputXml);

    Xml outputXml = new Xml();
    outputXml.setContent(outputContent);
    outputXml = xmlRepository.save(outputXml);

    XmlLink xmlLink = new XmlLink();
    xmlLink.setSourceFileName(fileName);
    xmlLink.setSourceXml(inputXml);
    xmlLink.setTransformedXml(outputXml);
    xmlLinkRepository.save(xmlLink);
  }

  public List<XmlLink> findAll() {
    return xmlLinkRepository.findAll();
  }

  public String getSource(Long id) {
    return xmlLinkRepository.getOne(id).getSourceXml().getContent();
  }

  public String getTransformed(Long id) {
    return xmlLinkRepository.getOne(id).getTransformedXml().getContent();
  }
}