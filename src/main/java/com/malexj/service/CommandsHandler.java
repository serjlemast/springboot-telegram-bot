package com.malexj.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Slf4j
@Service
public class CommandsHandler extends TelegramLongPollingBot {

  @Value("${telegram.bot.username}")
  private String username;

  public CommandsHandler(@Value("${telegram.bot.token}") String token) {
    super(token);
  }

  @Override
  public String getBotUsername() {
    return username;
  }

  /** States */
  private static final String START_STATE = "/start";

  private static final String DONT_NEED_CAT_STATE = "dont-need-cat";
  private static final String BUY_CAT_STATE = "buy-cat";
  private static final String CAT_NEWS_STATE = "cat-news";

  /** Logger */
  private static final String TEXT_FORMAT = "tx - {}, User - {}";

  private static final String TEXT_MESSAGE_FORMAT = "tx - {}, message date - {}";

  @Override
  public void onUpdateReceived(Update update) {
    String txId = "id:" + UUID.randomUUID() + ":date:" + LocalDateTime.now();
    if (update.hasMessage()) {
      Optional.ofNullable(update.getMessage())
          .filter(Message::hasText)
          .filter(message -> START_STATE.equals(message.getText()))
          .map(message -> userInfo(txId, message))
          .ifPresent(
              message -> {
                Message respMsg = initState(message);
                log.info(TEXT_MESSAGE_FORMAT, txId, respMsg.getDate());
              });
      return;
    }

    Optional.ofNullable(update.getCallbackQuery())
        .map(callbackQuery -> userInfo(txId, callbackQuery))
        .map(CallbackQuery::getData)
        .ifPresent(
            data -> {
              Message message =
                  switch (data) {
                    case DONT_NEED_CAT_STATE -> dontNeedCat(update);
                    case BUY_CAT_STATE -> buyCat(update);
                    case CAT_NEWS_STATE -> catNews(update);
                    default -> throw new IllegalStateException("Unexpected value: " + data);
                  };
              log.info(TEXT_MESSAGE_FORMAT, txId, message.getDate());
            });
  }

  private CallbackQuery userInfo(String txId, CallbackQuery callbackQuery) {
    log.info(TEXT_FORMAT, txId, callbackQuery.getFrom());
    return callbackQuery;
  }

  private Message userInfo(String txId, Message message) {
    log.info(TEXT_FORMAT, txId, message.getFrom());
    return message;
  }

  @SneakyThrows
  private Message dontNeedCat(Update update) {
    return execute(
        SendMessage.builder()
            .chatId(update.getCallbackQuery().getMessage().getChatId())
            .parseMode(ParseMode.MARKDOWN)
            .text("Не нужен тебе кот, если сюда нажал!!!")
            .build());
  }

  @SneakyThrows
  private Message buyCat(Update update) {
    Long chatId = update.getCallbackQuery().getMessage().getChatId();
    return execute(
        SendMessage.builder()
            .chatId(chatId)
            .parseMode(ParseMode.MARKDOWN)
            .text("Бля нажми кнопку 'Где купить кота' !")
            .build());
  }

  @SneakyThrows
  private Message catNews(Update update) {
    Long chatId = update.getCallbackQuery().getMessage().getChatId();
    SendMessage sendMessage =
        SendMessage.builder() //
            .chatId(chatId) //
            .parseMode(ParseMode.MARKDOWN) //
            .text(
                buildText(
                    "Вот тут красивые коты!!!", "\n", "https://skrynya.ua/product/984134")) //
            .build();
    return execute(sendMessage);
  }

  private String buildText(String... srt) {
    return String.join("", srt);
  }

  @SneakyThrows
  private Message initState(Message message) {
    long chatId = message.getChatId();
    var firstRow =
        List.of(
            InlineKeyboardButton.builder() //
                .text("Нужен кот?") //
                .callbackData(DONT_NEED_CAT_STATE) //
                .build(),
            InlineKeyboardButton.builder() //
                .text("Купи кота!") //
                .callbackData(BUY_CAT_STATE) //
                .build());
    var secondRow =
        List.of(
            InlineKeyboardButton.builder()
                .text("Где купить кота")
                .callbackData(CAT_NEWS_STATE)
                .build());
    var replyMarkup =
        InlineKeyboardMarkup.builder() //
            .keyboard(List.of(firstRow, secondRow)) //
            .build();
    return execute(
        SendMessage.builder()
            .chatId(chatId)
            .parseMode(ParseMode.MARKDOWN)
            .text("Чудо бот, который поможет вам купить кота!")
            .replyMarkup(replyMarkup)
            .build());
  }
}
