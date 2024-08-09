package com.shvg.sudokujourney.controller;

import com.shvg.sudokujourney.model.dto.UserDto;
import com.shvg.sudokujourney.service.database.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    public static final String QUALIFIER = "user";
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<UserDto> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable String id) {
        return userService.getById(id);
    }

    @PostMapping("/set")
    public void saveUser(@RequestBody UserDto userDto) {
        userService.save(userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.delete(id);
    }

}
