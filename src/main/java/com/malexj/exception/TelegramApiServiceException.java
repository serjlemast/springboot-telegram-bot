package com.malexj.exception;

public class TelegramApiServiceException extends RuntimeException {
  public TelegramApiServiceException() {
    super();
  }

  public TelegramApiServiceException(String message) {
    super(message);
  }

  public TelegramApiServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
