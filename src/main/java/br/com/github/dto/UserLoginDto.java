package br.com.github.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class UserLoginDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private String token;
  private Long expiredIn;
  private String tokenProvider;
}
