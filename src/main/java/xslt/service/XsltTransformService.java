package xslt.service;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;

/**
 * This class transforms {@code XML -> XML} with {@code XSL} template.
 *
 * @author makhramovich
 */
public class XsltTransformService {
  private final Templates templates;
  private final Integer indent;

  public XsltTransformService(String xslTemplate, Integer indent) {
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

  public String transform(String inputXml) throws TransformerException, IOException {
    try (Reader inputReader = new StringReader(inputXml);
         ByteArrayOutputStream os = new ByteArrayOutputStream()) {
      createTransformer().transform(new StreamSource(new BufferedReader(inputReader)), new StreamResult(new BufferedOutputStream(os)));
      return new String(os.toByteArray(), Charset.forName("UTF-8")).trim();
    }
  }

  private Transformer createTransformer() throws TransformerConfigurationException {
    Transformer transformer = templates.newTransformer();
    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", String.valueOf(indent));
    return transformer;
  }
}
