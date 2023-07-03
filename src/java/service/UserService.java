/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.UserDAO;
import entity.User;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DTS
 */
public class UserService {

    private static final UserDAO userDao = new UserDAO();

    /**
     *
     * @return true if username and password is correct
     */
    public boolean userLogin(String username, String password) {
        if (!userDao.isUsernameExist(username)) { // if username does not exist --> login failed
            return false;
        } else {
            byte[] salted = userDao.getUserSalt(username);
            System.out.println("salted: " + Arrays.toString(salted));

            byte[] hashedPassword = hashingPassword(password, salted);
            byte[] realHashed = userDao.getHashedPassword(username);

            System.out.println("hashed1: " + Arrays.toString(hashedPassword));
            System.out.println("hashed2: " + Arrays.toString(realHashed));

            return Arrays.equals(realHashed, hashedPassword);
        }
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
//    public boolean userLogin2(String username, String password) {
//        // Generate salt for password
//        byte[] salt = generateSalt();
//        System.out.println(salt.toString());
//        
//    }
    /**
     * Generate salt, helping in password hashing
     *
     * @return byte[16] array filled in with random bytes
     */
    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return salt;
    }

    /**
     * Hashing a password
     *
     * @param password
     * @param salt
     * @return hashed password. Or null if SHA-512 is not supported
     */
    private byte[] hashingPassword(String password, byte[] salt) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            System.out.println("Password after hash: " + Arrays.toString(hashedPassword));
            return hashedPassword;
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Register a user.
     *
     * @param username
     * @param email
     * @param password
     * @param retype_password
     * @return true if registered successfully, false otherwise
     */
    public boolean userRegister(String username, String email, String password, String retype_password) throws Exception {
        // check password
        if (!password.equals(retype_password)) {
            throw new Exception("Password does not match");
        }

        if (userDao.isUsernameExist(username)) {
            throw new Exception("username already existed");
        }

        System.out.println("USERNAME: " + username);

        // Generate salt
        byte[] salt = generateSalt();
        System.out.println(Arrays.toString(salt));
        System.out.println("Salt size: " + salt.length);

        byte[] hashedPassword = hashingPassword(password, salt);
        System.out.println("Password size: " + hashedPassword.length);

        User newUser = new User(userDao.getLastID() + 1, null, username, email, null, salt, hashedPassword, null);
        userDao.addUser(newUser);
        return true;
    }

    public Vector<User> getAllUSers() {
        return userDao.getAllUSers();
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

//    public User getUserByID(int id) {
//        
//    }
//    
//    public int getUserRole(int userID) {
//        User user = get
//    }
//    
    public int getUserRole(String username) {
        return userDao.getUserRole(username);
    }

    
}
