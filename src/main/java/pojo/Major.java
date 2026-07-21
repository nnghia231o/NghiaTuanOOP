/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author USER
 */
public class Major {
    private int majorID;
    private String majorName;

    public Major(int majorID, String majorName) {
        this.majorID = majorID;
        this.majorName = majorName;
    }

    public Major() {
    }

    public int getMajorID() {
        return majorID;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorID(int majorID) {
        this.majorID = majorID;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    //Hiện tên môn học
    @Override
    public String toString() {
        return majorName;
    }
}
