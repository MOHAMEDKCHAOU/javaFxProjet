package gui;

import entities.Location;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import services.LocationService;
import services.VoitureService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class updateLocationController implements Initializable {

    @FXML
    private Button btnClearLocation;

    @FXML
    private Button btnUpdateLocation;

    @FXML
    private TextField txtAdresse;

    @FXML
    private TextField txtCIN;

    @FXML
    private TextField txtChauffeur;

    @FXML
    private DatePicker txtDateDepot;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtPermis;

    @FXML
    private TextField txtPrenom;

    @FXML
    private TextField txtPrix;

    @FXML
    private AnchorPane updateLocationPane;



    Location loc;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("itemLocation.fxml"));
        try {
            AnchorPane anchorPane = fxmlLoader.load();
            HBox hBox = (HBox) anchorPane.getChildren().get(0);
            itemLocationController item = fxmlLoader.getController();
            LocationService ls = new LocationService();
            loc = ls.getById(item.getId());
            txtNom.setText(String.valueOf(loc.getNom()));
            txtPrenom.setText(loc.getPrenom());
            txtMail.setText(loc.getMail());
            txtMobile.setText(String.valueOf(loc.getMobile()));
            txtAdresse.setText(String.valueOf(loc.getAdresse()));
            txtCIN.setText(String.valueOf(loc.getNum_cin()));
            txtPrix.setText(String.valueOf(loc.getPrix()));
            txtPermis.setText(String.valueOf(loc.getPermis_conduite()));
            txtChauffeur.setText(String.valueOf(loc.getChauffeur()));
        } catch (IOException ex) {
            Logger.getLogger(itemLocationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void UpdateLocation(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnUpdateLocation){
            if (txtPrix.getText().isEmpty() || txtPrenom.getText().isEmpty() || txtPermis.getText().isEmpty() ||
                    txtNom.getText().isEmpty() || txtMobile.getText().isEmpty() || txtMail.getText().isEmpty() ||
                    txtCIN.getText().isEmpty() || txtDateDepot.getValue()==null || txtChauffeur.getText().isEmpty() ||
                    txtAdresse.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez remplir tous les détails concernant votre Location.");
                Optional<ButtonType> option = alert.showAndWait();

            } else {
                modifierLocation();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Modifié avec succès");
                alert.setHeaderText(null);
                alert.setContentText("Votre Location a été modifiée avec succès.");
                Optional<ButtonType> option = alert.showAndWait();

                clearFieldsLocation();
            }
        }
        if(event.getSource() == btnClearLocation){
            clearFieldsLocation();
        }
    }

    @FXML
    void clearFieldsLocation() {
        txtChauffeur.clear();
        txtCIN.clear();
        txtMail.clear();
        txtNom.clear();
        txtMobile.clear();
        txtDateDepot.getEditor().clear();
        txtPermis.clear();
        txtPrenom.clear();
        txtPrix.clear();
        txtAdresse.clear();
    }

    private void modifierLocation() {
        // From Formulaire
        int voiture_id= loc.getVoiture_id();
        String nom = txtNom.getText();
        String prenom = txtPrenom.getText();
        String mail = txtMail.getText();
        int mobile = Integer.parseInt(txtMobile.getText());
        String adresse = txtAdresse.getText();
        Date date_prise = loc.getDate_prise();
        Date date_depot = null;
        try {
            LocalDate localDate = txtDateDepot.getValue();
            if (localDate != null) {
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                date_depot = Date.from(instant);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        int num_cin = Integer.parseInt(txtCIN.getText());
        int prix = Integer.parseInt(txtPrix.getText());
        String permis_conduite = txtPermis.getText();
        String chauffeur = txtChauffeur.getText();
        int reponse_location_id = 1;
        int user_id = 1;


        Location location = new Location(
                loc.getId(),
                voiture_id, nom, prenom, mail, mobile, adresse, date_prise, date_depot,
                num_cin, prix, permis_conduite, chauffeur, reponse_location_id , user_id);

        LocationService ls = new LocationService();
        ls.modifier(location);
    }
}
