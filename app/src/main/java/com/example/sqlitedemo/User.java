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

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPetrolShed() {
        return PetrolShed;
    }

    public void setPetrolShed(String petrolShed) {
        PetrolShed = petrolShed;
    }

    public String getPetrolFillStatus() {
        return PetrolFillStatus;
    }

    public void setPetrolFillStatus(String petrolFillStatus) {
        PetrolFillStatus = petrolFillStatus;
    }

    public String getPetrolFillQuantity() {
        return PetrolFillQuantity;
    }

    public void setPetrolFillQuantity(String petrolFillQuantity) {
        PetrolFillQuantity = petrolFillQuantity;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId='" + UserId + '\'' +
                ", UserName='" + UserName + '\'' +
                ", PetrolShed='" + PetrolShed + '\'' +
                ", PetrolFillStatus='" + PetrolFillStatus + '\'' +
                ", PetrolFillQuantity='" + PetrolFillQuantity + '\'' +
                '}';
    }
}
