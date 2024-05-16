package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class gestionLocationController implements Initializable {

    @FXML
    private Button btnListLocation;

    @FXML
    private AnchorPane gestionLocationPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void goToPages(ActionEvent event) throws IOException {
        if(event.getSource()== btnListLocation){
            Parent fxml= FXMLLoader.load(getClass().getResource("listLocation.fxml"));
            gestionLocationPane.getChildren().removeAll();
            gestionLocationPane.getChildren().setAll(fxml);
        }
    }
}
