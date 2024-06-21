package org.example.javatree;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ClientGUIController {
    @FXML
    private Pane panefx;

    @FXML
    private TextField textfx;

    @FXML
    protected void draw(){
        Connection.getInstance().write("draw:xxx");
        String[] rows = Connection.getInstance().next().split(":");
        for(int i = 0; i < 5; i++){
            String row = rows[i];
            for(int j = 0; j < Math.pow(2,i); j++){
                VBox vbox = (VBox) panefx.getChildren().getFirst();
                HBox hbox = (HBox) vbox.getChildren().get(i);
                Pane p = (Pane) hbox.getChildren().get(j);
                Label l = (Label) p.getChildren().getFirst();
                l.setText(
                        row.split(",")[j].equals("#") ? "" : row.split(",")[j]
                );
            }
        }
    }

    @FXML
    protected void insert(){
        Connection.getInstance().write("insert:"+textfx.getText());
        draw();
    }

    @FXML
    protected void search(){

    }

    @FXML
    protected void delete(){

    }

}