package com.findahome;

public class Property {
    private String name;
    private String location;
    private String price;
    private String imageUrl;
    private boolean verified;
    private String tag;

    public Property(String name, String location, String price, String imageUrl, boolean verified, String tag) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.imageUrl = imageUrl;
        this.verified = verified;
        this.tag = tag;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getTag() {
        return tag;
    }
}
