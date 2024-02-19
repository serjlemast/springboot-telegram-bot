package com.malexj.service;

import com.malexj.model.ChatDto;
import com.malexj.model.ChatType;
import com.malexj.model.UserDto;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StorageService {

  private static final Set<UserDto> users = new HashSet<>();
  private static final Set<ChatDto> chats = new HashSet<>();

  public void saveChat(ChatDto chat) {
    ChatType chatType = chat.getType();
    if (chat.getType() == ChatType.PRIVATE) {
      users.add(chat.getUser());
    }
    chats.add(chat);
    log.info(
        "Init new '{}' chat with 'id' - {} and title - '{}'",
        chatType,
        chat.getId(),
        chat.getTitle());
  }

  public List<UserDto> findAllUsers() {
    return new ArrayList<>(users);
  }

  public List<ChatDto> findAllChats() {
    return new ArrayList<>(chats);
  }

  public List<ChatDto> findAllChatsByType(ChatType type) {
    return chats.stream() //
        .filter(chat -> chat.getType() == type) //
        .toList();
  }
}
