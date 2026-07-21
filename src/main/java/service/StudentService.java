package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pojo.Major;
import pojo.Student;
import singleton.ConnectionSingleton;

public class StudentService {

    //======================
    // Lấy danh sách sinh viên
    //======================
    public List<Student> getAllStudents() {

        List<Student> list = new ArrayList<>();

        Connection conn = ConnectionSingleton.getInstance().connect();

        String sql = "SELECT s.student_id, s.full_name, s.age, s.gender, "
                   + "m.major_id, m.major_name "
                   + "FROM Student s "
                   + "JOIN Major m ON s.major_id = m.major_id";

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Major major = new Major(
                        rs.getInt("major_id"),
                        rs.getString("major_name")
                );

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

    //======================
    // Thêm sinh viên
    //======================
    public boolean addStudent(Student student) {

        try {

            Connection conn = ConnectionSingleton.getInstance().connect();

            String sql = "INSERT INTO Student(full_name, age, gender, major_id) "
                       + "VALUES(?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getGender());
            ps.setInt(4, student.getMajor().getMajorID());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateStudent(Student s) {

        Connection conn = ConnectionSingleton.getInstance().connect();

            String sql = "UPDATE Student "
               + "SET full_name = ?, age = ?, gender = ?, major_id = ? "
               + "WHERE student_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, s.getName());
            ps.setInt(2, s.getAge());
            ps.setString(3, s.getGender());
            ps.setInt(4, s.getMajor().getMajorID());
            ps.setString(5, s.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
        public boolean deleteStudent(String id) {

        Connection conn = ConnectionSingleton.getInstance().connect();

        String sql = "DELETE FROM Student WHERE student_id = ?";

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}