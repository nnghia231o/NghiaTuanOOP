package decorator;

import pojo.Student;
import service.StudentService;

public class StudentDecorator {
// Lưu StudentService gốc
    protected StudentService studentService;
// Nhận StudentService từ ngoài truyền vào
    public StudentDecorator(StudentService studentService) {
        this.studentService = studentService;
    }

    public boolean addStudent(Student student) {
        return studentService.addStudent(student);// Gọi hàm thật của StudentService
    }

}