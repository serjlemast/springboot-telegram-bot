package com.malexj.service;

import com.malexj.mapper.ObjectMapper;
import com.malexj.model.ChatDto;
import com.malexj.model.ChatType;
import com.malexj.model.UserDto;
import com.malexj.model.entity.UserEntity;
import com.malexj.repository.ChatRepository;
import com.malexj.repository.UserRepository;
import java.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageService {

  private final ObjectMapper mapper;
  private final UserRepository userRepository;
  private final ChatRepository chatRepository;

  public void saveChat(ChatDto chatDto) {
    if (chatDto.getType() == ChatType.PRIVATE) {
      UserDto userDto = chatDto.getUser();
      if (!userRepository.existsByUserId(userDto.userId())) {
        userRepository.save(mapper.dtoToEntity(userDto));
      }
    }
    if (!chatRepository.existsByChatId(chatDto.getChatId())) {
      chatRepository.save(mapper.dtoToEntity(chatDto));
    }
  }

  public List<UserDto> findAllUsers() {
    return userRepository.findAll().stream().map(mapper::entityToDto).toList();
  }

  public List<ChatDto> findAllChats() {
    return chatRepository.findAll().stream().map(mapper::entityToDto).toList();
  }
}
