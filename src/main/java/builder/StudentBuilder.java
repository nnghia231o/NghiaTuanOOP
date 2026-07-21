/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package builder;

import pojo.Major;
import pojo.Student;

/**
 *
 * @author USER
 */
// Builder giúp tạo object có nhiều thuộc tính một cách dễ đọc, tránh 
//constructor quá dài và dễ mở rộng.
public class StudentBuilder {
    private String id;
    private String name;
    private int age;
    private String gender;
    private Major major;

    public StudentBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public StudentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public StudentBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public StudentBuilder setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public StudentBuilder setMajor(Major major) {
        this.major = major;
        return this;
    }

    public Student build() {
        return new Student(id, name, age, gender, major);
    }
}
