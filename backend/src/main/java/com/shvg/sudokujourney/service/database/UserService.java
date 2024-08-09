package com.shvg.sudokujourney.service.database;

import com.shvg.sudokujourney.model.dto.UserDto;

import java.util.List;

public interface UserService {

    void save(UserDto dto);
    UserDto getById(String id);
    UserDto getByUsername(String username);
    List<UserDto> getAll();
    void delete(String id);

}
