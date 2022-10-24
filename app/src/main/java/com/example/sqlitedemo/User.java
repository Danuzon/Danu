package com.example.sqlitedemo;

public class User {

    String UserId;
    String UserName;
    String PetrolShed;
    String PetrolFillStatus;
    String PetrolFillQuantity;

    public User(String userId, String userName, String petrolShed, String petrolFillStatus, String petrolFillQuantity) {
        UserId = userId;
        UserName = userName;
        PetrolShed = petrolShed;
        PetrolFillStatus = petrolFillStatus;
        PetrolFillQuantity = petrolFillQuantity;
    }
}
