package xslt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xslt.entity.Xml;

/**
 * @author makhramovich
 */
public interface XmlRepository extends JpaRepository<Xml, Long> {
}
