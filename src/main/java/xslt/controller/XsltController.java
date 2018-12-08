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
 * A controller for XML reading.
 *
 * @author makhramovich
 */
@RestController
@RequestMapping("xslt")
public class XsltController {
  @Autowired
  private XsltService xsltService;

  /**
   * Gets info about all processed XMLs.
   *
   * @return the all
   */
  @GetMapping("/all")
  public List<Xml> getAll() {
    return xsltService.findAll();
  }

  /**
   * Gets source XML by id.
   *
   * @param id the id
   * @return the source XML
   */
  @GetMapping("source/{id}")
  public String getSource(@PathVariable Long id) {
    return Optional.ofNullable(xsltService.getSource(id)).orElseThrow(NotFoundException::new);
  }

  /**
   * Gets transformed XML by id.
   *
   * @param id the id
   * @return the transformed XML
   */
  @GetMapping("transformed/{id}")
  public String getTransformed(@PathVariable Long id) {
    return Optional.ofNullable(xsltService.getTransformed(id)).orElseThrow(NotFoundException::new);
  }
}
