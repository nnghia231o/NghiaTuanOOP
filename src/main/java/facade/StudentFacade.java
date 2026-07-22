package facade;

import decorator.StudentValidationDecorator;
import java.util.List;
import pojo.Major;
import pojo.Student;
import service.MajorService;
import service.StudentService;

public class StudentFacade {
    private StudentService studentService = new StudentService();
    private MajorService majorService = new MajorService();
    private StudentValidationDecorator decorator = new StudentValidationDecorator(studentService);

    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    public List<Major> getAllMajor() {
        return majorService.getAllMajor();
    }

    public boolean addStudent(Student student) {
        return decorator.addStudent(student);
    }

    public boolean updateStudent(Student student) {
        return studentService.updateStudent(student);
    }

    public boolean deleteStudent(String id) {
        return studentService.deleteStudent(id);
    }

}