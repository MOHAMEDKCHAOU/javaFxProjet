package gui;

import entities.Voiture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import services.VoitureService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;

public class addVoitureController implements Initializable {

    @FXML
    private AnchorPane addVoiturePane;

    @FXML
    private Button btnAddVoiture;

    @FXML
    private Button btnClearVoiture;

    @FXML
    private Button btnImport;

    @FXML
    private ImageView imgVoitureInput;

    @FXML
    private TextField txtCategVoiture;

    @FXML
    private TextField txtCouleurVoiture;

    @FXML
    private TextField txtMarqueVoiture;

    @FXML
    private TextField txtPrixVoiture;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    void AjoutVoiture(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnAddVoiture){
            if (txtPrixVoiture.getText().isEmpty() || txtMarqueVoiture.getText().isEmpty() || txtCouleurVoiture.getText().isEmpty()
                    || txtCategVoiture.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez remplir tous les détails concernant votre Voiture.");
                Optional<ButtonType> option = alert.showAndWait();

            } else {
                ajouterVoiture();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajouté avec succès");
                alert.setHeaderText(null);
                alert.setContentText("Votre Voiture a été ajoutée avec succès.");
                Optional<ButtonType> option = alert.showAndWait();

                clearFieldsVoiture();
            }
        }
        if(event.getSource() == btnClearVoiture){
            clearFieldsVoiture();
        }
    }

    private File selectedImageFile;
    private String imageName = null ;

    @FXML
    void ajouterImage(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(imgVoitureInput.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imgVoitureInput.setImage(image);

            // Générer un nom de fichier unique pour l'image
            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf("."));
            imageName = uniqueID + extension;

            Path destination = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "uploads", imageName);
            Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

        }
    }

    @FXML
    void clearFieldsVoiture() {
        txtCategVoiture.clear();
        txtCouleurVoiture.clear();
        txtMarqueVoiture.clear();
        txtPrixVoiture.clear();
        imgVoitureInput.setImage(null);
    }

    private void ajouterVoiture() {
        // From Formulaire
        String categorie = txtCategVoiture.getText();
        String marque = txtMarqueVoiture.getText();
        String couleur = txtCouleurVoiture.getText();
        int prix = Integer.parseInt(txtPrixVoiture.getText());
        String image = imageName;


        Voiture voit = new Voiture(
                categorie, marque, couleur, prix, image);
        VoitureService vs = new VoitureService();
        vs.ajouter(voit);
    }
}
