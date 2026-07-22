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

// Áp dụng Singleton Pattern để chỉ tạo 1 kết nối Database
public class ConnectionSingleton {
    //static là biến dùng chung cho tất cả các đối tượng của một class và thuộc về class đó
    private static ConnectionSingleton instance;
    // Đối tượng Singleton dùng chung cho toàn bộ chương trình
    private Connection conn;
    
    // Nạp Driver MySQL khi class được load
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Nạp driver
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // chức năng kết nối đến Database
    private ConnectionSingleton() {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost/StudentDB", "root", "Thanhtuan3107@");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Lấy 1 đối tượng ConnectionSingleton duy nhất
    public static ConnectionSingleton getInstance() {
        if (instance == null)
            instance = new ConnectionSingleton();
        
        return instance;
    }
    
    // Trả về Connection để sử dụng
    public Connection connect() {
        return this.conn;
    }
    
    // Đóng kết nối Database
    public void close() { 
        if (this.conn != null)
            try {
                this.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionSingleton.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}

