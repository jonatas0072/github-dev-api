package br.com.github.service.user;

import br.com.github.domain.User;
import br.com.github.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {
  User create(UserDto dto);

  User update(UserDto dto);

  void delete(UserDto dto);

  Page<UserDto> findAll(Pageable pageable);

  UserDto findByEmail(String email);

  UserDto findById(UUID uuid);
}
