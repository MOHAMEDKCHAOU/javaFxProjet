package gui;

import entities.Location;
import entities.Voiture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.LocationService;
import services.VoitureService;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class addLocationController implements Initializable {

    @FXML
    private AnchorPane addLocationPane;

    @FXML
    private Button btnAddLocation;

    @FXML
    private Button btnClearLocation;

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
    private TextField txtAdresse;

    private int id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("listVoitureFrontCard.fxml"));
            try {
                AnchorPane anchorPane = fxmlLoader.load();
                VBox hBox = (VBox) anchorPane.getChildren().get(0);
                listVoitureFrontCardController itemController = fxmlLoader.getController();
                this.id = itemController.getIdEve();
            } catch (IOException ex) {
                Logger.getLogger(listVoitureFrontCardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AjoutLocation(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnAddLocation){
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
                ajouterLocation();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajouté avec succès");
                alert.setHeaderText(null);
                alert.setContentText("Votre Location a été ajoutée avec succès.");
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

    private void ajouterLocation() {
        // From Formulaire
        int voiture_id=this.id;
        String nom = txtNom.getText();
        String prenom = txtPrenom.getText();
        String mail = txtMail.getText();
        int mobile = Integer.parseInt(txtMobile.getText());
        String adresse = txtAdresse.getText();
        Date date_prise = null;
        try {
            LocalDate localDate = LocalDate.now();
            if (localDate != null) {
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                date_prise = Date.from(instant);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
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


        Location loc = new Location(
                voiture_id, nom, prenom, mail, mobile, adresse, date_prise, date_depot,
                num_cin, prix, permis_conduite, chauffeur, reponse_location_id , user_id);

        LocationService ls = new LocationService();
        ls.ajouter(loc);
    }
}
