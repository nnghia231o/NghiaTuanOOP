/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
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

        int age = Integer.parseInt(txtAge.getText().trim());

        String gender = rdoMale.isSelected() ? "Male" : "Female";

        Major major = cboMajor.getValue();

        Student student = new Student(
                null,
                name,
                age,
                gender,
                major
        );

        if (studentService.addStudent(student)) {

            loadStudent();

        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         loadMajor();
         loadStudent();
         loadForm();
    }    
    
}
