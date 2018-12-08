package xslt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xslt.entity.Xml;

/**
 * @author makhramovich
 */
public interface XmlRepository extends JpaRepository<Xml, Long> {
  @Query("SELECT x.source FROM Xml x where x.id=?1")
  String getSource(Long id);

  @Query("SELECT x.transformed FROM Xml x where x.id=?1")
  String getTransformed(Long id);
}
