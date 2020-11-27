package br.com.github.config.security;

import br.com.github.dto.UserLoginDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class ManagerJwt {

//  public static void main(String[] args) {
//    List<String> role = new ArrayList<>();
//    role.add("ADMIN");
//
//    UserLoginDto usuarioLoginDto =  createToken("jonatasfernandoaraujomacedo@gmail.com",role);
//    System.out.println(usuarioLoginDto.getToken());
//  }

  public UserLoginDto createToken(String email, List<String> roles) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_MONTH, SecurityConstants.JWT_EXPIRATION_DAYS);

    String jwtToken =
        Jwts.builder()
            .setSubject(email)
            .setExpiration(calendar.getTime())
            .claim(SecurityConstants.JWT_ROLE_KEY, roles)
            .signWith(SignatureAlgorithm.HS512, SecurityConstants.API_KEY.getBytes())
            .compact();

    Long expiredIn = calendar.getTimeInMillis();

    return new UserLoginDto(jwtToken, expiredIn, SecurityConstants.JWT_PROVIDER);
  }

  public Claims parseToken(String jwtToken) throws JwtException {
    return Jwts.parser()
        .setSigningKey(SecurityConstants.API_KEY.getBytes())
        .parseClaimsJws(jwtToken)
        .getBody();
  }
}
