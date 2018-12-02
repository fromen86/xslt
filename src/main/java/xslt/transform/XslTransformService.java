package xslt.transform;

import org.springframework.stereotype.Component;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * This class transforms {@code XML -> XML} with {@code XSL} template.
 *
 * @author makhramovich
 */
@Component
public class XslTransformService {
  private final Templates templates;
  private final Integer indent;

  public XslTransformService(String xslTemplate, Integer indent) {
    try {
      templates = TransformerFactory.newInstance().newTemplates(new StreamSource(xslTemplate));
    } catch (TransformerConfigurationException e) {
      throw new IllegalArgumentException("Wrong xsl", e);
    }
    if (indent == null || indent < 0) {
      throw new IllegalArgumentException("Wrong indent value " + indent);
    }
    this.indent = indent;
  }

  public void transform(File inputXml, File outputXml) throws TransformerException {
    createTransformer().transform(new StreamSource(inputXml), new StreamResult(outputXml));
  }

  private Transformer createTransformer() throws TransformerConfigurationException {
    // TODO UTF-8, last empty line
    Transformer transformer = templates.newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", String.valueOf(indent));
    return transformer;
  }
}
