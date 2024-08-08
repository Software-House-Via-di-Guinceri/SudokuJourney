package com.shvg.sudokujourney.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {

    public static final String COLLECTION_NAME = "users";

    private String id;
    private String firstName;
    private String lastName;

}
