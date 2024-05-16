package gui;

import entities.Voiture;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Pagination;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import services.VoitureService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class listVoitureFrontController implements Initializable {

    @FXML
    private ImageView background;

    @FXML
    private GridPane grid;

    @FXML
    private HBox hbox;

    @FXML
    private AnchorPane listVoitureFront;

    @FXML
    private Pagination pag;

    @FXML
    private HBox vbox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            VoitureService vs = new VoitureService();
            List<Voiture> voitures = vs.Show();
            pag.setPageCount((int) Math.ceil(voitures.size() / 3.0)); // Nombre total de pages nécessaire pour afficher toutes les cartes
            pag.setPageFactory(pageIndex -> {
                HBox hbox = new HBox();
                hbox.setSpacing(10);
                hbox.setAlignment(Pos.CENTER);
                int itemsPerPage = 3; // Nombre des sujets à afficher par page
                int page = pageIndex * itemsPerPage;
                for (int i = page; i < Math.min(page + itemsPerPage, voitures.size()); i++) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("listVoitureFrontCard.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();
                        anchorPane.getStyleClass().add("ct");
                        listVoitureFrontCardController itemController = fxmlLoader.getController();
                        itemController.setData(voitures.get(i));
                        hbox.getChildren().add(anchorPane);
                        HBox.setMargin(anchorPane, new Insets(10)); // Marges entre les cartes
                    } catch (IOException ex) {
                        Logger.getLogger(listVoitureFrontCardController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                return hbox;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
