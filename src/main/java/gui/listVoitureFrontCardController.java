package gui;

import entities.Voiture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class listVoitureFrontCardController implements Initializable {

    @FXML
    private Label labelCategVoiture;

    @FXML
    private Label labelCouleurVoiture;

    @FXML
    private ImageView labelImgVoiture;

    @FXML
    private Label labelMarqueVoiture;

    @FXML
    private Label labelPrixVoiture;

    Voiture voit;
    private static int idVoit;

    public int getIdEve(){
        return this.idVoit;
    }
    public void setData (Voiture voit){
        this.voit = voit;
        labelMarqueVoiture.setText(voit.getMarque());
        labelCategVoiture.setText(voit.getCategories());
        labelCouleurVoiture.setText(String.valueOf(voit.getCouleur()));
        labelPrixVoiture.setText(String.valueOf(voit.getPrix()));
        labelImgVoiture.setImage(new Image("C:\\Users\\hamma kchaou\\Desktop\\gestionVoiture\\gestionVoiture\\src\\main\\java\\uploads\\"+voit.getImage()));
        this.idVoit=voit.getId();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void open_ajouterLocation(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("addLocation.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add Location");
        stage.setScene(new Scene(fxml));
        stage.showAndWait();
    }
}
