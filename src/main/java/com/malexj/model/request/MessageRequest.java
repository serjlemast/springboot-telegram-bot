package com.malexj.model.request;

import java.util.Objects;

public record MessageRequest(Long catId, String message) {

  public MessageRequest {
    Objects.requireNonNull(catId);
    Objects.requireNonNull(message);
  }
}
