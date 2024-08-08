package com.shvg.sudokujourney.service;

import com.shvg.sudokujourney.model.dto.UserDto;

import java.util.List;

public interface FirebaseService {

    // USERS
    void saveUser(UserDto dto);
    void updateUser(UserDto dto);
    UserDto getUserById(String id);
    List<UserDto> getAllUsers();
    void deleteUser(String id);

}
