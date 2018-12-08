package xslt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xslt.entity.XmlLink;

/**
 * @author makhramovich
 */
public interface XmlLinkRepository extends JpaRepository<XmlLink, Long> {
}
