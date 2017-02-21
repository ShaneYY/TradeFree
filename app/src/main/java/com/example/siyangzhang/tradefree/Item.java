package com.example.siyangzhang.tradefree;

import java.util.UUID;

/**
 * Created by siyangzhang on 2/17/17.
 */

public class Item {

    private UUID itemId;
    private User user;
    private Photo image;
    private String title;
    private String price;
    private String detail;
    private String type;

    private boolean sold;

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
