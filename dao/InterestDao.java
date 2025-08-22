package com.axora.communityskillexchange.dao;

import com.axora.communityskillexchange.model.Interest;
import com.axora.communityskillexchange.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InterestDao {

    // Insert a new interest
    public void create(Interest i) throws Exception {
        String sql = "INSERT INTO interests(listing_id, from_user_id, message, status) VALUES (?, ?, ?, ?)";
        try (Connection c = DbUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, i.getListingId());
            ps.setInt(2, i.getFromUserId());
            ps.setString(3, i.getMessage());
            ps.setString(4, "pending"); // default status

            int rows = ps.executeUpdate(); // assign return value
            if (rows == 0) throw new Exception("Failed to insert interest!");
        }
    }

    // Fetch all interests for listings owned by a specific user
    public List<Interest> listByOwner(int ownerId) throws Exception {
        String sql = "SELECT i.* FROM interests i " +
                     "JOIN listings l ON i.listing_id = l.id " +
                     "WHERE l.user_id = ? ORDER BY i.id DESC";
        List<Interest> list = new ArrayList<>();
        try (Connection c = DbUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, ownerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    // Helper method to map ResultSet row to Interest object
    private Interest map(ResultSet rs) throws Exception {
        Interest i = new Interest();
        i.setId(rs.getInt("id"));
        i.setListingId(rs.getInt("listing_id"));
        i.setFromUserId(rs.getInt("from_user_id"));
        i.setMessage(rs.getString("message"));
        i.setStatus(rs.getString("status"));
        return i;
    }
}
