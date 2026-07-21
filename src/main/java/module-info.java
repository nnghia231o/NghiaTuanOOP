module com.mycompany.nghiatuanoop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.mycompany.nghiatuanoop to javafx.fxml;
    opens controller to javafx.fxml;
    opens pojo to javafx.base;
    
    exports com.mycompany.nghiatuanoop;
}
