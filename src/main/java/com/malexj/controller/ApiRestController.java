package com.malexj.controller;

import com.malexj.model.request.MessageRequest;
import com.malexj.model.response.ChatResponse;
import com.malexj.model.response.MessageResponse;
import com.malexj.model.response.UserResponse;
import com.malexj.service.TelegramApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ApiRestController {

  private final TelegramApiService apiService;

  @GetMapping("/users")
  public ResponseEntity<UserResponse> findUsers() {
    return ResponseEntity.ok(apiService.findAllUsers());
  }

  @GetMapping("/chats")
  public ResponseEntity<ChatResponse> findAllPrivateChats() {
    return ResponseEntity.ok(apiService.findAllChats());
  }

  @PostMapping("/messages")
  public ResponseEntity<MessageResponse> sendMessage(@RequestBody MessageRequest request) {
    return ResponseEntity.ok(apiService.sendMessage(request));
  }
}
