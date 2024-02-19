package com.malexj.mapper;

import com.malexj.model.ChatDto;
import com.malexj.model.UserDto;
import com.malexj.model.entity.ChatEntity;
import com.malexj.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** MapStruct mapper: */
@Mapper(componentModel = "spring")
public interface ObjectMapper {

  ChatDto entityToDto(ChatEntity entity);

  UserDto entityToDto(UserEntity entity);

  @Mapping(target = "id", ignore = true)
  ChatEntity dtoToEntity(ChatDto dto);

  @Mapping(target = "id", ignore = true)
  UserEntity dtoToEntity(UserDto dto);
}
