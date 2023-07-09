/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DTS
 */
public class UserDAO extends DBConnect {

    /**
     * Return user object corresponding to id
     *
     * @param id
     * @return
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
                byte[] salt = rs.getBytes(6);
                byte[] passwordHashed = rs.getBytes(7);
                String city = rs.getString(8);

                user = new User(id, fullname, username, email, phone, salt, passwordHashed, city);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    /**
     * Get User object by username
     *
     * @param username
     * @return
     */
    public User getUserByUsername(String username) {
        String sqlStatement = "SELECT * FROM [User] WHERE username = '" + username + "';";
        ResultSet rs = getData(sqlStatement);
        User user = null;
        try {
            if (rs.next()) {
                int id = rs.getInt(1);
                String fullname = rs.getString(2);
                String email = rs.getString(4);
                String phone = rs.getString(5);
                byte[] salt = rs.getBytes(6);
                byte[] passwordHashed = rs.getBytes(7);
                String city = rs.getString(8);

                user = new User(id, fullname, username, email, phone, salt, passwordHashed, city);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    /**
     * Get User by username
     *
     * @param email
     * @return User object, or null if there is no user with email provided
     */
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM [User] WHERE email = ?;";
        User user = null;
        try {
            PreparedStatement pre = connect.prepareStatement(sql);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(1);
                String fullname = rs.getString(2);
                String username = rs.getString(3);

                String phone = rs.getString(5);
                byte[] salt = rs.getBytes(6);
                byte[] passwordHashed = rs.getBytes(7);
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
            pre.setBytes(6, user.getSalted());
            System.out.println(Arrays.toString(user.getSalted()));
            pre.setBytes(7, user.getPasswordHashed());
            pre.setString(8, user.getCity());

            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getCause());
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
     * DEPRECATED
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
     * Get salt of account by username
     *
     * @param username
     * @return salt String, or null if error
     */
    public byte[] getUserSalt(String username) {
        String sqlStatement = "SELECT salt FROM [User] WHERE username LIKE ?;";
        try {
            PreparedStatement pre = connect.prepareStatement(sqlStatement);

            pre.setString(1, username);

            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                return rs.getBytes(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public byte[] getHashedPassword(String username) {
        String sqlStatement = "SELECT pwd_hashed FROM [User] WHERE username LIKE ?;";
        try {
            PreparedStatement pre = connect.prepareStatement(sqlStatement);

            pre.setString(1, username);

            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                return rs.getBytes(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
                int id = rs.getInt(1);
                String fullname = rs.getString(2);
                String username = rs.getString(3);
                String email = rs.getString(4);
                String phone = rs.getString(5);
                byte[] salt = rs.getBytes(6);
                byte[] passwordHashed = rs.getBytes(7);
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
     * Get user role's id corresponding to username.
     *
     * @param username
     * @return role id. -1 if there is no username in DB
     */
    public int getUserRole(String username) {
        System.out.println("Username: " + username);
        String sqlStatement = "SELECT b.role_id FROM \n"
                + "[User] a JOIN UserRole b ON a.id = b.user_id \n"
                + "WHERE username LIKE ?;";

        try {
            PreparedStatement pre = connect.prepareStatement(sqlStatement);

            pre.setString(1, username);

            ResultSet rs = pre.executeQuery();

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
     * @param user
     * @return
     */
    public int updateUser(User user) {
        String sql = "UPDATE [User]\n"
                + "   SET [fullName] = ?,[username] = ?,[email] = ?,[phone] = ?,[city] = ?\n"
                + " WHERE id = ?;";
        int n = 0;

        try {
            PreparedStatement pre = connect.prepareStatement(sql);
            pre.setString(1, user.getFullname());
            pre.setString(2, user.getUsername());
            pre.setString(3, user.getEmail());
            pre.setString(4, user.getPhone());
            pre.setString(5, user.getCity());
            pre.setInt(6, user.getId());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Update user error: " + ex.getMessage());
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    /**
     * Get salt of account by email
     *
     * @param email
     * @return salt String, or null if error
     */
    public byte[] getUserSaltByEmail(String email) {
        String sqlStatement = "SELECT salt FROM [User] WHERE email LIKE ?;";
        try {
            PreparedStatement pre = connect.prepareStatement(sqlStatement);

            pre.setString(1, email);

            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                return rs.getBytes(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param email email of account to update
     * @param password byte[] array contains hashed password
     * @return 
     */
    public int updatePassword(String email, byte[] password) {
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [pwd_hashed] = ?\n"
                + " WHERE email LIKE ?;";
        int n = 0;
        try {
            PreparedStatement pre = connect.prepareStatement(sql);
            pre.setBytes(1, password);
            pre.setString(2, email);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

//    public static void main(String[] args) {
//        UserDAO dao = new UserDAO();
//        ResultSet res = dao.getData("select * from [User] where 1<0");
//        ResultSetMetaData rsmd;
//        try {
//            rsmd = res.getMetaData();
//
//            System.out.println(rsmd.getColumnType(1));
//            System.out.println(rsmd.getColumnLabel(1));
//            System.out.println(rsmd.getColumnDisplaySize(1));
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
