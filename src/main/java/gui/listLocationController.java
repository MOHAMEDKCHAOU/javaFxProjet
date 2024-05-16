package gui;

import entities.Location;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.LocationService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class listLocationController implements Initializable {

    @FXML
    private AnchorPane listLocationPane;

    @FXML
    private VBox vBox;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LocationService ls = new LocationService();
            List<Location> locations = ls.Show();

            for(int i=0;i<locations.size();i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemLocation.fxml"));
                try {
                    AnchorPane anchorPane = fxmlLoader.load();
                    HBox hBox = (HBox) anchorPane.getChildren().get(0);
                    itemLocationController itemController = fxmlLoader.getController();
                    itemController.setData(locations.get(i));
                    vBox.getChildren().add(hBox);
                } catch (IOException ex) {
                    Logger.getLogger(itemLocationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
