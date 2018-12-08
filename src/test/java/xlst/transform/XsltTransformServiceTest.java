package xlst.transform;

import org.apache.commons.io.FileUtils;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xslt.XsltApplication;
import xslt.transform.XsltTransformService;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

/**
 * @author makhramovich
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XsltApplication.class)
@TestPropertySource(locations="classpath:test.properties")
public class XsltTransformServiceTest {
  @Autowired
  private XsltTransformService transformService;

  @Test
  public void testTransform() throws IOException, TransformerException {
    File outFile = null;
    try {
      outFile = File.createTempFile("xsl-transform-out", ".xml");
      transformService.transform(new File(getPath("/transform/original_order.xml")), outFile);
      assertTrue(FileUtils.contentEquals(new File(getPath("/transform/converted.xml")), outFile));
    } finally {
      FileUtils.deleteQuietly(outFile);
    }
  }

  private String getPath(String localPath) {
    return getClass().getResource(localPath).getFile();
  }
}
