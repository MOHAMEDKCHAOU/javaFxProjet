package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HelloController {
    @FXML
    private Button btnVoiture;

    @FXML
    private Button btnLocation;

    @FXML
    private AnchorPane view_pages;

    @FXML
    void switchForm(ActionEvent event) throws IOException {
        if(event.getSource()== btnVoiture){
            Parent fxml= FXMLLoader.load(getClass().getResource("gestionVoiture.fxml"));
            view_pages.getChildren().removeAll();
            view_pages.getChildren().setAll(fxml);
        }else if(event.getSource()==btnLocation){
            Parent fxml= FXMLLoader.load(getClass().getResource("gestionLocation.fxml"));
            view_pages.getChildren().removeAll();
            view_pages.getChildren().setAll(fxml);
        }
    }

}