/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.PasswordResetDAO;
import dao.UserDAO;
import entity.PasswordReset;
import entity.User;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
    private static final PasswordResetDAO passDao = new PasswordResetDAO();
    private static String[] userRoles = {"user", "admin"};

    /**
     *
     * @param username
     * @param password
     * @return true if username and password is correct
     */
    public boolean userLogin(String username, String password) {
        if (!userDao.isUsernameExist(username)) { // if username does not exist --> login failed
            return false;
        } else {
            byte[] salted = userDao.getUserSalt(username);
//            System.out.println("salted: " + Arrays.toString(salted));

            byte[] hashedPassword = hashingPassword(password, salted);
            byte[] realHashed = userDao.getHashedPassword(username);

//            System.out.println("hashed1: " + Arrays.toString(hashedPassword));
//            System.out.println("hashed2: " + Arrays.toString(realHashed));
            return Arrays.equals(realHashed, hashedPassword);
        }
    }

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
//            System.out.println("Password after hash: " + Arrays.toString(hashedPassword));
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

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    /**
     * Get user role by username
     *
     * @param username
     * @return role name
     */
    public String getUserRole(String username) {
//        System.out.println("username: " + username);
        return userRoles[userDao.getUserRole(username) - 1]; // subtract 1 becasue roleID in DB begins from 1
    }

    public int getUserRoleID(String username) {
        return userDao.getUserRole(username);
    }

    public boolean updateUser(User user) {
        if (userDao.updateUser(user) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get user in the DB by ID
     *
     * @param uID
     * @return User object or null if there is no id
     */
    public User getUserByID(int uID) {
        return userDao.getUserByID(uID);
    }

    public void addPasswordReset(PasswordReset pwdR) {
        passDao.addPasswordReset(pwdR);
    }

    /**
     * Check whether this token belongs to this email in DB, and is in time
     *
     * @param token
     * @return
     */
    public boolean checkValidToken(String token) {
        return passDao.checkValidToken(token);
    }

    /**
     *
     * @param newPassword
     * @param token
     */
    public void changeUserPassword(String newPassword, String token) {
        String email = passDao.getEmailByToken(token);
        byte[] salt = userDao.getUserSaltByEmail(email);
        byte[] password = hashingPassword(newPassword, salt);
        userDao.updatePassword(email, password);
    }
}
