package com.malexj.model.response;

import com.malexj.model.ChatDto;
import java.util.List;

public record ChatResponse(List<ChatDto> privateChats) {}
