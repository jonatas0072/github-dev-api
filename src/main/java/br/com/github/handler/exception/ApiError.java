package br.com.github.handler.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor()
public class ApiError implements Serializable {

  private static final long serialVersionUID = -6689800709152064117L;

  private String message;
  private int status;
  private LocalDate date;
}
