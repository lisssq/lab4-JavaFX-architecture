module com.example.assembly_javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.assembly_javafx to javafx.fxml;
    exports com.example.assembly_javafx;
}