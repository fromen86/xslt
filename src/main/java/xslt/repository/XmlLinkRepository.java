package xslt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xslt.entity.Xml;

/**
 * @author makhramovich
 */
public interface XmlLinkRepository extends JpaRepository<Xml, Long> {
}
