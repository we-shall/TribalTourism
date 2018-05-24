package com.example.vishalsingh.villageexpandedview;

/**
 * Created by vishalsingh on 22/03/18.
 */

public class Address {
    private String houseno;
    private String locality;
    private String street;
    private String district;
    private String pincode;
    private String state;

    Address(String houseno, String locality, String street, String district,String pincode, String state){
        this.houseno = houseno;
        this.locality = locality;
        this.street = street;
        this.district = district;
        this.pincode = pincode;
        this.state = state;
    }
    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Address: " + houseno + ", " +locality + ", "+ street + ", "+ district + ",\n " + state + "\n " +pincode;
    }
}


