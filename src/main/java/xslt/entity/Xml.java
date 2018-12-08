package xslt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author makhramovich
 */
@Entity
@Table(name = "xml")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Xml {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  private String sourceFileName;
  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private Date date;

  @NotNull
  @Lob
  @Basic(fetch = FetchType.LAZY)
  @JsonIgnore
  private String source;

  @NotNull
  @Lob
  @Basic(fetch = FetchType.LAZY)
  @JsonIgnore
  private String transformed;
}
