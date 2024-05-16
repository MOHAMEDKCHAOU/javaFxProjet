package gui;

import entities.Voiture;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.VoitureService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class listVoitureController implements Initializable {

    @FXML
    private AnchorPane listVoiturePane;

    @FXML
    private VBox vBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            VoitureService vs = new VoitureService();
            List<Voiture> voitures = vs.Show();

            for(int i=0;i<voitures.size();i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemVoiture.fxml"));
                try {
                    AnchorPane anchorPane = fxmlLoader.load();
                    HBox hBox = (HBox) anchorPane.getChildren().get(0);
                    itemVoitureController itemController = fxmlLoader.getController();
                    itemController.setData(voitures.get(i));
                    vBox.getChildren().add(hBox);
                } catch (IOException ex) {
                    Logger.getLogger(itemVoitureController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
