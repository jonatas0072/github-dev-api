package br.com.github.config.security;

import br.com.github.config.security.utils.HashUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordEncoder implements PasswordEncoder {
  @Override
  public String encode(CharSequence charSequence) {
    return HashUtils.getSecureHashString(charSequence.toString());
  }

  @Override
  public boolean matches(CharSequence charSequence, String s) {
    return true;
  }
}
