package xslt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import xslt.transform.XsltTransformService;

/**
 * @author makhramovich
 */
@SpringBootApplication
public class XsltApplication {
  @Bean
  public XsltTransformService xslTransformService(@Value("${xsl.transform.template}") String xslTemplate,
                                                  @Value("${xsl.transform.indent}") Integer indent) {
    return new XsltTransformService(xslTemplate, indent);
  }

  public static void main(String[] args) {
    SpringApplication.run(XsltApplication.class, args);
  }
}
