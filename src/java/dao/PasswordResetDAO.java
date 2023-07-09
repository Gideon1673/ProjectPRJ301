/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.PasswordReset;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DTS
 */
public class PasswordResetDAO extends DBConnect {

    /**
     * Add a PasswordReset object into DB
     *
     * @param pwdR
     * @return
     */
    public int addPasswordReset(PasswordReset pwdR) {
        String sql = "INSERT INTO [PasswordReset]\n"
                + "           ([email]\n"
                + "           ,[token]\n"
                + "           ,[expireDate])\n"
                + "     VALUES (?, ?, ?);";
        int n = 0;
        System.out.println(pwdR);
        try {
            PreparedStatement pre = connect.prepareStatement(sql);
            pre.setString(1, pwdR.getEmail());
            pre.setString(2, pwdR.getToken());
//            pre.setString(3, pwdR.getDate().toString()); // xem xet chuyen thanh setString()
            pre.setObject(3, pwdR.getDate());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
            Logger.getLogger(PasswordResetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public PasswordReset getPwdResetByEmail(String email) {
        String sql = "SELECT * FROM PasswordReset WHERE email = ?;";
        PreparedStatement pre;
        PasswordReset pwd = null;
        try {
            pre = connect.prepareStatement(sql);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String token = rs.getString(2);
                System.out.println("Date get from DB: " + rs.getString(3));
                LocalDateTime time = LocalDateTime.parse(rs.getString(3));

                pwd = new PasswordReset(email, token, time);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PasswordResetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pwd;
    }

    /**
     * Check whether this token is valid (exist) in the DB
     *
     * @param token
     * @return
     */
    public boolean checkValidToken(String token) {
        String sql = "SELECT expireDate FROM PasswordReset WHERE token = ?;";
        try {
            PreparedStatement pre = connect.prepareStatement(sql);
            pre.setString(1, token);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) { // if token exist, compare time to check expire
//                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
//                LocalDateTime localDateTime = LocalDateTime.now();
                Timestamp timestamp = rs.getTimestamp(1);
                LocalDateTime expire = timestamp.toLocalDateTime();

                // TAI SAO DONG DUOI LAI LOI NHI???
//                LocalDateTime expire = LocalDateTime.parse(rs.getString(1), DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss.SSS"));
                LocalDateTime now = LocalDateTime.now();
                return expire.isAfter(now);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PasswordResetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * @param token
     * @return 
     */
    public String getEmailByToken(String token) {
        String sql = "SELECT email FROM PasswordReset WHERE token LIKE ?;";
        try {
            PreparedStatement pre = connect.prepareStatement(sql);
            pre.setString(1, token);
            ResultSet rs = pre.executeQuery();
            if(rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PasswordResetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
