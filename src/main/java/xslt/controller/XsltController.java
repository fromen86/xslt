package xslt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xslt.entity.XmlLink;
import xslt.service.XsltService;

import java.util.List;

/**
 * @author makhramovich
 */
@RestController
@RequestMapping("xslt")
public class XsltController {
  @Autowired
  private XsltService xsltService;

  @GetMapping("/all")
  public List<XmlLink> getAll() {
    return xsltService.findAll();
  }

  @GetMapping("source/{id}")
  public String getSource(@PathVariable Long id) {
    return xsltService.getSource(id);
  }

  @GetMapping("transformed/{id}")
  public String getTransformed(@PathVariable Long id) {
    return xsltService.getTransformed(id);
  }
}
