package br.com.github.handler.exception;

public class UserBadRequestError extends RuntimeException {
  public UserBadRequestError(String email) {
    super("User with email [" + email + "] already exists.");
  }
}
