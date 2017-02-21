package com.example.siyangzhang.tradefree;

import java.util.UUID;
import com.google.android.maps.GeoPoint;
/**
 * Created by siyangzhang on 2/17/17.
 */

public class User {
    private UUID userId;
    private String name;
    private Boolean sex;
    private String number;
    private GeoPoint location;

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

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

}
