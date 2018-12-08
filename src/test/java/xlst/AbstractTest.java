package xlst;

import java.io.File;

/**
 * @author makhramovich
 */
public abstract class AbstractTest {
  protected File getResource(String localPath) {
    return new File(getClass().getResource(localPath).getFile());
  }
}
