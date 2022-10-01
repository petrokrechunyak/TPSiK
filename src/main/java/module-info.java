module subject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;


    opens subject.lab_3 to javafx.fxml;
    exports subject.lab_3;

    opens subject.lab_4 to javafx.fxml;
    exports subject.lab_4;
}