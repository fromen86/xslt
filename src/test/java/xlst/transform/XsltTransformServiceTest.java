package xlst.transform;

import org.apache.commons.io.FileUtils;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xmlunit.diff.Diff;
import xlst.XsltTestConfiguration;
import xslt.service.XsltTransformService;

import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;

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
    assertFalse(new Diff(
            new StreamSource(new StringReader(expectedOutputXml)),
            new StreamSource(new StringReader(outputXml)),
            Collections.emptyList()
    ).hasDifferences());
  }

  private String getPath(String localPath) {
    return getClass().getResource(localPath).getFile();
  }
}
