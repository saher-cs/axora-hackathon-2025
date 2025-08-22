package com.axora.communityskillexchange.model;

public class Interest {
    private int id, listingId, fromUserId;
    private String message, status;

    public int getId() { return id; } public void setId(int id) { this.id = id; }
    public int getListingId() { return listingId; } public void setListingId(int listingId) { this.listingId = listingId; }
    public int getFromUserId() { return fromUserId; } public void setFromUserId(int fromUserId) { this.fromUserId = fromUserId; }
    public String getMessage() { return message; } public void setMessage(String message) { this.message = message; }
    public String getStatus() { return status; } public void setStatus(String status) { this.status = status; }
}
