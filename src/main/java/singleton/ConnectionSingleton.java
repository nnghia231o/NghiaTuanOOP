/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author USER
 */
public class ConnectionSingleton {
    private static Connection connection;
    
    private ConnectionSingleton() {
        
    }
    
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/StudentDB",
                        "root",
                        "Thanhtuan3107@");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    
}

