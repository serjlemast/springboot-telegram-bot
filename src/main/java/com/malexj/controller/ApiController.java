package com.malexj.controller;

import com.malexj.model.request.MessageRequest;
import com.malexj.model.response.ChatResponse;
import com.malexj.model.response.MessageResponse;
import com.malexj.model.response.UserResponse;
import com.malexj.service.StorageService;
import com.malexj.service.TelegramPollingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.objects.Message;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ApiController {

  private final StorageService storageService;
  private final TelegramPollingService pollingService;

  @GetMapping("/users")
  public ResponseEntity<UserResponse> findUsers() {
    return ResponseEntity.ok(new UserResponse(storageService.findAllUsers()));
  }

  @GetMapping("/chats")
  public ResponseEntity<ChatResponse> findAllPrivateChats() {
    return ResponseEntity.ok(new ChatResponse(storageService.findAllChats()));
  }

  @PostMapping("/messages")
  public ResponseEntity<MessageResponse> sendMessage(@RequestBody MessageRequest request) {
    Message message = pollingService.sendMessage(request.catId(), request.message());
    return ResponseEntity.ok(new MessageResponse(message));
  }
}
