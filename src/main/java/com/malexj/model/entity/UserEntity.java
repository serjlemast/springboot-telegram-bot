package com.malexj.model.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "user")
public class UserEntity {
  @MongoId private String id;

  @Indexed(unique = true)
  private Long userId;

  private String firstName;
  private String lastName;
  private String username;
}
