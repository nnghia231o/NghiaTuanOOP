package abstractfactory;

import pojo.Major;
import pojo.Student;

public class EnglishFactory extends AbstractFactory {

    @Override
    public Student createStudent(String id,
            String name,
            int age,
            String gender,
            Major major) {

        return new Student(id, name, age, gender, major);
    }
}