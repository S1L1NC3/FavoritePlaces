package com.dmd.favoriteplacesjavaversion.model;

import java.io.Serializable;

public class FavoritePlace implements Serializable {
    private int ID;
    private String Name;
    private Double Latitude;
    private Double Longitude;

    public FavoritePlace(int ID, String name, Double latitude, Double longitude) {
        this.ID = ID;
        Name = name;
        Latitude = latitude;
        Longitude = longitude;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }
}
