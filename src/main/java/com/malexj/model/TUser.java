package com.malexj.model;

import java.util.Objects;
import org.telegram.telegrambots.meta.api.objects.User;

public record TUser(Long userId, String firstName, String lastName, String username) {

  public TUser(User user) {
    this(
        Objects.requireNonNull(user.getId()),
        user.getFirstName(),
        user.getLastName(),
        user.getUserName());
  }
}
