package service;

import singleton.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginService { // Xử lý nghiệp vụ đăng nhập

    public boolean login(String username, String password) { // Kiểm tra tk mk

        try {
            // Lấy getInstance bên ConnecSingleTon rồi lấy hàm connect return về
            Connection conn = ConnectionSingleton.getInstance().connect(); // Lấy kết nối

            String sql = "SELECT * FROM Account WHERE username=? AND password=?"; // KIểm tra tk mk

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery(); // Lưu kết quả vào rs

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace(); // In lỗi
        }

        return false;
    }
}