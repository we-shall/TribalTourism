package com.example.vishalsingh.villageexpandedview;

/**
 * Created by vishalsingh on 22/03/18.
 */

public class Description {

    private String nameOfVillage;
    private String description;
    private String  galleryImage1;
    private String  galleryImage2;
    private String  galleryImage3;
    private String  galleryImage4;
    private String history;
    private String culture;
    private double rating;
    private String speciality;

    Description(String nameOfVillage, String description, String galleryImage1,String galleryImage2,String galleryImage3,String galleryImage4, String history, String culture,double rating,String speciality){
        this.setNameOfVillage(nameOfVillage);
        this.setDescription(description);
        this.setGalleryImage1(galleryImage1);
        this.setGalleryImage2(galleryImage2);
        this.setGalleryImage3(galleryImage3);
        this.setGalleryImage4(galleryImage4);
        this.setHistory(history);
        this.setCulture(culture);
        this.setRating(rating);
        this.setSpeciality(speciality);
    }

    public String getNameOfVillage() {
        return nameOfVillage;
    }

    public void setNameOfVillage(String nameOfVillage) {
        this.nameOfVillage = nameOfVillage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getGalleryImage1() {
        return galleryImage1;
    }

    public void setGalleryImage1(String galleryImage1) {
        this.galleryImage1 = galleryImage1;
    }

    public String getGalleryImage2() {
        return galleryImage2;
    }

    public void setGalleryImage2(String galleryImage2) {
        this.galleryImage2 = galleryImage2;
    }

    public String getGalleryImage3() {
        return galleryImage3;
    }

    public void setGalleryImage3(String galleryImage3) {
        this.galleryImage3 = galleryImage3;
    }

    public String getGalleryImage4() {
        return galleryImage4;
    }

    public void setGalleryImage4(String galleryImage4) {
        this.galleryImage4 = galleryImage4;
    }
}
