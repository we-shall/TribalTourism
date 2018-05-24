package com.example.vishalsingh.villageexpandedview;

/**
 * Created by vishalsingh on 22/03/18.
 */


public class Accomodation {
    private Address address;
    private String price;
    private String h_name;
    private String contact;
    private int capacityEachRoom;
    private String image1;
    private String image2;
    private int roomsAvailable;
    private String email;
    private double rating;
    private String nameOfplace;


    Accomodation(Address address, String price, String h_name, String contact, int capacityEachRoom, String image1,String image2, int roomsAvailable,String email,double rating,String nameOfplace){
        this.address = address;
        this.price = price;
        this.h_name = h_name;
        this.contact = contact;
        this.setCapacityEachRoom(capacityEachRoom);
        this.setImage1(image1);
        this.setImage2(image2);
        this.roomsAvailable = roomsAvailable;
        this.email = email;
        this.rating = rating;
        this.nameOfplace = nameOfplace;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getCapacityEachRoom() {
        return capacityEachRoom;
    }

    public void setCapacityEachRoom(int capacityEachRoom) {
        this.capacityEachRoom = capacityEachRoom;
    }

    public int getRoomsAvailable() {
        return roomsAvailable;
    }

    public void setRoomsAvailable(int roomsAvailable) {
        this.roomsAvailable = roomsAvailable;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getNameOfplace() {
        return nameOfplace;
    }

    public void setNameOfplace(String nameOfplace) {
        this.nameOfplace = nameOfplace;
    }
}

