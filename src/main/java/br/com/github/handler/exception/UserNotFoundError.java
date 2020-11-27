package br.com.github.handler.exception;

public class UserNotFoundError extends RuntimeException {

  public UserNotFoundError(String param, String value) {
    super("User with " + param + " [ " + value + "] not found.");
  }
}
