package xslt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * @author makhramovich
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Xml {
  private String content;
}
