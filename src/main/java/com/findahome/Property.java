package com.findahome;

public class Property {
    private String name;
    private String location;
    private String price;
    private String imageUrl;
    private boolean verified;
    private String tag;
    private String beds;
    private String baths;
    private String sqft;

    public Property(String name, String location, String price, String imageUrl, boolean verified, String tag) {
        this(name, location, price, imageUrl, verified, tag, "2 Beds", "2 Baths", "1,200 sqft");
    }

    public Property(String name, String location, String price, String imageUrl, boolean verified, String tag,
            String beds, String baths, String sqft) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.imageUrl = imageUrl;
        this.verified = verified;
        this.tag = tag;
        this.beds = beds;
        this.baths = baths;
        this.sqft = sqft;
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

    public String getBeds() {
        return beds;
    }

    public String getBaths() {
        return baths;
    }

    public String getSqft() {
        return sqft;
    }
}
