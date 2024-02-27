package com.malexj.service;

import com.malexj.exception.TelegramApiServiceException;
import com.malexj.model.request.MessageRequest;
import com.malexj.model.response.ChatResponse;
import com.malexj.model.response.MessageResponse;
import com.malexj.model.response.UserResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramApiService {

  private static final String SHORT_MASSAGE_FORMAT = "%s ...";

  private final DefaultAbsSender sender;
  private final StorageService storageService;

  public MessageResponse sendMessage(MessageRequest request) {
    log.info(
        "Message to be sent, chatId - {}, short message - {}",
        request.catId(),
        shortMessage(request));
    try {
      Message message =
          sender.execute(
              SendMessage.builder()
                  .protectContent(true)
                  .chatId(request.catId())
                  .parseMode(ParseMode.MARKDOWN)
                  .text(request.message())
                  .build());
      return new MessageResponse(message);
    } catch (TelegramApiException e) {
      log.error("Telegram Api error - {}", e.getMessage());
      throw new TelegramApiServiceException(e.getMessage(), e);
    }
  }

  private String shortMessage(MessageRequest request) {
    return Optional.ofNullable(request.message())
        .filter(message -> message.length() > 60)
        .map(message -> String.format(SHORT_MASSAGE_FORMAT, message.substring(0, 50)))
        .orElse("");
  }

  public UserResponse findAllUsers() {
    return new UserResponse(storageService.findAllUsers());
  }

  public ChatResponse findAllChats() {
    return new ChatResponse(storageService.findAllChats());
  }
}
