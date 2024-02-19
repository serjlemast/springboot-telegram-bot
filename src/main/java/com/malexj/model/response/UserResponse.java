package com.malexj.model.response;

import com.malexj.model.UserDto;
import java.util.List;

public record UserResponse(List<UserDto> users) {}
