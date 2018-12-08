package xslt.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author makhramovich
 */
@Entity
@Table(name = "xml_link")
@Data
public class XmlLink {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String sourceFileName;

  @Lob
  @Basic(fetch = FetchType.LAZY)
  private String sourceXml;

  @Lob
  @Basic(fetch = FetchType.LAZY)
  private String transformedXml;
}
