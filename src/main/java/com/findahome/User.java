package com.findahome;

public class User {
    private int id;
    private String username;
    private String password;
    private String fullName;
    private String role;
    private String avatarUrl;
    private boolean verified;
    private String memberSince;
    private double profileStrength; // 0.0 to 1.0

    public User(String username, String fullName, String role, String avatarUrl, boolean verified, String memberSince,
            double profileStrength) {
        this.username = username;
        this.fullName = fullName;
        this.role = role;
        this.avatarUrl = avatarUrl;
        this.verified = verified;
        this.memberSince = memberSince;
        this.profileStrength = profileStrength;
        // Password default or ignored for now
    }

    public User(int id, String username, String fullName, String role, String avatarUrl, boolean verified,
            String memberSince, double profileStrength) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.role = role;
        this.avatarUrl = avatarUrl;
        this.verified = verified;
        this.memberSince = memberSince;
        this.profileStrength = profileStrength;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRole() {
        return role;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getMemberSince() {
        return memberSince;
    }

    public double getProfileStrength() {
        return profileStrength;
    }

    public void setProfileStrength(double profileStrength) {
        this.profileStrength = profileStrength;
    }
}
