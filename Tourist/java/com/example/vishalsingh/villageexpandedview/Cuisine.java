package com.example.vishalsingh.villageexpandedview;



/**
 * Created by vishalsingh on 21/03/18.
 */

public class Cuisine {

    private String url;
    private String description;
    private String shortDescription;
    private double rating;
    private String c_name;
    private String recipe;
    private String price;
    private String ingredients;
    private String shopName;
    private String location;
    private int category;
    private String opentime;
    private String closetime;


    Cuisine(String url, String description, double rating, String longname, String recipe, String price, String ingredients, String shopName, String location, int category, String opentime, String closetime){
        this.url = url;
        this.description = description;
        this.rating = rating;
        this.c_name = longname;
        this.recipe = recipe;
        this.price = price;
        this.ingredients = ingredients;
        this.shopName = shopName;
        this.location = location;
        this.category = category;
        this.opentime = opentime;
        this.closetime = closetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public  String getImage() {
        return url ;
    }

    public void setImage(String image) {
        this.url = image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getLongname() {
        return c_name;
    }

    public void setLongname(String longname) {
        this.c_name = longname;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getClosetime() {
        return closetime;
    }

    public void setClosetime(String closetime) {
        this.closetime = closetime;
    }



    public String getShortDescriotion() {
        return shortDescription;
    }

    public void setShortDescriotion(String shortDescriotion) {
        this.shortDescription = shortDescriotion;
    }
}
