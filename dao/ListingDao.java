package com.axora.communityskillexchange.dao;

import com.axora.communityskillexchange.model.Listing;
import com.axora.communityskillexchange.util.DbUtil;
import java.sql.*;
import java.util.*;

public class ListingDao {
    public void create(Listing l) throws Exception {
        String sql = "INSERT INTO listings(user_id,type,title,description,city,price) VALUES(?,?,?,?,?,?)";
        try(Connection c = DbUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, l.getUserId());
            ps.setString(2, l.getType());
            ps.setString(3, l.getTitle());
            ps.setString(4, l.getDescription());
            ps.setString(5, l.getCity());
            ps.setString(6, l.getPrice());
            ps.executeUpdate();
        }
    }

    public Listing findById(int id) throws Exception {
        String sql = "SELECT * FROM listings WHERE id=?";
        try(Connection c = DbUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return map(rs);
        }
        return null;
    }

    public List<Listing> listAll() throws Exception {
        String sql = "SELECT * FROM listings ORDER BY id DESC";
        List<Listing> list = new ArrayList<>();
        try(Connection c = DbUtil.getConnection(); Statement st = c.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) list.add(map(rs));
        }
        return list;
    }

    public List<Listing> listByUser(int userId) throws Exception {
        String sql = "SELECT * FROM listings WHERE user_id=? ORDER BY id DESC";
        List<Listing> list = new ArrayList<>();
        try(Connection c = DbUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) list.add(map(rs));
        }
        return list;
    }

    private Listing map(ResultSet rs) throws Exception {
        Listing l = new Listing();
        l.setId(rs.getInt("id"));
        l.setUserId(rs.getInt("user_id"));
        l.setType(rs.getString("type"));
        l.setTitle(rs.getString("title"));
        l.setDescription(rs.getString("description"));
        l.setCity(rs.getString("city"));
        l.setPrice(rs.getString("price"));
        return l;
    }
}
