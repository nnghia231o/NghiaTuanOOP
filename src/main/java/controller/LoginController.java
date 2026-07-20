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

public class LoginController implements Initializable {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    private LoginService loginService = new LoginService();
    
    @FXML
    private void btnLogin_Click() { // SK CLICK nút

        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đầy đủ thông tin!");
            alert.show();

            return;
        }

        if (loginService.login(username, password)) {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/mycompany/nghiatuanoop/student.fxml"));

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Student Management");
                stage.show();

                Stage currentStage = (Stage) txtUsername.getScene().getWindow();
                currentStage.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Sai tài khoản hoặc mật khẩu!");
            alert.show();
        }
    }
        
    @FXML
    private void btnExit_Click() {

        System.exit(0);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
}