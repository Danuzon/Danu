package com.example.sqlitedemo;

public class PetrolShedAdmin {

    private String PetrolShedId;
    private String PetrolShedName;
    private String AvailableQuantity;

    public PetrolShedAdmin(String petrolShedId, String petrolShedName, String availableQuantity) {
        PetrolShedId = petrolShedId;
        PetrolShedName = petrolShedName;
        AvailableQuantity = availableQuantity;
    }

    public String getPetrolShedId() {
        return PetrolShedId;
    }

    public void setPetrolShedId(String petrolShedId) {
        PetrolShedId = petrolShedId;
    }

    public String getPetrolShedName() {
        return PetrolShedName;
    }

    public void setPetrolShedName(String petrolShedName) {
        PetrolShedName = petrolShedName;
    }

    public String getAvailableQuantity() {
        return AvailableQuantity;
    }

    public void setAvailableQuantity(String availableQuantity) {
        AvailableQuantity = availableQuantity;
    }
}
