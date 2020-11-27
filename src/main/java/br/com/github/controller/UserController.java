package br.com.github.controller;

import br.com.github.domain.User;
import br.com.github.dto.LoginDto;
import br.com.github.dto.UserDto;
import br.com.github.dto.UserLoginDto;
import br.com.github.service.user.UserService;
import br.com.github.service.user.impl.UserDetailsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@Api(value = "API Usuários")
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {

  private UserService userService;

  private UserDetailsServiceImpl userDetailsService;

  @PostMapping("/create")
  @ApiOperation(value = "Cadastrar um usuário")
  ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDTO) {
    User user = userService.create(userDTO);
    return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(user.getUuid())
                .toUri())
        .build();
  }

  @PostMapping("/login")
  @ApiOperation(value = "Faz login de usuário")
  ResponseEntity<UserLoginDto> login(@RequestBody LoginDto loginDTO) {
    return ResponseEntity.ok(userDetailsService.loginUser(loginDTO));
  }

  @GetMapping("/{uuid}")
  @ApiOperation(value = "Busca usuário pelo id")
  ResponseEntity<UserDto> findById(@PathVariable(name = "uuid") UUID uuid) {
    return ResponseEntity.ok(userService.findById(uuid));
  }

  @GetMapping("")
  @ApiOperation(value = "Lista todos os usuários")
  ResponseEntity<Page<UserDto>> findAll(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "10") int size) {
    return ResponseEntity.ok(userService.findAll(PageRequest.of(page, size)));
  }

  @DeleteMapping("delete")
  @ApiOperation(value = "Exclui um usuário")
  ResponseEntity<Void> deleteUser(@RequestBody UserDto userDTO) {
    userService.delete(userDTO);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/update")
  @ApiOperation(value = "Atualiza um usuario")
  ResponseEntity<Void> updateUser(@RequestBody UserDto userDTO) {
    userService.update(userDTO);
    return ResponseEntity.noContent().build();
  }
}
