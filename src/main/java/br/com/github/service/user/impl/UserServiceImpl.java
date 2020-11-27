package br.com.github.service.user.impl;

import br.com.github.domain.User;
import br.com.github.dto.UserDto;
import br.com.github.handler.exception.UserBadRequestError;
import br.com.github.handler.exception.UserNotFoundError;
import br.com.github.repository.UserRepository;
import br.com.github.service.user.UserService;
import br.com.github.service.user.convert.UserConvert;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  @Override
  public User create(UserDto userDto) {
    if (userRepository.findByEmail(userDto.getEmail()).isPresent())
      throw new UserBadRequestError(userDto.getEmail());
    User userToCreate = UserConvert.convertDtoForEntity(userDto);
    return userRepository.save(userToCreate);
  }

  @Override
  public User update(UserDto userDto) {
    User user =
        userRepository
            .findByEmail(userDto.getEmail())
            .orElseThrow(() -> new UserNotFoundError("email", userDto.getEmail()));

    if (userDto.getName().isEmpty()) {
      throw new UserBadRequestError(userDto.getEmail());
    }
    user.setName(userDto.getName());
    return userRepository.save(user);
  }

  @Override
  public void delete(UserDto userDto) {
    User user =
        userRepository
            .findByEmail(userDto.getEmail())
            .orElseThrow(() -> new UserNotFoundError("email", userDto.getEmail()));
    userRepository.delete(user);
  }

  @Override
  public Page<UserDto> findAll(Pageable pageable) {
    return userRepository.findAll(pageable).map(user -> UserConvert.convertEntityForDto(user));
  }

  @Override
  public UserDto findByEmail(String email) {
    User user =
        userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundError("email", email));
    return UserConvert.convertEntityForDto(user);
  }

  @Override
  public UserDto findById(UUID uuid) {
    User user =
        userRepository.findById(uuid).orElseThrow(() -> new UserNotFoundError("uuid", uuid.toString()));
    return UserConvert.convertEntityForDto(user);
  }
}
