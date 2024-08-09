package com.shvg.sudokujourney.service;

import com.google.gson.JsonObject;

public interface GoogleService {

    String getOauthAccessTokenGoogle(String code);

    JsonObject getProfileDetailsGoogle(String accessToken);

}
