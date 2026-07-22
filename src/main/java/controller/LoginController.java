package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.LoginService;
import singleton.MyAlertSingleton;
import singleton.MyStageSingleton;

public class LoginController implements Initializable {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    
    //private LoginFacade facade = new LoginFacade();
    //facade sẽ thay phần dưới
    private LoginService loginService = new LoginService();
    
    //Sự kiện Nhấn nút đăng nhập
    @FXML
    private void btnLogin_Click() { 
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        if (username.isEmpty() || password.isEmpty()) {
            MyAlertSingleton.getInstance().showMsg("Vui lòng nhập đầy đủ thông tin!",Alert.AlertType.WARNING);
            return;
        }

        //if (facade.login(username, password)) {
        //facade sẽ thay phần dưới
        if (loginService.login(username, password)) {
            try {
                MyStageSingleton.getInstance().showStage("student");
                Stage currentStage = (Stage) txtUsername.getScene().getWindow();
                currentStage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            MyAlertSingleton.getInstance().showMsg("Sai tài khoản hoặc mật khẩu!",Alert.AlertType.ERROR);
        }
    }
        
    //Sự kiện nhấn nút thoát
    @FXML
    private void btnExit_Click() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}