module com.mycompany.nghiatuanoop {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.nghiatuanoop to javafx.fxml;
    exports com.mycompany.nghiatuanoop;
}
