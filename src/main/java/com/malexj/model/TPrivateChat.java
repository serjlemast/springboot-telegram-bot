package com.malexj.model;

import java.util.Objects;
import org.telegram.telegrambots.meta.api.objects.Message;

public record TPrivateChat(Long chatId, TUser user) {
  public TPrivateChat(Message message) {
    this(message.getChatId(), new TUser(message.getFrom()));
  }

  public TPrivateChat {
    Objects.requireNonNull(chatId);
    Objects.requireNonNull(user);
  }
}
