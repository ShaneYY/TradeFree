package com.example.siyangzhang.tradefree.Bean;

import java.io.Serializable;
import java.util.UUID;
import com.google.android.maps.GeoPoint;

/**
 * Created by siyangzhang on 2/10/17.
 */

public class User implements Serializable {
    private UUID userId;
    private String name;
    private String sex;
    private String number;
    private GeoPoint location;

    /**
     *  Need to figure out how to use GeoPoint later
     */

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }
}
