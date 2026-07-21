/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author USER
 */
public class ConnectionSingleton {
    private static ConnectionSingleton instance;//static là biến dùng chung cho tất cả các đối tượng của một class và thuộc về class đó
    private Connection conn;
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Nạp driver
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ConnectionSingleton() {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost/StudentDB", "root", "Nn@231o@06");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ConnectionSingleton getInstance() {
        if (instance == null)
            instance = new ConnectionSingleton();
        
        return instance;
    }
    
    public Connection connect() {
        return this.conn;
    }
    
    public void close() { // Đóng kết nối
        if (this.conn != null)
            try {
                this.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionSingleton.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}

