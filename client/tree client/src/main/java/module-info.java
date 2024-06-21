module org.example.tree_client {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tree_client to javafx.fxml;
    exports org.example.tree_client;
}