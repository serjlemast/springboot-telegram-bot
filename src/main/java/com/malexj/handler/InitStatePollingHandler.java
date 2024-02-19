package com.malexj.handler;

import java.util.List;
import java.util.Optional;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.MaybeInaccessibleMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Slf4j
public abstract class InitStatePollingHandler extends TelegramLongPollingBot {

  /** States */
  protected static final String START_STATE = "/start";

  private static final String DONT_NEED_CAT_STATE = "dont-need-cat";
  private static final String BUY_CAT_STATE = "buy-cat";
  private static final String CAT_NEWS_STATE = "cat-news";

  public InitStatePollingHandler(String token) {
    super(token);
  }

  protected void privateChatCallback(Update update) {
    Optional.ofNullable(update.getCallbackQuery())
        .map(CallbackQuery::getData)
        .ifPresent(
            data -> {
              var chatId = findChatId(update);
              switch (data) {
                case DONT_NEED_CAT_STATE ->
                    sendMessage(chatId, "Не нужен тебе кот, если сюда нажал!!!");
                case BUY_CAT_STATE -> sendMessage(chatId, "Бля нажми кнопку 'Где купить кота' !");
                case CAT_NEWS_STATE ->
                    sendMessage(
                        chatId,
                        buildText("Вот тут красивые коты!!!", "https://skrynya.ua/product/984134"));
              }
            });
  }

  protected Message initState(Message message) {
    log.info("Init '/start' chat with bot");
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
    return sendMessageWithMarkup(chatId, replyMarkup);
  }

  private Long findChatId(Update update) {
    return Optional.ofNullable(update.getCallbackQuery())
        .map(CallbackQuery::getMessage)
        .map(MaybeInaccessibleMessage::getChatId)
        .orElseThrow();
  }

  private String buildText(String... srt) {
    return String.join(" ", srt);
  }

  @SneakyThrows
  private void sendMessage(Long chatId, String text) {
    var message =
        SendMessage.builder() //
            .chatId(chatId)
            .parseMode(ParseMode.MARKDOWN)
            .text(text)
            .build();
    execute(message);
  }

  @SneakyThrows
  private Message sendMessageWithMarkup(Long chatId, ReplyKeyboard replyKeyboard) {
    var message =
        SendMessage.builder() //
            .chatId(chatId)
            .parseMode(ParseMode.MARKDOWN)
            .text("Чудо бот, который поможет вам купить кота!")
            .replyMarkup(replyKeyboard)
            .build();
    return execute(message);
  }
}
