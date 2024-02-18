package com.malexj.controller;

import com.malexj.model.response.TPrivateChatResponse;
import com.malexj.model.response.TUserResponse;
import com.malexj.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ApiController {

  private final UserService userService;

  @GetMapping("/users")
  public ResponseEntity<TUserResponse> findAll() {
    return ResponseEntity.ok(new TUserResponse(userService.findAllUsers()));
  }

  @GetMapping("/chats")
  public ResponseEntity<TPrivateChatResponse> findAllPrivateChats() {
    return ResponseEntity.ok(new TPrivateChatResponse(userService.findAllPrivateChats()));
  }
}
