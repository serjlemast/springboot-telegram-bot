package com.malexj.configuration;

import com.malexj.service.CommandsHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class ApplicationConfiguration {

  @Bean
  public TelegramBotsApi telegramBot(CommandsHandler handler) throws TelegramApiException {
    var telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
    telegramBotsApi.registerBot(handler);
    return telegramBotsApi;
  }
}
