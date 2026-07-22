/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import builder.StudentBuilder;
import decorator.StudentValidationDecorator;
import facade.StudentFacade;
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
import singleton.MyAlertSingleton;
import singleton.MyStageSingleton;


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

    //private MajorService majorService = new MajorService();
    //private StudentService studentService = new StudentService();
    //private StudentValidationDecorator decorator = new StudentValidationDecorator(studentService); 
    // 3 dòng trên thay cho dong này vì facade
    private StudentFacade facade = new StudentFacade();
    
    
    private void loadMajor() {
        //Chuyển List thành obser để Java sử dụng
        ObservableList<Major> list =FXCollections.observableArrayList(facade.getAllMajor());
        //đưa dữ liệu lên combobox
        cboMajor.setItems(list);
    }
    
    //Đỗ dữ liệu vào Table View
    private void loadStudent(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colMajor.setCellValueFactory(new PropertyValueFactory<>("major"));
        ObservableList<Student> list = FXCollections.observableArrayList(facade.getAllStudents());
        tvInformation.setItems(list);
    }
    
    //Khi nhấn nút đăng xuất sẽ quay lại màn hình đăng nhập
    @FXML
    private void logout(ActionEvent event) {
        try {
            MyStageSingleton.getInstance().showStage("login");
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
    
    //Khi chọn 1 dòng trên Table View sẽ đỗ dữ liệu lên các ô nhập thông tin
    private void loadForm() {
        tvInformation.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, student) -> {
            if (student != null) {
                txtName.setText(student.getName());
                txtAge.setText(String.valueOf(student.getAge()));
                cboMajor.setValue(student.getMajor());
                if (student.getGender().equals("Male")) {
                    rdoMale.setSelected(true);
                } else {
                    rdoFemale.setSelected(true);
                }
            }
        });
    }
    
    //Sự kiện thêm học sinh
    @FXML
    private void btnAdd_Click() {
        //Lấy dữ liệu từ giao diện
        String name = txtName.getText().trim();
        String ageText = txtAge.getText().trim();
        Major major = cboMajor.getValue();
        String gender = rdoMale.isSelected() ? "Male" : "Female";

        // Kiểm tra nhập đủ thông tin
        if (name.isEmpty() || ageText.isEmpty() || major == null|| (!rdoMale.isSelected() && !rdoFemale.isSelected())) {
            //Áp dụng MyAlertSingleton để hiển thị thông báo
            MyAlertSingleton.getInstance().showMsg("Vui lòng nhập đầy đủ thông tin!",Alert.AlertType.WARNING);
            return;
        }
        
        //Kiểm tra tuổi là số
        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            //Áp dụng MyAlertSingleton để hiển thị thông báo
            MyAlertSingleton.getInstance().showMsg("Tuổi phải là số!",Alert.AlertType.ERROR);
            return;
        }
        // Builder Student dùng để tạo đối tượng Student từng bước, thay cho việc gọi trực tiếp new Student()
        Student student = new StudentBuilder()
                .setName(name)
                .setAge(age)
                .setGender(gender)
                .setMajor(major)
                .build();
               
        // Abstract Factory sẽ được áp dụng thay cho Builder Student ở đây
        /*
        AbstractFactory factory = FactoryProducer.getFactory(major.getMajorName());
        Student student = factory.createStudent(
                null,
                name,
                age,
                gender,
                major
        );
        */
        
        // Decorator dùng để
        if (facade.addStudent(student)) {
            ////Áp dụng MyAlertSingleton để hiển thị thông báo
            MyAlertSingleton.getInstance().showMsg("Thêm sinh viên thành công!");
            loadStudent();
            // Xóa dữ liệu trên form
            txtName.clear();
            txtAge.clear();
            cboMajor.getSelectionModel().clearSelection();
            rdoMale.setSelected(false);
            rdoFemale.setSelected(false);
        } else {
            //Áp dụng MyAlertSingleton để hiển thị thông báo
            MyAlertSingleton.getInstance().showMsg("Thêm sinh viên thất bại!",Alert.AlertType.ERROR);
        }
    }
    
    //Sự kiện sửa học sinh
    @FXML
    private void btnUpdate_Click() {
        Student student = tvInformation.getSelectionModel().getSelectedItem();
        if (student == null) {
            //Áp dụng MyAlertSingleton để hiển thị thông báo
            MyAlertSingleton.getInstance().showMsg("Vui lòng nhập đầy đủ thông tin!",Alert.AlertType.WARNING);
            return;
        }

        String name = txtName.getText().trim();
        String ageText = txtAge.getText().trim();
        Major major = cboMajor.getValue();
        String gender = rdoMale.isSelected() ? "Male" : "Female";
        // Kiểm tra nhập đủ thông tin
        if (name.isEmpty() || ageText.isEmpty()|| major == null|| (!rdoMale.isSelected() && !rdoFemale.isSelected())) {
            //Áp dụng MyAlertSingleton để hiển thị thông báo
            MyAlertSingleton.getInstance().showMsg("Vui lòng nhập đầy đủ thông tin!",Alert.AlertType.WARNING);
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            MyAlertSingleton.getInstance().showMsg("Tuổi phải là số!",Alert.AlertType.ERROR);
            return;
        }

        student.setName(name);
        student.setAge(age);
        student.setGender(gender);
        student.setMajor(major);

        if (facade.updateStudent(student)) {
            MyAlertSingleton.getInstance().showMsg("Cập nhật thành công!");
            loadStudent();
            // Xóa dữ liệu trên form
            txtName.clear();
            txtAge.clear();
            cboMajor.getSelectionModel().clearSelection();
            rdoMale.setSelected(false);
            rdoFemale.setSelected(false);
        } else {
            //Áp dụng MyAlertSingleton để hiển thị thông báo
            MyAlertSingleton.getInstance().showMsg("Cập nhật thất bại!",Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnDelete_Click() {
        Student student = tvInformation.getSelectionModel().getSelectedItem();
        if (student == null) {
            MyAlertSingleton.getInstance().showMsg("Vui lòng chọn sinh viên cần xóa!",Alert.AlertType.WARNING);
            return;
        }
        Optional<ButtonType> result = MyAlertSingleton.getInstance().showMsg("Bạn có chắc muốn xóa sinh viên này?",Alert.AlertType.CONFIRMATION);
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (facade.deleteStudent(student.getId())) {
                MyAlertSingleton.getInstance().showMsg("Xóa thành công!");
                loadStudent();
                txtName.clear();
                txtAge.clear();
                cboMajor.getSelectionModel().clearSelection();
                rdoMale.setSelected(false);
                rdoFemale.setSelected(false);
            } else {
                MyAlertSingleton.getInstance().showMsg("Xóa thất bại!",Alert.AlertType.ERROR);
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
