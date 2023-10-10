/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DTS
 */
public class UserRoleDAO extends DBConnect {

    public int addUserRole(User user, int roleID) {
        String SQL = "INSERT INTO [UserRole]\n"
                + "           ([role_id]\n"
                + "           ,[user_id])\n"
                + "     VALUES\n"
                + "           (?, ?);";
        int added = 0;
        PreparedStatement preStmt;
        try {
            preStmt = connect.prepareStatement(SQL);
            preStmt.setInt(1, roleID);
            preStmt.setInt(2, user.getId());
            added = preStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return added;
    }
}
