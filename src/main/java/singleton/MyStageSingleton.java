package singleton;

import com.mycompany.nghiatuanoop.App;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


//// Áp dụng Singleton Pattern để chỉ tạo một Stage(cửa sổ) duy nhất
public class MyStageSingleton {
    private static MyStageSingleton instance;
    private final Stage stage;

    //Thiết lập Stage mặc định
    private MyStageSingleton() {
        this.stage = new Stage();
        this.stage.setTitle("Student Management");
    }

    //Lấy 1 đối tượng MySingleton duy nhất
    public static MyStageSingleton getInstance() {
        if (instance == null) {
            instance = new MyStageSingleton();
        }
        return instance;
    }

    // Hiển thị giao diện từ file FXML
    public void showStage(String fxml) {
        if (!this.stage.isShowing()) {
            try {
                Scene scene = new Scene(
                        new FXMLLoader(App.class.getResource(fxml + ".fxml")).load()
                );

                this.stage.setScene(scene);
                this.stage.show();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}