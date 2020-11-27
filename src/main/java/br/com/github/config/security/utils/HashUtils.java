package br.com.github.config.security.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtils {
  public static String getSecureHashString(String text) {
    String hash = DigestUtils.sha256Hex(text);
    return hash;
  }
}
