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
import xslt.entity.XmlLink;
import xslt.repository.XmlLinkRepository;

import java.io.IOException;

/**
 * @author makhramovich
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XsltApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
public class XmlLinkRepositoryTest extends AbstractTest {
  @Autowired
  private XmlLinkRepository xmlLinkRepository;

  private String inputXmlContent;
  private String outputXmlContent;

  @Before
  public void before() throws IOException {
    inputXmlContent = FileUtils.readFileToString(getResource("/transform/original_order.xml"), "UTF-8");
    outputXmlContent = FileUtils.readFileToString(getResource("/transform/transformed.xml"), "UTF-8");
  }

  @Test
  public void testSave() {
    XmlLink xmlLink = new XmlLink();
    xmlLink.setSourceFileName("original_order.xml");
    xmlLink.setSourceXml(inputXmlContent);
    xmlLink.setTransformedXml(outputXmlContent);
    xmlLink = xmlLinkRepository.save(xmlLink);

    System.out.println(xmlLink.getId());
  }
}
