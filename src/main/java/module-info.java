module com.mycompany.nghiatuanoop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.nghiatuanoop to javafx.fxml;
    exports com.mycompany.nghiatuanoop;
}
