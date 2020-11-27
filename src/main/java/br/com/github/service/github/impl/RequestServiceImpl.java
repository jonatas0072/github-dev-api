package br.com.github.service.github.impl;

import br.com.github.service.github.RequestService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {

  @Value("${github.uri.base.developer}")
  private String endpoint;

  @Override
  public HttpResponse<JsonNode> findByName(String username) {
    return Unirest.get(endpoint).routeParam("param", "users").routeParam("q", username).asJson();
  }

  @Override
  public HttpResponse<JsonNode> findByLanguage(String language) {
    return Unirest.get(endpoint)
        .routeParam("param", "repositories")
        .routeParam("q", "topic:" + language)
        .asJson();
  }
}
