package xlst.service;

import org.apache.commons.io.FileUtils;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.xmlunit.diff.Diff;
import xlst.AbstractTest;
import xslt.service.XsltTransformService;

import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;

/**
 * @author makhramovich
 */
public class XsltTransformServiceTest extends AbstractTest {
  @Autowired
  private XsltTransformService transformService;

  @Test
  public void testTransform() throws IOException, TransformerException {
    String inputXml = FileUtils.readFileToString(getResource("/transform/original_order.xml"), "UTF-8");
    String outputXml = transformService.transform(inputXml);
    String expectedOutputXml = FileUtils.readFileToString(getResource("/transform/transformed.xml"), "UTF-8");
    assertFalse(new Diff(
            new StreamSource(new StringReader(expectedOutputXml)),
            new StreamSource(new StringReader(outputXml)),
            Collections.emptyList()
    ).hasDifferences());
  }
}
