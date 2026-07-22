/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package template;

import pojo.Student;
import service.StudentService;

/**
 *
 * @author pc
 */
public class AddStudentTemplate extends StudentTemplate {

    private StudentService service = new StudentService();

    @Override
    protected boolean process(Student student) {
        return service.addStudent(student);
    }
}
