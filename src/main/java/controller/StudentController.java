/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pojo.Major;
import pojo.Student;
import service.MajorService;
import service.StudentService;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


/**
 * FXML Controller class
 *
 * @author USER
 */
public class StudentController implements Initializable {

    @FXML
    private TableView<Student> tvInformation;

    @FXML
    private TableColumn<Student,String> colId;

    @FXML
    private TableColumn<Student,String> colName;

    @FXML
    private TableColumn<Student,Integer> colAge;

    @FXML
    private TableColumn<Student,String> colGender;

    @FXML
    private TableColumn<Student,Major> colMajor;

    @FXML
    private ComboBox<Major> cboMajor;

    private MajorService majorService = new MajorService();
    private StudentService studentService = new StudentService();
    
    private void loadMajor() {
        //Chuyển List thành obser để Java sử dụng
        ObservableList<Major> list =FXCollections.observableArrayList(majorService.getAllMajor());
        //đưa dữ liệu lên combobox
        cboMajor.setItems(list);
    }
    
    private void loadStudent(){

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        colName.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        colAge.setCellValueFactory(
                new PropertyValueFactory<>("age"));

        colGender.setCellValueFactory(
                new PropertyValueFactory<>("gender"));

        colMajor.setCellValueFactory(
                new PropertyValueFactory<>("major"));

        ObservableList<Student> list =
                FXCollections.observableArrayList(
                        studentService.getAllStudents());

        tvInformation.setItems(list);

    }
    
    @FXML
    private void logout(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/mycompany/nghiatuanoop/login.fxml"));

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAge;

    @FXML
    private RadioButton rdoFemale;

    @FXML
    private RadioButton rdoMale;
    
    private void loadForm() {

        tvInformation.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, student) -> {

            if (student != null) {

                txtName.setText(student.getName());

                txtAge.setText(
                        String.valueOf(student.getAge()));

                cboMajor.setValue(student.getMajor());

                if (student.getGender().equals("Male")) {

                    rdoMale.setSelected(true);

                } else {

                    rdoFemale.setSelected(true);

                }
            }
        });
    }
    
    @FXML
    private void btnAdd_Click() {

        String name = txtName.getText().trim();
        String ageText = txtAge.getText().trim();
        Major major = cboMajor.getValue();

        // Kiểm tra nhập đủ thông tin
        if (name.isEmpty() || ageText.isEmpty() || major == null
                || (!rdoMale.isSelected() && !rdoFemale.isSelected())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đầy đủ thông tin!");
            alert.show();
            return;
        }

        int age;

        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Tuổi phải là số!");
            alert.show();
            return;
        }

        String gender = rdoMale.isSelected() ? "Male" : "Female";

        Student student = new Student(
                null,
                name,
                age,
                gender,
                major
        );

        if (studentService.addStudent(student)) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thêm sinh viên thành công!");
            alert.show();

            loadStudent();

            // Xóa dữ liệu trên form
            txtName.clear();
            txtAge.clear();
            cboMajor.getSelectionModel().clearSelection();
            rdoMale.setSelected(false);
            rdoFemale.setSelected(false);

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Thêm sinh viên thất bại!");
            alert.show();
        }
    }
    @FXML
    private void btnUpdate_Click() {

        Student student = tvInformation.getSelectionModel().getSelectedItem();

        if (student == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn sinh viên cần sửa!");
            alert.show();
            return;
        }

        String name = txtName.getText().trim();
        String ageText = txtAge.getText().trim();
        Major major = cboMajor.getValue();

        // Kiểm tra nhập đủ thông tin
        if (name.isEmpty() || ageText.isEmpty()
                || major == null
                || (!rdoMale.isSelected() && !rdoFemale.isSelected())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đầy đủ thông tin!");
            alert.show();
            return;
        }

        int age;

        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Tuổi phải là số!");
            alert.show();
            return;
        }

        String gender = rdoMale.isSelected() ? "Male" : "Female";

        student.setName(name);
        student.setAge(age);
        student.setGender(gender);
        student.setMajor(major);

        if (studentService.updateStudent(student)) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Cập nhật thành công!");
            alert.show();

            loadStudent();

            // Xóa dữ liệu trên form
            txtName.clear();
            txtAge.clear();
            cboMajor.getSelectionModel().clearSelection();
            rdoMale.setSelected(false);
            rdoFemale.setSelected(false);

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Cập nhật thất bại!");
            alert.show();
        }
    }

    @FXML
    private void btnDelete_Click() {

        Student student = tvInformation.getSelectionModel().getSelectedItem();

        if (student == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn sinh viên cần xóa!");
            alert.show();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setHeaderText(null);
        confirm.setContentText("Bạn có chắc muốn xóa sinh viên này?");

        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            if (studentService.deleteStudent(student.getId())) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Xóa thành công!");
                alert.show();

                loadStudent();

                txtName.clear();
                txtAge.clear();
                cboMajor.getSelectionModel().clearSelection();
                rdoMale.setSelected(false);
                rdoFemale.setSelected(false);

            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Xóa thất bại!");
                alert.show();
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadMajor();
        loadStudent();
        loadForm();
}
}
