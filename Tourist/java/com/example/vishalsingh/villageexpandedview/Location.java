package com.example.vishalsingh.villageexpandedview;

/**
 * Created by vishalsingh on 22/03/18.
 */

public class Location {

    private String villageName;
    private String latVillage;
    private String longVillage;

    Location (String villageName, String latVillage, String longVillage, String currentLat, String currentLong){
        this.setLatVillage(latVillage);
        this.setLongVillage(longVillage);
        this.setVillageName(villageName);

    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getLatVillage() {
        return latVillage;
    }

    public void setLatVillage(String latVillage) {
        this.latVillage = latVillage;
    }

    public String getLongVillage() {
        return longVillage;
    }

    public void setLongVillage(String longVillage) {
        this.longVillage = longVillage;
    }

}
