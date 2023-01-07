package com.gonion.identity.mapper;

import com.gonion.identity.entity.User;
import com.gonion.identity.framework.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  UserResponse userToUserDto(User user);
}
