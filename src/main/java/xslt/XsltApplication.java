package xslt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import xslt.transform.XslTransformService;

/**
 * @author makhramovich
 */
@SpringBootApplication
public class XsltApplication {
  @Bean
  public XslTransformService xslTransformService(@Value("${xsl.transform.template}") String xslTemplate,
                                                 @Value("${xsl.transform.indent}") Integer indent) {
    return new XslTransformService(xslTemplate, indent);
  }

  public static void main(String[] args) {
    SpringApplication.run(XsltApplication.class, args);
  }
}
