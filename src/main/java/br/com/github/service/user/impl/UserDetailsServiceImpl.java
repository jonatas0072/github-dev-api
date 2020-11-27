package br.com.github.service.user.impl;

import br.com.github.config.security.ManagerJwt;
import br.com.github.dto.LoginDto;
import br.com.github.dto.UserDto;
import br.com.github.dto.UserLoginDto;
import br.com.github.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired private UserService userService;

  @Autowired private AuthenticationManager authenticationManager;

  @Autowired private ManagerJwt jwtManager;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserDto userDTO = userService.findByEmail(email);
    List<GrantedAuthority> authorities =
        Arrays.asList(new SimpleGrantedAuthority("ROLE " + userDTO.getRole().name()));
    org.springframework.security.core.userdetails.User userDetails =
        new org.springframework.security.core.userdetails.User(
            userDTO.getEmail(), userDTO.getPassword(), authorities);
    return userDetails;
  }

  public UserLoginDto loginUser(LoginDto loginDTO) {
    UsernamePasswordAuthenticationToken token =
        new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
    Authentication authenticate = authenticationManager.authenticate(token);
    SecurityContextHolder.getContext().setAuthentication(authenticate);

    org.springframework.security.core.userdetails.User userDetails =
        (org.springframework.security.core.userdetails.User) authenticate.getPrincipal();
    List<String> roles =
        userDetails.getAuthorities().stream()
            .map(authority -> authority.getAuthority())
            .collect(Collectors.toList());
    return jwtManager.createToken(userDetails.getUsername(), roles);
  }
}
