package com.malexj.service;

import com.malexj.model.TPrivateChat;
import com.malexj.model.TUser;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private static final Set<TPrivateChat> privateChats = new HashSet<>();
  private static final Set<TUser> users = new HashSet<>();

  public List<TUser> findAllUsers() {
    return new ArrayList<>(users);
  }

  public void savePrivateChatAndUser(TPrivateChat privateChat) {
    users.add(privateChat.user());
    privateChats.add(privateChat);
  }

  public void saveUser(TUser user) {
    users.add(user);
  }

  public Optional<TUser> getUserByUsername(String username) {
    return users.stream().filter(user -> user.username().equals(username)).findFirst();
  }

  public Optional<TPrivateChat> findPrivateChatByUsername(String username) {
    return privateChats.stream()
        .filter(tPrivateChat -> tPrivateChat.user().username().equals(username))
        .findFirst();
  }

  public List<TPrivateChat> findAllPrivateChats() {
    return new ArrayList<>(privateChats);
  }
}
