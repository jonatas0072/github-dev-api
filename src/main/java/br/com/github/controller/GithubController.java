package br.com.github.controller;

import br.com.github.service.github.RequestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@Api(value = "API que consome o github")
@RequestMapping("api/github")
@AllArgsConstructor
public class GithubController {

  private RequestService serivceService;

  @GetMapping("find/{name}")
  @ApiOperation(value = "Busca dados pelo nome")
  public String findByName(@PathVariable(name = "name") String name) {
    return serivceService.findByName(name).getBody().toString();
  }

  @GetMapping("find-language/{language}")
  @ApiOperation(value = "Busca dados das linguagens mais usadas")
  public String findRepositoriesByTopic(@PathVariable(name = "language") String language) {
    return serivceService.findByLanguage(language).getBody().toString();
  }
}
