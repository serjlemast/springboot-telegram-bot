package com.malexj.configuration;

import com.malexj.filter.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

  private final LongPollingBot bot;

  @SneakyThrows
  @EventListener({ContextRefreshedEvent.class})
  public void init() {
    var botsApi = new TelegramBotsApi(DefaultBotSession.class);
    var botSession = botsApi.registerBot(bot);
    log.info("Starting TelegramBotsApi - {}", botSession.isRunning());
  }

  @Bean
  public FilterRegistrationBean<JwtTokenFilter> jwtFilter(JwtTokenFilter jwtTokenFilter) {
    FilterRegistrationBean<JwtTokenFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(jwtTokenFilter);
    registrationBean.addUrlPatterns("/v1/*");
    return registrationBean;
  }
}
