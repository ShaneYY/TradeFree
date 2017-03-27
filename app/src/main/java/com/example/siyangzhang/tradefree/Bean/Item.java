package com.example.siyangzhang.tradefree.Bean;

import java.io.Serializable;
import java.util.UUID;

public class Item implements Serializable {

    private UUID itemId;
    private User user;
    private Photo image;
    private String title;
    private String price;
    private String detail;
    private String type;
    private String time;
    private boolean sold;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public boolean isSold() {return sold;}

    public void setSold(boolean sold) {this.sold = sold;}

    public UUID getItemId() {return itemId;}

    public void setItemId(UUID itemId) {this.itemId = itemId;}

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public Photo getImage() {return image;}

    public void setImage(Photo image) {this.image = image;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getPrice() {return price;}

    public void setPrice(String price) {this.price = price;}

    public String getDetail() {return detail;}

    public void setDetail(String detail) {this.detail = detail;}

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}
}
