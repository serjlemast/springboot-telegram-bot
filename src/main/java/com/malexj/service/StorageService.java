package com.malexj.service;

import com.malexj.model.TChat;
import com.malexj.model.TUser;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

  private static final Set<TUser> users = new HashSet<>();
  private static final Set<TChat> chats = new HashSet<>();

  public void saveChat(TChat chat) {
    if (chat.getType() == TChat.ChatType.PRIVATE) {
      users.add(chat.getUser());
    }
    chats.add(chat);
  }

  public List<TUser> findAllUsers() {
    return new ArrayList<>(users);
  }

  public List<TChat> findAllChats() {
    return new ArrayList<>(chats);
  }

  public List<TChat> findAllChatsByType(TChat.ChatType type) {
    return chats.stream() //
        .filter(chat -> chat.getType() == type) //
        .toList();
  }
}
