package com.example.sqlitedemo;

public class PetrolShedAdmin {

    /*

Admin model calls is created to handle the Petrol admin object
*/

    private String PetrolShedId;
    private String PetrolShedName;
    private String AvailableQuantity;

// defining petrolshed admin details
    public PetrolShedAdmin( String petrolShedId, String petrolShedName, String availableQuantity) {
        PetrolShedId = petrolShedId;
        PetrolShedName = petrolShedName;
        AvailableQuantity = availableQuantity;
    }

    //this method is created for debugging purpose
    @Override
    public String toString() {
        return "PetrolShedAdmin{" +
                "PetrolShedId='" + PetrolShedId + '\'' +
                ", PetrolShedName='" + PetrolShedName + '\'' +
                ", AvailableQuantity='" + AvailableQuantity + '\'' +
                '}';
    }
}
