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
import java.util.Date;

/**
 * XML repository test.
 *
 * @author makhramovich
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XsltApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
public class XmlRepositoryTest extends AbstractTest {
  @Autowired
  private XmlRepository xmlRepository;

  private String inputXml;
  private String outputXml;

  @Before
  public void before() throws IOException {
    inputXml = FileUtils.readFileToString(getResource("/transform/original_order.xml"), "UTF-8");
    outputXml = FileUtils.readFileToString(getResource("/transform/transformed.xml"), "UTF-8");
  }

  @Test
  public void testSave() {
    xmlRepository.save(new Xml(null, "original_order.xml", new Date(), inputXml, outputXml));
  }
}
