package com.shvg.sudokujourney.util.mapper;

import com.shvg.sudokujourney.model.dto.UserDto;
import com.shvg.sudokujourney.model.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    UserEntity userToEntity(UserDto dto);
    UserDto userToDto(UserEntity entity);

}
