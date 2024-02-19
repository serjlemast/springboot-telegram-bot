package com.malexj.handler;

import com.malexj.model.ChatDto;
import com.malexj.service.StorageService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.*;

@Slf4j
@Service
public class TelegramPollingBotHandler extends InitStatePollingHandler {

  private final StorageService storageService;

  @Value("${telegram.bot.username}")
  private String username;

  public TelegramPollingBotHandler(
      @Value("${telegram.bot.token}") String token, StorageService storageService) {
    super(token);
    this.storageService = storageService;
  }

  @Override
  public String getBotUsername() {
    return username;
  }

  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage()) {
      Message message = update.getMessage();
      /*
       * Handle start private conversation with bot
       */
      Optional.ofNullable(message)
          .filter(Message::isCommand)
          .filter(msg -> START_STATE.equals(msg.getText()))
          .map(this::initState)
          .ifPresent(msg -> storageService.saveChat(new ChatDto(msg.getChat(), msg.getFrom())));

      /*
       * Handle private message to bot
       */
      Optional.ofNullable(message)
          .filter(Message::isUserMessage)
          .ifPresent(
              msg -> {
                var user = msg.getFrom();
                log.info(
                    "Private message - '{}' from user: '{}'", msg.getText(), user.getFirstName());
              });
      /*
       * Handle group message
       */
      Optional.ofNullable(message)
          .filter(Message::isSuperGroupMessage)
          .ifPresent(
              msg -> {
                var chat = msg.getChat();
                var user = msg.getFrom();
                log.info(
                    "Super group message: '{}', to '{}' '{}' chat, from user: '{}'",
                    msg.getText(),
                    chat.getType(),
                    chat.getTitle(),
                    user.getFirstName());
              });
      return;
    }

    /*
     * Interaction wit telegram bot in private chanel
     */
    if (update.hasCallbackQuery()) {
      privateChatCallback(update);
      return;
    }

    /*
     * Handle initial initialization of telegram bot in the group or channel
     */
    if (update.hasMyChatMember()) {
      Optional.ofNullable(update.getMyChatMember())
          .map(ChatMemberUpdated::getChat)
          .ifPresent(
              chat -> {
                log.info(
                    "Init new '{}' chat with 'id' - {} and title - '{}'",
                    chat.getType(),
                    chat.getId(),
                    chat.getTitle());
                storageService.saveChat(new ChatDto(chat));
              });
      return;
    }

    /*
     * handle chat (group or channel) message
     */
    if (update.hasChannelPost()) {
      Optional.ofNullable(update.getChannelPost())
          .ifPresent(
              message -> {
                var chat = message.getChat();
                log.info(
                    "Chat message: '{}', from '{}' chanel '{}'",
                    message.getText(),
                    chat.getType(),
                    chat.getTitle());
              });
      return;
    }
    log.warn("unknown command or query - {}", update);
  }
}
