/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flyweight;

import java.util.HashMap;
import java.util.Map;
import pojo.Major;

/**
 *
 * @author pc
 */
public class MajorFlyweight {
    private static final Map<Integer, Major> majors = new HashMap<>();

    public static Major getMajor(int id, String name) {

        if (!majors.containsKey(id)) {
            majors.put(id, new Major(id, name));
            System.out.println("Create Major: " + name);
        }

        return majors.get(id);
    }
}
