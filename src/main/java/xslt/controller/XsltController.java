package xslt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xslt.entity.Xml;
import xslt.exception.NotFoundException;
import xslt.service.XsltService;

import java.util.List;
import java.util.Optional;

/**
 * @author makhramovich
 */
@RestController
@RequestMapping("xslt")
public class XsltController {
  @Autowired
  private XsltService xsltService;

  @GetMapping("/all")
  public List<Xml> getAll() {
    return xsltService.findAll();
  }

  @GetMapping("source/{id}")
  public String getSource(@PathVariable Long id) {
    return Optional.ofNullable(xsltService.getSource(id)).orElseThrow(NotFoundException::new);
  }

  @GetMapping("transformed/{id}")
  public String getTransformed(@PathVariable Long id) {
    return Optional.ofNullable(xsltService.getTransformed(id)).orElseThrow(NotFoundException::new);
  }
}
