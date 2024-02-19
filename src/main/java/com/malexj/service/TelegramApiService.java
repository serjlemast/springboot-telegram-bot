package com.malexj.service;

import com.malexj.model.request.MessageRequest;
import com.malexj.model.response.ChatResponse;
import com.malexj.model.response.MessageResponse;
import com.malexj.model.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@RequiredArgsConstructor
public class TelegramApiService {

  private final DefaultAbsSender sender;
  private final StorageService storageService;

  @SneakyThrows
  public MessageResponse sendMessage(MessageRequest request) {
    Message message =
        sender.execute(
            SendMessage.builder()
                .protectContent(true)
                .chatId(request.catId())
                .parseMode(ParseMode.MARKDOWN)
                .text(request.message())
                .build());
    return new MessageResponse(message);
  }

  public UserResponse findAllUsers() {
    return new UserResponse(storageService.findAllUsers());
  }

  public ChatResponse findAllChats() {
    return new ChatResponse(storageService.findAllChats());
  }
}
