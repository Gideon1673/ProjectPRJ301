/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DTS
 */
public class UserDAO extends DBConnect {

    /**
     *
     */
    public User getUserByID(int id) {
        String sqlStatement = "SELECT * FROM [User] WHERE id = " + id + ";";
        ResultSet rs = getData(sqlStatement);
        User user = null;
        try {
            if (rs.next()) { // if there exists user with id
                String fullname = rs.getString(2);
                String username = rs.getString(3);
                String email = rs.getString(4);
                String phone = rs.getString(5);
                String salt = rs.getString(6);
                String passwordHashed = rs.getString(7);
                String city = rs.getString(8);

                user = new User(id, fullname, username, email, phone, salt, passwordHashed, city);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public User getUserByUsername(String username) {
        String sqlStatement = "SELECT * FROM [User] WHERE username = '" + username + "';";
        ResultSet rs = getData(sqlStatement);
        User user = null;
        try {
            if (rs.next()) {
                int id  = rs.getInt(1);
                String fullname = rs.getString(2);
                String email = rs.getString(4);
                String phone = rs.getString(5);
                String salt = rs.getString(6);
                String passwordHashed = rs.getString(7);
                String city = rs.getString(8);

                user = new User(id, fullname, username, email, phone, salt, passwordHashed, city);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    /**
     * Add an user to DB
     *
     * @param user
     */
    public void addUser(User user) {
        String sqlStatement = "INSERT INTO [User](id, fullName, username, email, phone, salt, pwd_hashed, city) VALUES\n"
                + "(?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement pre;
        try {
            pre = connect.prepareStatement(sqlStatement);
            pre.setInt(1, user.getId());
            pre.setString(2, user.getFullname());
            pre.setString(3, user.getUsername());
            pre.setString(4, user.getEmail());
            pre.setString(5, user.getPhone());
            pre.setBytes(6, user.getSalted().getBytes());
            pre.setBytes(7, user.getPasswordHashed().getBytes());
            pre.setString(8, user.getCity());

            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Get last user id
     *
     * @return the last ID in the DB, or -1 if DB is empty
     */
    public int getLastID() {
        String sqlStatement = "SELECT TOP(1) id FROM [User] ORDER BY id DESC;";

        ResultSet rs = getData(sqlStatement);
        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public boolean validate(String username, String password) {
//      SQL Server does not take into account whitespace in WHERE, so we must use LIKE for password
        String sqlStatement = "SELECT id FROM [User] WHERE username = ? AND pwd_hashed LIKE ?;";
//        System.out.println(username.length() + " " + password.length());
        boolean success = false;

        try {
            PreparedStatement preStatement = connect.prepareStatement(sqlStatement);
            preStatement.setString(1, username);
            preStatement.setString(2, password);

            ResultSet rs = preStatement.executeQuery();

            success = rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success;
    }

    /**
     * Check whether an username exists in the DB
     *
     * @param username
     * @return true if username already exist, false if it's not
     */
    public boolean isUsernameExist(String username) {
        String sqlStatement = "SELECT id FROM [User] WHERE username LIKE '" + username + "';";
        ResultSet rs = getData(sqlStatement);
        try {
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Vector<User> getAllUSers() {
        String sqlStatement = "SELECT * FROM [User];";
        ResultSet rs = getData(sqlStatement);
        Vector<User> users = new Vector<>();
        try {
            while (rs.next()) {
                int id  = rs.getInt(1);
                String fullname = rs.getString(2);
                String username = rs.getString(3);
                String email = rs.getString(4);
                String phone = rs.getString(5);
                String salt = rs.getString(6);
                String passwordHashed = rs.getString(7);
                String city = rs.getString(8);

                User user = new User(id, fullname, username, email, phone, salt, passwordHashed, city);
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    /**
     * Get user role corresponding to username.
     * @param username
     * @return role id. -1 if there is no username in DB
     */
    public int getUserRole(String username) {
        String sqlStatement = "SELECT b.role_id FROM \n"
                + "[User] a JOIN UserRole b ON a.id = b.user_id \n"
                + "WHERE username LIKE ?;";
        
        try {
            PreparedStatement pre = connect.prepareStatement(sqlStatement);
            
            pre.setString(1, username);
            
            ResultSet rs = pre.executeQuery();
            
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
        
    }
}
