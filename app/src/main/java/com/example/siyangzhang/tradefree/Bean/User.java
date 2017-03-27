package com.example.siyangzhang.tradefree.Bean;

import java.io.Serializable;
import java.util.UUID;
import com.google.android.maps.GeoPoint;

public class User implements Serializable {
    private UUID userId;
    private String name;
    private Boolean sex;
    private String number;

    /**
     *  Need to figure out how to use GeoPoint later
     */
    //private GeoPoint location;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    //public GeoPoint getLocation() {
    //    return location;
    //}

    //public void setLocation(GeoPoint location) {
    //    this.location = location;
    //}
}
