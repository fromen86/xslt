package xlst.transform;

import org.apache.commons.io.FileUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xlst.XsltTestConfiguration;
import xslt.service.XsltTransformService;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

/**
 * @author makhramovich
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XsltTestConfiguration.class)
@TestPropertySource(locations = "classpath:test.properties")
public class XsltTransformServiceTest {
  @Autowired
  private XsltTransformService transformService;

  @Test
  public void testTransform() throws IOException, TransformerException {
    String inputXml = FileUtils.readFileToString(new File(getPath("/transform/original_order.xml")), "UTF-8");
    String outputXml = transformService.transform(inputXml);
    String expectedOutputXml = FileUtils.readFileToString(new File(getPath("/transform/converted.xml")), "UTF-8");
    assertEquals(expectedOutputXml, outputXml);
  }

  private String getPath(String localPath) {
    return getClass().getResource(localPath).getFile();
  }
}
