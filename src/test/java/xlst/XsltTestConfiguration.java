package xlst;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import xslt.service.XsltTransformService;

/**
 * @author makhramovich
 */
@TestConfiguration
public class XsltTestConfiguration {
  @Bean
  public XsltTransformService xslTransformService(@Value("${xsl.transform.template}") String xslTemplate,
                                                  @Value("${xsl.transform.indent}") Integer indent) {
    return new XsltTransformService(xslTemplate, indent);
  }
}
