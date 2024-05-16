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

public class gestionVoitureController implements Initializable {

    @FXML
    private Button btnAddVoiture;

    @FXML
    private Button btnListVoiture;

    @FXML
    private AnchorPane gestionVoiturePane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void goToPages(ActionEvent event) throws IOException {
        if(event.getSource()== btnAddVoiture){
            Parent fxml= FXMLLoader.load(getClass().getResource("addVoiture.fxml"));
            gestionVoiturePane.getChildren().removeAll();
            gestionVoiturePane.getChildren().setAll(fxml);
        }else if(event.getSource()==btnListVoiture){
            Parent fxml= FXMLLoader.load(getClass().getResource("listVoiture.fxml"));
            gestionVoiturePane.getChildren().removeAll();
            gestionVoiturePane.getChildren().setAll(fxml);
        }
    }
}
