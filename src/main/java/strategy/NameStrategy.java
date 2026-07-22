/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import java.util.Comparator;
import java.util.List;
import pojo.Student;

/**
 *
 * @author pc
 */
public class NameStrategy implements StudentStrategy {

    @Override
    public List<Student> execute(List<Student> students) {

        students.sort(Comparator.comparing(Student::getName));

        return students;
    }
}
