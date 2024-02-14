package com.malexj.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

  private final LongPollingBot bot;

  @EventListener({ContextRefreshedEvent.class})
  public void init() {
    try {
      var botsApi = new TelegramBotsApi(DefaultBotSession.class);
      var botSession = botsApi.registerBot(bot);
      log.info("Starting TelegramBotsApi - {}", botSession.isRunning());
    } catch (TelegramApiException e) {
      log.warn(e.getMessage());
    }
  }
}
