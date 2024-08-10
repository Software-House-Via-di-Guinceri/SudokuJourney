package com.shvg.sudokujourney.controller;

import com.shvg.sudokujourney.model.dto.UserDto;
import com.shvg.sudokujourney.service.database.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    public static final String QUALIFIER = "user";
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers(@AuthenticationPrincipal OAuth2User user) {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@AuthenticationPrincipal OAuth2User user, @PathVariable String id) {
        return new ResponseEntity<>(
                userService.getById(id),
                HttpStatus.OK
        );
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
