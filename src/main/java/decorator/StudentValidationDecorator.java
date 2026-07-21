package decorator;

import pojo.Student;
import service.StudentService;

public class StudentValidationDecorator extends StudentDecorator {

    public StudentValidationDecorator(StudentService studentService) {
        super(studentService);
    }

    @Override
    public boolean addStudent(Student student) {

        if(student.getName().trim().isEmpty()){
            System.out.println("Tên không được rỗng");
            return false;
        }

        if(student.getAge() <= 0){
            System.out.println("Tuổi không hợp lệ");
            return false;
        }

        return super.addStudent(student);

    }

}