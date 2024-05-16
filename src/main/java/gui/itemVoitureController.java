package gui;

import entities.Voiture;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.VoitureService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class itemVoitureController implements Initializable {

    @FXML
    private Button btnModifierVoiture;

    @FXML
    private Button btnSupprimerVoiture;

    @FXML
    private ImageView imgVoiture;

    @FXML
    private AnchorPane itemVoiturePane;

    @FXML
    private Label labelCategVoiture;

    @FXML
    private Label labelCouleurVoiture;

    @FXML
    private Label labelMarqueVoiture;

    @FXML
    private Label labelPrixVoiture;


    private static int id;

    public int getId(){
        return this.id;
    }

    Voiture voit;
    public void setData (Voiture voit){
        this.voit = voit;

        labelCategVoiture.setText(voit.getCategories());
        labelMarqueVoiture.setText(voit.getMarque());
        labelCouleurVoiture.setText(String.valueOf(voit.getCouleur()));
        labelPrixVoiture.setText(String.valueOf(voit.getPrix()));
        imgVoiture.setImage(new Image("C:\\Users\\hamma kchaou\\Desktop\\gestionVoiture\\gestionVoiture\\src\\main\\java\\uploads\\"+voit.getImage()));
        this.id=voit.getId();
    }

    public Voiture getData (Voiture voit){
        this.voit = voit;
        return this.voit;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void open_UpdateVoiture(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("updateVoiture.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Update Voiture");
        stage.setScene(new Scene(fxml));
        stage.showAndWait();
    }

    @FXML
    void supprimerVoiture(ActionEvent event) throws SQLException {
        VoitureService vs = new VoitureService();

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette Voiture ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID de la voiture sélectionnée
            int id = this.voit.getId();

            // Supprimer la voiture de la base de données
            vs.supprimer(id);
        }
    }
}
