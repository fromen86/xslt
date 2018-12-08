package xslt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author makhramovich
 */
@Entity
@Table(name = "xml")
@Data
public class Xml implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String content;

  @JsonIgnore
  @OneToOne(fetch = FetchType.LAZY, optional = false, mappedBy = "xml")
  private XmlLink xmlLink;
}
