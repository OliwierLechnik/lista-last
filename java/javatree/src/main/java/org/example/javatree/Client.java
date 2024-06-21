package org.example.javatree;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Client extends Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Pane panefx = (Pane) scene.lookup("#panefx");

        int numberOfRows = 5;
        VBox vbox = new VBox();
        vbox.setId("vboxfx");

        for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {

            HBox hBox = new HBox();

            int numberOfPanes = (int) Math.pow(2, rowIndex);

            for (int i = 0; i < numberOfPanes; i++) {
                Pane pane = new Pane();
                pane.setMinHeight(50);
                pane.setMinWidth(50);

                pane.prefWidthProperty().bind(hBox.widthProperty().divide(numberOfPanes));
                Label label = new Label("");
                label.setId(String.format("%d-%d",rowIndex,i));
                label.setAlignment(Pos.CENTER);
                label.prefWidthProperty().bind(hBox.widthProperty().divide(numberOfPanes));
                label.setStyle("-fx-text-alignment: center");
                pane.getChildren().add(label);
//                pane.setStyle("-fx-border-color: black");

                hBox.getChildren().add(pane);
            }

            vbox.getChildren().add(hBox);
        }

//        Node <String> tree = new Node<String>("dupa");
//        tree.insert("beksa");
//        tree.insert("ala ma kota");
//        tree.insert("cipa");
//        tree.insert("ewa");


//        for(int i = 0; i < numberOfRows; i++){
//            String row = tree.getRowAsString(i);
//            for(int j = 0; j < Math.pow(2,i); j++){
//                HBox hbox = (HBox) vbox.getChildren().get(i);
//                Pane p = (Pane) hbox.getChildren().get(j);
//                Label l = (Label) p.getChildren().getFirst();
//                l.setText(
//                    row.split(",")[j]
//                );
//            }
//        }

        panefx.getChildren().add(vbox);

        primaryStage.setTitle("Binary tree");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}