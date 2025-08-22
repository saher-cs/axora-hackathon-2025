package com.axora.communityskillexchange.dao;

import com.axora.communityskillexchange.model.User;
import com.axora.communityskillexchange.util.DbUtil;
import com.axora.communityskillexchange.util.PasswordUtil;
import java.sql.*;

public class UserDao {
    public void create(User u) throws Exception {
        String sql = "INSERT INTO users(name,email,password_hash,phone,city,bio) VALUES(?,?,?,?,?,?)";
        try(Connection c = DbUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,u.getName());
            ps.setString(2,u.getEmail());
            ps.setString(3,u.getPasswordHash());
            ps.setString(4,u.getPhone());
            ps.setString(5,u.getCity());
            ps.setString(6,u.getBio());
            ps.executeUpdate();
        }
    }

    public User findByEmail(String email) throws Exception {
        String sql = "SELECT * FROM users WHERE email=?";
        try(Connection c = DbUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return map(rs);
        }
        return null;
    }

    public User login(String email, String plain) throws Exception {
        User u = findByEmail(email);
        if(u != null && PasswordUtil.matches(plain, u.getPasswordHash())) return u;
        return null;
    }

    private User map(ResultSet rs) throws Exception {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setName(rs.getString("name"));
        u.setEmail(rs.getString("email"));
        u.setPasswordHash(rs.getString("password_hash"));
        u.setPhone(rs.getString("phone"));
        u.setCity(rs.getString("city"));
        u.setBio(rs.getString("bio"));
        return u;
    }
}
