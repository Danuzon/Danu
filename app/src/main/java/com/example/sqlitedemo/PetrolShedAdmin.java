package com.example.sqlitedemo;

public class PetrolShedAdmin {

    private String PetrolShedId;
    private String PetrolShedName;
    private String AvailableQuantity;

    public PetrolShedAdmin( String petrolShedId, String petrolShedName, String availableQuantity) {
        PetrolShedId = petrolShedId;
        PetrolShedName = petrolShedName;
        AvailableQuantity = availableQuantity;
    }
}
