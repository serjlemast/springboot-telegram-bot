package com.malexj.model;

import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

@Data
public class ChatDto {

  private Long id;
  private String title;
  private ChatType type;
  private UserDto user;

  public ChatDto(Chat chat) {
    this.id = chat.getId();
    this.title = chat.getTitle();
    this.type = ChatType.findType(chat.getType());
  }

  public ChatDto(Chat chat, User user) {
    this(chat);
    this.user = new UserDto(user);
  }
}
