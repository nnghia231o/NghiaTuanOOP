/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import pojo.Major;
import service.MajorService;


/**
 * FXML Controller class
 *
 * @author USER
 */
public class StudentController implements Initializable {

  @FXML
    private ComboBox<Major> cboMajor;

    @FXML
    private TableView<?> tvInformation;

    private MajorService majorService = new MajorService();
    
    private void loadMajor() {
        //Chuyển List thành obser để Java sử dụng
        ObservableList<Major> list =FXCollections.observableArrayList(majorService.getAllMajor());
        //đưa dữ liệu lên combobox
        cboMajor.setItems(list);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         loadMajor();
    }    
    
}
