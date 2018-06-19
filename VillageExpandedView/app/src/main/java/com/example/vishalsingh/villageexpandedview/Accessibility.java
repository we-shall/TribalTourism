package com.example.vishalsingh.villageexpandedview;

/**
 * Created by vishalsingh on 22/03/18.
 */

public class Accessibility {

    private String nameAirport;
    private String nameRailway;
    private String nameBusstop;
    private String nearestBusstop;
    private String nearestAirport;
    private String nearestRailway;


    Accessibility(String nameAirport, String nameRailway, String nameBusstop, String nearestAirport, String nearestBusstop,String nearestRailway){
        this.setNameAirport(nameAirport);
        this.setNameRailway(nameRailway);
        this.setNameBusstop(nameBusstop);
        this.setNearestAirport(nearestAirport);
        this.setNearestBusstop(nearestBusstop);
        this.setNearestRailway(nearestRailway);
    }


    public String getNameAirport() {
        return nameAirport;
    }

    public void setNameAirport(String nameAirport) {
        this.nameAirport = nameAirport;
    }

    public String getNameRailway() {
        return nameRailway;
    }

    public void setNameRailway(String nameRailway) {
        this.nameRailway = nameRailway;
    }

    public String getNameBusstop() {
        return nameBusstop;
    }

    public void setNameBusstop(String nameBusstop) {
        this.nameBusstop = nameBusstop;
    }


    public String getNearestBusstop() {
        return nearestBusstop;
    }

    public void setNearestBusstop(String nearestBusstop) {
        this.nearestBusstop = nearestBusstop;
    }

    public String getNearestAirport() {
        return nearestAirport;
    }

    public void setNearestAirport(String nearestAirport) {
        this.nearestAirport = nearestAirport;
    }

    public String getNearestRailway() {
        return nearestRailway;
    }

    public void setNearestRailway(String nearestRailway) {
        this.nearestRailway = nearestRailway;
    }
}
