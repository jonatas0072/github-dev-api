package br.com.github.service.user.convert;

import br.com.github.domain.User;
import br.com.github.dto.UserDto;

public final class UserConvert {

  public static UserDto convertEntityForDto(User user) {
    return UserDto.newBuilder()
        .birthDate(user.getBirthDate())
        .name(user.getName())
        .email(user.getEmail())
        .password("*******")
        .role(user.getRole())
        .build();
  }

  public static User convertDtoForEntity(UserDto userDTO) {
    return User.newBuilder()
        .name(userDTO.getName())
        .birthDate(userDTO.getBirthDate())
        .email(userDTO.getEmail())
        .password(userDTO.getPassword())
        .role(userDTO.getRole())
        .build();
  }
}
