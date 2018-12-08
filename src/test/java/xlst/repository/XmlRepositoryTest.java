package xlst.repository;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xlst.AbstractTest;
import xslt.entity.Xml;
import xslt.repository.XmlRepository;

import java.io.IOException;
import java.util.Date;

/**
 * XML repository test.
 *
 * @author makhramovich
 */
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
