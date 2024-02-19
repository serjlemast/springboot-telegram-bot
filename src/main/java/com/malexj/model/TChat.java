package com.malexj.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

@Data
@Slf4j
public class TChat {

  private Long id;
  private String title;
  private ChatType type;
  private TUser user;

  public TChat(Chat chat) {
    this.id = chat.getId();
    this.title = chat.getTitle();
    this.type = ChatType.findType(chat.getType());
    log.info("Init new '{}' chat with 'id' - {} and title - '{}'", this.title, this.id, this.title);
  }

  public TChat(Chat chat, User user) {
    this(chat);
    this.user = new TUser(user);
  }

  /**
   * Link: <a href="https://core.telegram.org/bots/api#chat">chat info</a> <br>
   * Type of chat, can be either “private”, “group”, “supergroup” or “channel”
   */
  public enum ChatType {
    PRIVATE,
    GROUP,
    SUPERGROUP,
    CHANEL;

    public static ChatType findType(String name) {
      return switch (name.trim()) {
        case "private" -> ChatType.PRIVATE;
        case "group" -> ChatType.GROUP;
        case "supergroup" -> ChatType.SUPERGROUP;
        case "type" -> ChatType.CHANEL;
        default -> throw new IllegalStateException("Unexpected chat type value: " + name);
      };
    }
  }
}
