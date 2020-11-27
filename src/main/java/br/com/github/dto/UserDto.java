package br.com.github.dto;

import br.com.github.domain.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor()
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor()
@Getter
@Setter
public class UserDto {

  @NotNull(message = "Name cannot be null.")
  private String name;

  @JsonProperty("birth_date")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthDate;

  @Email
  @NotNull(message = "Email cannot be null.")
  private String email;

  @NotNull(message = "Password cannot be null.")
  private String password;

  @NotNull(message = "Role cannot be null.")
  private Role role;
}
