package com.axora.communityskillexchange.model;

public class User {
    private int id;
    private String name, email, passwordHash, phone, city, bio;

    public int getId() { return id; } public void setId(int id) { this.id = id; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; } public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getPhone() { return phone; } public void setPhone(String phone) { this.phone = phone; }
    public String getCity() { return city; } public void setCity(String city) { this.city = city; }
    public String getBio() { return bio; } public void setBio(String bio) { this.bio = bio; }
}
