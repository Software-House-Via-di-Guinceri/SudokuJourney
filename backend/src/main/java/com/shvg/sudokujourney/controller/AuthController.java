package com.shvg.sudokujourney.controller;

import com.google.gson.JsonObject;
import com.shvg.sudokujourney.service.GoogleService;
import com.shvg.sudokujourney.service.database.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class AuthController {

    private final GoogleService googleService;
    private final UserService userService;

    public AuthController(GoogleService googleService, UserService databaseService) {
        this.googleService = googleService;
        this.userService = databaseService;
    }

    @GetMapping("grantcode")
    public String grantCode(@RequestParam("code") String code, @RequestParam("scope") String scope, @RequestParam("authuser") String authUser, @RequestParam("prompt") String prompt) {
        JsonObject userDetails = googleService.getProfileDetailsGoogle(googleService.getOauthAccessTokenGoogle(code));

        // salva se nuovo utente
        //if (databaseService.)

        // genera e restituisci access token

        return "";
    }

}
