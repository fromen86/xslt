package xlst.repository;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xlst.AbstractTest;
import xslt.XsltApplication;
import xslt.entity.Xml;
import xslt.repository.XmlRepository;

import java.io.IOException;

/**
 * @author makhramovich
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XsltApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
public class XmlRepositoryTest extends AbstractTest {
  @Autowired
  private XmlRepository xmlRepository;

  private String inputXmlContent;
  private String outputXmlContent;

  @Before
  public void before() throws IOException {
    inputXmlContent = FileUtils.readFileToString(getResource("/transform/original_order.xml"), "UTF-8");
    outputXmlContent = FileUtils.readFileToString(getResource("/transform/transformed.xml"), "UTF-8");
  }

  @Test
  public void testSave() {
    Xml xml = new Xml();
    xml.setSourceFileName("original_order.xml");
    xml.setSourceXml(inputXmlContent);
    xml.setTransformedXml(outputXmlContent);
    xmlRepository.save(xml);
  }
}
