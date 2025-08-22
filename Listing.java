package com.axora.communityskillexchange.model;

public class Listing {
    private int id, userId;
    private String type, title, description, city, price;

    public int getId() { return id; } public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; } public void setUserId(int userId) { this.userId = userId; }
    public String getType() { return type; } public void setType(String type) { this.type = type; }
    public String getTitle() { return title; } public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; } public void setDescription(String description) { this.description = description; }
    public String getCity() { return city; } public void setCity(String city) { this.city = city; }
    public String getPrice() { return price; } public void setPrice(String price) { this.price = price; }
}
