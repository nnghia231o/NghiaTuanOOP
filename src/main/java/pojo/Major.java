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
    private String majorID;
    private String majorName;

    public Major(String majorID, String majorName) {
        this.majorID = majorID;
        this.majorName = majorName;
    }

    public Major() {
    }

    public String getMajorID() {
        return majorID;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorID(String majorID) {
        this.majorID = majorID;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
    
    
}
