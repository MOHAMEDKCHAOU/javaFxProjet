package gui;

import entities.Voiture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import services.VoitureService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class updateVoitureController implements Initializable {

    @FXML
    private Button btnClearVoiture;

    @FXML
    private Button btnImport;

    @FXML
    private Button btnUpdateVoiture;

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

    @FXML
    private AnchorPane updateVoiturePane;


    Voiture voit;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("itemVoiture.fxml"));
        try {
            AnchorPane anchorPane = fxmlLoader.load();
            HBox hBox = (HBox) anchorPane.getChildren().get(0);
            itemVoitureController item = fxmlLoader.getController();
            VoitureService vs = new VoitureService();
            voit = vs.getById(item.getId());
            txtCategVoiture.setText(String.valueOf(voit.getCategories()));
            txtMarqueVoiture.setText(voit.getMarque());
            txtCouleurVoiture.setText(voit.getCouleur());
            txtPrixVoiture.setText(String.valueOf(voit.getPrix()));
            imgVoitureInput.setImage(new Image("C:\\Users\\hamma kchaou\\Desktop\\gestionVoiture\\gestionVoiture\\src\\main\\java\\uploads\\"+voit.getImage()));
            imageName = voit.getImage();
        } catch (IOException ex) {
            Logger.getLogger(itemVoitureController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void UpdateVoiture(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnUpdateVoiture){
            if (txtPrixVoiture.getText().isEmpty() || txtMarqueVoiture.getText().isEmpty() || txtCouleurVoiture.getText().isEmpty()
                    || txtCategVoiture.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez remplir tous les détails concernant votre Voiture.");
                Optional<ButtonType> option = alert.showAndWait();

            } else {
                modifierVoiture();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Modifiée avec succès");
                alert.setHeaderText(null);
                alert.setContentText("Votre Voiture a été modifiée avec succès.");
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

    void modifierVoiture(){
        String categorie = txtCategVoiture.getText();
        String marque = txtMarqueVoiture.getText();
        String couleur = txtCouleurVoiture.getText();
        int prix = Integer.parseInt(txtPrixVoiture.getText());
        String image = imageName;


        Voiture v = new Voiture(
                voit.getId(),
                categorie, marque, couleur, prix,image);
        VoitureService vs = new VoitureService();
        vs.modifier(v);
    }
}
