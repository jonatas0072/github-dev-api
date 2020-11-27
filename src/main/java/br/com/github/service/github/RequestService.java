package br.com.github.service.github;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

public interface RequestService {

  HttpResponse<JsonNode> findByName(String username);

  HttpResponse<JsonNode> findByLanguage(String language);
}
