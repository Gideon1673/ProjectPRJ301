package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author DTS
 */
public class DBConnect {

    protected Connection connect = null;

    public DBConnect() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            System.out.println(System.getProperty("user.dir"));
            input = new FileInputStream("C:\\Users\\DTS\\Documents\\NetBeansProjects\\Project-Final\\web\\config\\config.properties");

            // load properties file contains username and password
            prop.load(input);
            
            String dbURL = prop.getProperty("database.url");
            String dbDriver = prop.getProperty("database.driver");
            String dbUsername = prop.getProperty("database.username");
            String dbPassword = prop.getProperty("database.password");

            Class.forName(dbDriver);
            connect = DriverManager.getConnection(dbURL, dbUsername, dbPassword);            

            System.out.println("Connected successfully");
        } catch (ClassNotFoundException | SQLException | FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Execute SQL Statement and get back the data
     *
     * @param sqlStatement SQL Statement to be executed
     * @return ResultSet object contains data
     */
    public ResultSet getData(String sqlStatement) {
        ResultSet rs = null;
        Statement statement = null;

        try {
            statement = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery(sqlStatement);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

//    public DBConnect(String url, String username, String password) {
//        try {
//            Class.forName(DB_DRIVER);
//            connect = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
//            System.out.println("Connected");
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
