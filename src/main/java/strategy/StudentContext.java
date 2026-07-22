/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import java.util.List;
import pojo.Student;

/**
 *
 * @author pc
 */
public class StudentContext {
    private StudentStrategy strategy;

    public StudentContext(StudentStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Student> executeStrategy(List<Student> students) {
        return strategy.execute(students);
    }
}
