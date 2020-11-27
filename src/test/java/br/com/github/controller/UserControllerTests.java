package br.com.github.controller;

import br.com.github.domain.User;
import br.com.github.domain.enums.Role;
import br.com.github.dto.UserDto;
import br.com.github.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(value = "classpath:application-test.properties")
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

  private final String AUTH = "Authorization";

  private final String TOKEN =
      "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb25hdGFzZmVybmFuZG9hcmF1am9tYWNlZG9AZ21haWwuY29tIiwiZXhwIjoxNjA2ODc2NjQyLCJyb2xlIjpbIkFETUlOIl19.ZeFQ9VoEL0hXspaVbQtydma6czsCLDmhoBF7RpS7nwb8tqxoNBZNsuW99DgLT4wiJHT2voxg49_rCQHYX-F6Ow";

  private final String JSON_CONTENT =
      new String(Files.readAllBytes(Paths.get("src/test/resources/user.json")));

  private final String JSON_CONTENT_LOGIN =
      new String(Files.readAllBytes(Paths.get("src/test/resources/user_login.json")));

  @Autowired private MockMvc mockMvc;

  @Autowired private UserService userService;

  public UserControllerTests() throws IOException {}

  @Test
  @Transactional
  void shouldCreateUser() throws Exception {
    this.mockMvc
        .perform(
            post("/api/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON_CONTENT)
                .header(AUTH, TOKEN))
        .andExpect(status().isCreated());
  }

  @Test
  @Transactional
  void shouldLogin() throws Exception {
    shouldCreateUser();
    this.mockMvc
        .perform(
            post("/api/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON_CONTENT_LOGIN))
        .andExpect(status().isOk());
  }

  @Test
  void shoudFindUserByUuid() throws Exception {
    User user =
        userService.create(
            UserDto.newBuilder()
                .birthDate(LocalDate.of(2020, 10, 10))
                .email("jonatas0072@gmail.com.br")
                .role(Role.ADMIN)
                .name("Jonatas")
                .password("1234567890")
                .build());

    this.mockMvc
        .perform(
            get("/api/user/" + user.getUuid())
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTH, TOKEN))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(user.getName()));
  }

  @Test
  @Transactional
  void shouldReturnAllUsers() throws Exception {
    User userOne =
        userService.create(
            UserDto.newBuilder()
                .birthDate(LocalDate.of(2020, 10, 10))
                .email("jonatas0071@gmail.com.br")
                .role(Role.ADMIN)
                .name("Jonatas Fernando")
                .password("1234567890")
                .build());
    User userTwo =
        userService.create(
            UserDto.newBuilder()
                .birthDate(LocalDate.of(2020, 10, 10))
                .email("jonatas0072@gmail.com.br")
                .role(Role.ADMIN)
                .name("Jonatas Macedo")
                .password("1234567890")
                .build());

    this.mockMvc
        .perform(get("/api/user/").contentType(MediaType.APPLICATION_JSON).header(AUTH, TOKEN))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content.[0].name").value(userOne.getName()))
        .andExpect(jsonPath("$.content.[1].name").value(userTwo.getName()));
  }

  @Test
  @Transactional
  void shouldDeleteUser() throws Exception {
    User user =
            userService.create(
                    UserDto.newBuilder()
                            .birthDate(LocalDate.of(2020, 10, 10))
                            .email("jonatas0071@gmail.com.br")
                            .role(Role.ADMIN)
                            .name("Jonatas Fernando")
                            .password("1234567890")
                            .build());

    mockMvc
            .perform(
                    delete("/api/user/delete")
                            .header(AUTH,TOKEN)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"email\": \"jonatas0071@gmail.com.br\"\n" + "}"))
            .andExpect(status().isNoContent());

  }

  @Test
  @Transactional
  void shouldUpdateUser() throws Exception {
    User user =
            userService.create(
                    UserDto.newBuilder()
                            .birthDate(LocalDate.of(2020, 10, 10))
                            .email("jonatas0071@gmail.com.br")
                            .role(Role.ADMIN)
                            .name("Jonatas Fernando")
                            .password("1234567890")
                            .build());

    mockMvc
            .perform(
                    put("/api/user/update")
                            .header(AUTH,TOKEN)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(
                                    "{\"email\": \"jonatas0071@gmail.com.br\",\n"
                                            + " \"name\": \"Jonatas Macedo\"\n"
                                            + "}"))
            .andExpect(status().isNoContent());

  }
}
