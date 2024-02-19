package com.malexj.model.response;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.TimeZone;
import org.telegram.telegrambots.meta.api.objects.Message;

public record MessageResponse(Long messageId, LocalDateTime dateTime) {

  public MessageResponse(Message message) {

    this(
        message.getChatId(),
        Optional.ofNullable(message.getDate())
            .map(
                date ->
                    LocalDateTime.ofInstant(
                        Instant.ofEpochMilli(date), TimeZone.getDefault().toZoneId()))
            .orElse(LocalDateTime.now()));
  }
}
