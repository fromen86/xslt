package xlst;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xslt.XsltApplication;

import java.io.File;

/**
 * A parent test.
 *
 * @author makhramovich
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XsltApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
public abstract class AbstractTest {
  protected File getResource(String localPath) {
    return new File(getClass().getResource(localPath).getFile());
  }
}
