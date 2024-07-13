package com.example.eco_recicla;

public class Version {
    private String bill_number;
    private String userName;
    private String address;
    private String buyer_name;
    private String vehicle_plate;
    private boolean expanded;

    // Constructor
    public Version(String bill_number, String userName, String address, String buyer_name, String vehicle_plate) {
        this.bill_number = bill_number;
        this.userName = userName;
        this.address = address;
        this.buyer_name = buyer_name;
        this.vehicle_plate = vehicle_plate;
        this.expanded = false;
    }

    // Getters and Setters


    public String getBill_number() {
        return bill_number;
    }

    public void setBill_number(String bill_number) {
        this.bill_number = bill_number;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuyer_name() {
        return buyer_name;
    }

    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }

    public String getVehicle_plate() {
        return vehicle_plate;
    }

    public void setVehicle_plate(String vehicle_plate) {
        this.vehicle_plate = vehicle_plate;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    // toString method


    @Override
    public String toString() {
        return "Version{" +
                "bill_number='" + bill_number + '\'' +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", buyer_name='" + buyer_name + '\'' +
                ", vehicle_plate='" + vehicle_plate + '\'' +
                ", expanded=" + expanded +
                '}';
    }
}
