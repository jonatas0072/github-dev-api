package br.com.github.dto;

import br.com.github.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "newBuilder")
@Getter
@Setter
public class LoginDto {

  @NotNull(message = "Email cannot be null")
  @Email
  private String email;

  @NotNull(message = "Password cannot be null")
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;
}
