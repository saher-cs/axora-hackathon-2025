package com.axora.communityskillexchange.servlet;

import com.axora.communityskillexchange.dao.*;
import com.axora.communityskillexchange.model.*;
import com.axora.communityskillexchange.util.PasswordUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.sql.SQLIntegrityConstraintViolationException;

public class HomeServlet extends HttpServlet {
    UserDao userDao = new UserDao();
    ListingDao listingDao = new ListingDao();
    InterestDao interestDao = new InterestDao();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            switch(action) {
                case "register": handleRegister(req,resp); break;
                case "login": handleLogin(req,resp); break;
                case "createListing": handleCreateListing(req,resp); break;
                case "expressInterest": handleExpressInterest(req,resp); break;
                default: resp.getWriter().println("Unknown action!");
            }
        } catch(Exception e) {
            resp.getWriter().println("Error: "+e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            switch(action) {
                case "dashboard": handleDashboard(req,resp); break;
                case "viewListing": handleViewListing(req,resp); break;
                default: resp.getWriter().println("Unknown action!");
            }
        } catch(Exception e) {
            resp.getWriter().println("Error: "+e.getMessage());
        }
    }

    // ========== HANDLE REGISTER ==========
    private void handleRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String phone = req.getParameter("phone");
            String city = req.getParameter("city");
            String bio = req.getParameter("bio");

            // Input validation
            if(name==null || name.isEmpty()) { resp.getWriter().println("Name required!"); return; }
            if(email==null || !email.matches("\\S+@\\S+\\.\\S+")) { resp.getWriter().println("Valid email required!"); return; }
            if(password==null || password.length()<6) { resp.getWriter().println("Password must be at least 6 characters!"); return; }

            User u = new User();
            u.setName(name); u.setEmail(email);
            u.setPasswordHash(PasswordUtil.hash(password));
            u.setPhone(phone); u.setCity(city); u.setBio(bio);

            try {
                userDao.create(u);
                resp.getWriter().println("User Registered Successfully!");
            } catch(SQLIntegrityConstraintViolationException ex) {
                resp.getWriter().println("Email already exists!");
            }
        } catch(Exception e) {
            resp.getWriter().println("Registration error: "+e.getMessage());
        }
    }

    // ========== HANDLE LOGIN ==========
    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            if(email==null || password==null) { resp.getWriter().println("Email and password required!"); return; }

            User u = userDao.login(email,password);
            if(u!=null) {
                req.getSession().setAttribute("user", u);
                resp.getWriter().println("Login Success! Welcome "+u.getName());
            } else {
                resp.getWriter().println("Invalid Credentials");
            }
        } catch(Exception e) {
            resp.getWriter().println("Login error: "+e.getMessage());
        }
    }

    // ========== HANDLE CREATE LISTING ==========
    private void handleCreateListing(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            HttpSession session = req.getSession(false);
            if(session==null || session.getAttribute("user")==null) { resp.getWriter().println("Login required!"); return; }

            String type = req.getParameter("type");
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String city = req.getParameter("city");
            String price = req.getParameter("price");

            // Input validation
            if(type==null || type.isEmpty()) { resp.getWriter().println("Type required!"); return; }
            if(title==null || title.isEmpty()) { resp.getWriter().println("Title required!"); return; }

            User u = (User) session.getAttribute("user");
            Listing l = new Listing();
            l.setUserId(u.getId()); l.setType(type); l.setTitle(title);
            l.setDescription(description); l.setCity(city); l.setPrice(price);

            listingDao.create(l);
            resp.getWriter().println("Listing Created Successfully!");
        } catch(Exception e) {
            resp.getWriter().println("Create listing error: "+e.getMessage());
        }
    }

    // ========== HANDLE EXPRESS INTEREST ==========
    private void handleExpressInterest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            HttpSession session = req.getSession(false);
            if(session==null || session.getAttribute("user")==null) { resp.getWriter().println("Login required!"); return; }

            String listingIdStr = req.getParameter("listingId");
            String message = req.getParameter("message");

            if(listingIdStr==null || listingIdStr.isEmpty()) { resp.getWriter().println("Listing ID required!"); return; }
            if(message==null || message.isEmpty()) { resp.getWriter().println("Message required!"); return; }

            int listingId = Integer.parseInt(listingIdStr);
            Listing l = listingDao.findById(listingId);
            if(l==null) { resp.getWriter().println("Listing not found!"); return; }

            Interest i = new Interest();
            User u = (User) session.getAttribute("user");
            i.setFromUserId(u.getId());
            i.setListingId(listingId);
            i.setMessage(message);

            interestDao.create(i);
            resp.getWriter().println("Interest Expressed Successfully!");
        } catch(NumberFormatException e) {
            resp.getWriter().println("Invalid Listing ID!");
        } catch(Exception e) {
            resp.getWriter().println("Express interest error: "+e.getMessage());
        }
    }

    // ========== HANDLE DASHBOARD ==========
    private void handleDashboard(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            HttpSession session = req.getSession(false);
            if(session==null || session.getAttribute("user")==null) { resp.getWriter().println("Login required!"); return; }

            User u = (User) session.getAttribute("user");
            List<Listing> myListings = listingDao.listByUser(u.getId());
            List<Interest> myInterests = interestDao.listByOwner(u.getId());

            resp.getWriter().println("=== Your Listings ===");
            for(Listing l : myListings) {
                resp.getWriter().println("ID:"+l.getId()+" | "+l.getTitle()+" | "+l.getType()+" | "+l.getCity());
            }

            resp.getWriter().println("\n=== Interests Received ===");
            for(Interest i : myInterests) {
                resp.getWriter().println("Listing ID: "+i.getListingId()+" | From User ID: "+i.getFromUserId()+" | Msg: "+i.getMessage());
            }
        } catch(Exception e) {
            resp.getWriter().println("Dashboard error: "+e.getMessage());
        }
    }

    // ========== HANDLE VIEW LISTING ==========
    private void handleViewListing(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String idStr = req.getParameter("id");
            if(idStr==null || idStr.isEmpty()) { resp.getWriter().println("Listing ID required!"); return; }

            int id = Integer.parseInt(idStr);
            Listing l = listingDao.findById(id);
            if(l!=null) {
                resp.getWriter().println("Title: "+l.getTitle()+" | Type: "+l.getType()+" | Description: "+l.getDescription());
            } else {
                resp.getWriter().println("Listing not found");
            }
        } catch(NumberFormatException e) {
            resp.getWriter().println("Invalid Listing ID!");
        } catch(Exception e) {
            resp.getWriter().println("View listing error: "+e.getMessage());
        }
    }
}
