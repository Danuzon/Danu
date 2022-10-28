package com.example.sqlitedemo;
// defining user attributes

/*
User Model class is created here to handle the user object efficiently
 */

public class User {

    String UserName;
    String PetrolShed;
    String PetrolFillStatus;
    String VehicleType;
    String PetrolFillQuantity;

    public User(String userName, String petrolShed, String petrolFillStatus, String vehicleType, String petrolFillQuantity) {
        UserName = userName;
        PetrolShed = petrolShed;
        PetrolFillStatus = petrolFillStatus;
        VehicleType = vehicleType;
        PetrolFillQuantity = petrolFillQuantity;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(String vehicleType) {
        VehicleType = vehicleType;
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
                "UserName='" + UserName + '\'' +
                ", PetrolShed='" + PetrolShed + '\'' +
                ", PetrolFillStatus='" + PetrolFillStatus + '\'' +
                ", VehicleType='" + VehicleType + '\'' +
                ", PetrolFillQuantity='" + PetrolFillQuantity + '\'' +
                '}';
    }
}
