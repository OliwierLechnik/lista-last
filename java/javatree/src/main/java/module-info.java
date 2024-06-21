module org.example.javatree {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.javatree to javafx.fxml;
    exports org.example.javatree;
}