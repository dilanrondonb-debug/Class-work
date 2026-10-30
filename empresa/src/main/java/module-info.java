module co.edu.uniquindio.poo.empresa {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.empresa to javafx.fxml;
    exports co.edu.uniquindio.poo.empresa;
}