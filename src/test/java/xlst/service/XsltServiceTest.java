package xlst.service;

import org.apache.commons.io.FileUtils;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xlst.AbstractTest;
import xslt.entity.Xml;
import xslt.service.XsltService;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * XML service test.
 * @author makhramovich
 */
public class XsltServiceTest extends AbstractTest {
  @Autowired
  private XsltService xsltService;

  @Test
  public void testProcess() {
    process();
  }

  @Test
  public void testGetAfterProcessing() {
    process();
    List<Xml> xmlList = xsltService.findAll();
    assertFalse(xmlList.isEmpty());

    assertTrue(xmlList.get(0).getSourceFileName().startsWith("test-input"));
    try {
      xsltService.getTransformed(xmlList.get(0).getId());
    } catch (Exception e) {
      fail("Get transformed by id error");
    }
  }

  private void process() {
    File copyFile = null;
    try {
      copyFile = File.createTempFile("test-input", ".xml");
      FileUtils.copyFile(getResource("/transform/original_order.xml"), copyFile);
      xsltService.process(copyFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      FileUtils.deleteQuietly(copyFile);
    }
  }
}
