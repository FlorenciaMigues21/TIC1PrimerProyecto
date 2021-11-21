package proyecto.ui.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.business.entities.*;
import proyecto.business.entities_managers.ComentaryManager;
import proyecto.business.exceptions.DataBaseError;
import proyecto.business.exceptions.IncompleteObjectException;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;

@Component
public class experienceView {

    static Publication publicacion;

    @Autowired
    ComentaryManager comentaryMan;

    @FXML
    private AnchorPane ap;

    @FXML
    private Label NombreAtraccion;

    @FXML
    private Button backButton;

    @FXML
    private VBox comBox;

    @FXML
    private Text direccion;

    @FXML
    private VBox higiene;

    @FXML
    private ChoiceBox<Time> horario;

    @FXML
    private ImageView imagen1;

    @FXML
    private ImageView imagen2;

    @FXML
    private ImageView imagen3;

    @FXML
    private ImageView imagen4;

    @FXML
    private ImageView imagen5;

    @FXML
    private Text infoExp;

    @FXML
    private VBox itemsInc;

    @FXML
    private Text precio;

    @FXML
    private Label puntaje;

    @FXML
    private Label telefono;

    @FXML
    void goBack(ActionEvent event) {
        Stage stage2 = (Stage) this.backButton.getScene().getWindow();
        stage2.close();
    }

    public void initialize() throws IOException {
        ap.sceneProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                newValue.windowProperty().addListener((observable1, oldValue1, newValue1) -> {
                            if (newValue1 != null) {
                                Stage stage = (Stage) ap.getScene().getWindow();
                                publicacion = (Publication) stage.getUserData();
                                try {
                                    loadInfo();
                                } catch (IOException | DataBaseError | IncompleteObjectException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                );
            }
        }
        ));

    }

    private void loadInfo() throws IOException, DataBaseError, IncompleteObjectException {
        NombreAtraccion.setText(publicacion.getTitle());
        direccion.setText(publicacion.getUbication());
        UpElements();
        infoExp.setText(publicacion.getDescription());
        telefono.setText(publicacion.getPhone());
        horario.getItems().addAll(publicacion.getHourStart());
        precio.setText(String.valueOf(publicacion.getPrecio()));
        UpPhotos();
        upComentaries();

    }
    //Subir elementos
    private void UpElements(){
        ArrayList<IncludedInPublication> listInclud = new ArrayList<>(publicacion.getIncluido());
        ArrayList<Hygiene> listHigiene = new ArrayList<>(publicacion.getMedidas_de_higiene());
        for(int i=0;i<publicacion.getIncluido().size();i++){
            Label newItem = new Label(listInclud.get(i).getIncluido());
            itemsInc.getChildren().add(newItem);
        }
        for (int i=0;i<publicacion.getMedidas_de_higiene().size();i++){
            Label newItem = new Label(listHigiene.get(i).getMedidas());
            higiene.getChildren().add(newItem);
        }
    }

    //Subir fotos
    private void UpPhotos() throws IOException {
        ArrayList<Photo> fotos = new ArrayList<>(publicacion.getPhotoList());
        Image newImage1 = fotos.get(0).getImageFromByteArray(fotos.get(0).getPhoto());
        imagen1.setImage(newImage1);
        Image newImage2 = fotos.get(1).getImageFromByteArray(fotos.get(1).getPhoto());
        imagen2.setImage(newImage2);
        Image newImage3 = fotos.get(2).getImageFromByteArray(fotos.get(2).getPhoto());
        imagen3.setImage(newImage3);
        Image newImage4 = fotos.get(3).getImageFromByteArray(fotos.get(3).getPhoto());
        imagen4.setImage(newImage4);
        Image newImage5 = fotos.get(4).getImageFromByteArray(fotos.get(4).getPhoto());
        imagen5.setImage(newImage5);
    }

    //Subir comentarios
    private void upComentaries() throws DataBaseError, IncompleteObjectException {
        ArrayList<Comentary> comentarios = new ArrayList<>(comentaryMan.getComentaryOfPublication(publicacion));
        for (Comentary comentary : comentarios) {
            VBox newVBox = new VBox();
            newVBox.setPrefWidth(269);
            newVBox.setPrefHeight(77);
            Text user = new Text(comentary.getTurista().getUsername());
            Text comentario = new Text(comentary.getComantary());
            newVBox.getChildren().add(user);
            newVBox.getChildren().add(comentario);
            comBox.getChildren().add(newVBox);
        }
    }
}


