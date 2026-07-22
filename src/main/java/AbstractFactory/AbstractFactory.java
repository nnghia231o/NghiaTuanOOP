package abstractfactory;

import pojo.Major;
import pojo.Student;

public abstract class AbstractFactory {

    public abstract Student createStudent(
            String id,
            String name,
            int age,
            String gender,
            Major major);
}