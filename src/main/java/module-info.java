module com.example.librarymanagenment {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens com.example.librarymanagenment to javafx.fxml;
    exports com.example.librarymanagenment;
}