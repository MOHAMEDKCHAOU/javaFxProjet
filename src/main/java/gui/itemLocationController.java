package gui;

import entities.Location;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.LocationService;
import services.VoitureService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class itemLocationController implements Initializable {

    @FXML
    private Button btnModifierLocation;

    @FXML
    private Button btnSupprimerLocation;

    @FXML
    private AnchorPane itemLocationPane;

    @FXML
    private Label labelMailLocation;

    @FXML
    private Label labelMobileLocation;

    @FXML
    private Label labelNomLocation;

    @FXML
    private Label labelPrenomLocation;

    @FXML
    private Label labelPrixLocation;

    @FXML
    private Label labelVoitureLocation;


    private static int id;

    public int getId(){
        return this.id;
    }

    Location loc;
    public void setData (Location loc) throws SQLException {
        this.loc = loc;
        VoitureService vs = new VoitureService();

        labelVoitureLocation.setText(vs.getById(loc.getVoiture_id()).getMarque());
        labelNomLocation.setText(loc.getNom());
        labelPrenomLocation.setText(String.valueOf(loc.getPrenom()));
        labelMailLocation.setText(String.valueOf(loc.getMail()));
        labelMobileLocation.setText(String.valueOf(loc.getMobile()));
        labelPrixLocation.setText(String.valueOf(loc.getPrix()));
        this.id=loc.getId();
    }

    public Location getData (Location loc){
        this.loc = loc;
        return this.loc;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    void open_ShowLocation(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("showLocation.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Show Location");
        stage.setScene(new Scene(fxml));
        stage.showAndWait();
    }

    @FXML
    void open_UpdateLocation(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("updateLocation.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Update Location");
        stage.setScene(new Scene(fxml));
        stage.showAndWait();
    }

    @FXML
    void supprimerLocation(ActionEvent event) throws SQLException {
        LocationService ls = new LocationService();

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette Location ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID de la location sélectionnée
            int id = this.loc.getId();

            // Supprimer la location de la base de données
            ls.supprimer(id);
        }
    }
}
