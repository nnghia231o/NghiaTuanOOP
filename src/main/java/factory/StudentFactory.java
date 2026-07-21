/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import pojo.Major;
import pojo.Student;

/**
 *
 * @author USER
 */

//Factory chịu trách nhiệm tạo đối tượng, giúp Controller không phải dùng 
// new Student() trực tiếp, code dễ bảo trì và dễ thay đổi.
public class StudentFactory {
    // static để gọi trực tiếp không cần new StudentFactory()
        public static Student createStudent(
            String id,
            String name,
            int age,
            String gender,
            Major major) {

        return new Student(id, name, age, gender, major);
    }
}
