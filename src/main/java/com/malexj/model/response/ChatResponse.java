package com.malexj.model.response;

import com.malexj.model.TChat;
import java.util.List;

public record ChatResponse(List<TChat> privateChats) {}
