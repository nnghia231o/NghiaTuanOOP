/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author USER
 */
public class Student {
    private String id;
    private String name;
    private int age;
    private String gender;
    private Major major;

    public Student() {
    }

    public Student(String id, String name, int age, String gender, Major major) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.major = major;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Major getMajor() {
        return major;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setMajor(Major major) {
        this.major = major;
    } 
}
