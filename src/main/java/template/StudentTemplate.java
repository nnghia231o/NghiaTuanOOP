/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package template;

import javafx.scene.control.Alert;
import pojo.Student;


/**
 *
 * @author pc
 */
public abstract class StudentTemplate {

    public final void execute(Student student) {

        if (!validate(student)) {
            showMessage("Dữ liệu không hợp lệ!");
            return;
        }

        boolean result = process(student);

        if (result) {
            showMessage("Thành công!");
        } else {
            showMessage("Thất bại!");
        }
    }

    protected boolean validate(Student student) {
        return student != null
                && student.getName() != null
                && !student.getName().isBlank()
                && student.getAge() > 0
                && student.getMajor() != null;
    }

    protected abstract boolean process(Student student);

    protected void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
