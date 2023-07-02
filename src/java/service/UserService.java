/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.UserDAO;
import entity.User;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author DTS
 */
public class UserService {

    private static final UserDAO userDao = new UserDAO();

    /**
     *
     * @return
     */
    public boolean userLogin(String username, String password) {
        return userDao.validate(username, password);
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
     * @return byte[16] array filled in with random bytes
     */
    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        
        return salt;
    }

    /**
     * Register a user.
     *
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

        User newUser = new User(userDao.getLastID(), username, email, null, password, null);
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
