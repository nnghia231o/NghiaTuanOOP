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
import singleton.ConnectionSingleton;

public class MajorService {

   public List<Major> getAllMajor() {

        List<Major> list = new ArrayList<>();

        try {

            Connection conn = ConnectionSingleton.getInstance().connect();

            String sql = "SELECT * FROM Major";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Major major = new Major();

                major.setMajorID(rs.getString("major_id"));
                major.setMajorName(rs.getString("major_name"));

                list.add(major);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
 }
