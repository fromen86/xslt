package xslt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "xml")
@Data
public class Xml {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String sourceFileName;

  @Lob
  @Basic(fetch = FetchType.LAZY)
  @JsonIgnore
  private String sourceXml;

  @Lob
  @Basic(fetch = FetchType.LAZY)
  @JsonIgnore
  private String transformedXml;
}
