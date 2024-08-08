package com.shvg.sudokujourney.controller;

import com.google.cloud.firestore.WriteResult;
import com.shvg.sudokujourney.model.dto.UserDto;
import com.shvg.sudokujourney.model.entity.UserEntity;
import com.shvg.sudokujourney.service.FirebaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final FirebaseService firebaseService;

    public UserController(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @GetMapping("")
    public List<UserDto> getAllUsers() {
        return firebaseService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable String id) {
        return firebaseService.getUserById(id);
    }

    @PostMapping("/set")
    public void saveUser(@RequestBody UserDto userDto) {
        firebaseService.saveUser(userDto);
    }

    @PostMapping("/update")
    public void updateUser(@RequestBody UserDto userDto) {
        firebaseService.updateUser(userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        firebaseService.deleteUser(id);
    }

}
