package br.com.github.config.security;

import br.com.github.handler.exception.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AuthorizationFilterJwt extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      FilterChain filterChain)
      throws ServletException, IOException {

    String jwt = httpServletRequest.getHeader("Authorization");

    if (jwt == null || !jwt.startsWith(SecurityConstants.JWT_PROVIDER)) {
      ApiError apiException =
          new ApiError("Unauthorized access", HttpStatus.UNAUTHORIZED.value(), LocalDate.now());
      PrintWriter writer = httpServletResponse.getWriter();

      ObjectMapper mapper = new ObjectMapper();
      mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
      String apiErrorMessage = mapper.writeValueAsString(writer);

      writer.write(apiErrorMessage);

      httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
      httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

      return;
    }
    jwt = jwt.replace(SecurityConstants.JWT_PROVIDER, "");

    try {
      Claims claims = new ManagerJwt().parseToken(jwt);
      String email = claims.getSubject();
      List<String> roles = (List<String>) claims.get(SecurityConstants.JWT_ROLE_KEY);

      List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
      roles.forEach(
          role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
          });

      Authentication authentication =
          new UsernamePasswordAuthenticationToken(email, null, grantedAuthorities);

      SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (Exception e) {
      ApiError apiException =
          new ApiError("Unauthorized access", HttpStatus.UNAUTHORIZED.value(), LocalDate.now());
      PrintWriter writer = httpServletResponse.getWriter();

      ObjectMapper mapper = new ObjectMapper();
      String apiErrorMessage = mapper.writeValueAsString(writer);

      writer.write(apiErrorMessage);

      httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
      httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

      return;
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
