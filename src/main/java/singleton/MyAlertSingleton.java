/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singleton;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author pc
 */

// Áp dụng Singleton Pattern để chỉ tạo một đối tượng Alert duy nhất
public class MyAlertSingleton {
    private static MyAlertSingleton instance;
    private final Alert alert;
    
    // Tạo 1 khung thông báo (Alert) mặc định
    private MyAlertSingleton() {
        this.alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Student Management");
        alert.setHeaderText("Student Management");
    }
    
    // Lấy đối tượng Singleton duy nhất
    public static MyAlertSingleton getInstance() {
        if (instance == null)
            instance = new MyAlertSingleton();
        
        return instance;
    }
    
    // Hiển thị thông báo với nội dung truyền vào
    public void showMsg(String content) {
        this.alert.setContentText(content);
        this.alert.show();
    }
    
    // Hiển thị thông báo với loại Alert được truyền vào (Warning, Error, Confirmation,...)
    public Optional<ButtonType> showMsg(String content, Alert.AlertType type) {
        this.alert.setContentText(content);
        this.alert.setAlertType(type);
        return this.alert.showAndWait();
    }
}
