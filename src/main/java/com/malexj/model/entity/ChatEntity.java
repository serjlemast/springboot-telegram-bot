package com.malexj.model.entity;

import com.malexj.model.ChatType;
import com.malexj.model.UserDto;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "chats")
@TypeAlias("ChatEntity")
public class ChatEntity {
  @MongoId private String id;

  @Field(name = "active")
  private boolean isActive;

  @Indexed(unique = true)
  private Long chatId;

  private String title;
  private ChatType type;

  //  @DBRef
  private UserDto user;
}
