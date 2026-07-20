/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pojo.Major;
import pojo.Student;
import singleton.ConnectionSingleton;
/**
 *
 * @author USER
 */
public class StudentService {
    public List<Student> getAllStudents() {
    List<Student> list = new ArrayList<>();

    Connection conn = ConnectionSingleton.getInstance().connect();

    String sql = "SELECT * FROM Student";

    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Major major = new Major("major_id","major_name");
            Student s = new Student(
                    rs.getString("student_id"),
                    rs.getString("full_name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    major
            );

            list.add(s);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}
}
