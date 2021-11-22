package proyecto.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.Main;
import proyecto.business.entities.Photo;
import proyecto.business.entities.Publication;
import proyecto.business.entities.Tourist;
import proyecto.business.entities_managers.Busqueda.SearchService;
import proyecto.business.entities_managers.PublicationManager;
import proyecto.ui.admin.experienceView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class search {

    @Autowired
    SearchService searchMan;

    static String itemBus;

    static Tourist turista;

    @FXML
    private AnchorPane seartching;
    @FXML
    private VBox boxItems;

    @FXML
    private Button buscar;

    @FXML
    private Button calendarButton;

    @FXML
    private TextField elementoBus;

    @FXML
    private Button homeButton;

    @FXML
    void goHome(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(Inicio.class.getResourceAsStream("Inicio.fxml"));
        Stage stage = new Stage();
        stage.setUserData(turista);
        stage.setScene(new Scene(root));
        stage.show();

        Stage stage2 = (Stage) this.seartching.getScene().getWindow();
        stage2.close();
    }

    @FXML
    void carrito(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(carrito.class.getResourceAsStream("carrito.fxml"));
        Stage stage = new Stage();
        stage.setUserData(turista);
        stage.setScene(new Scene(root));
        stage.show();

        Stage stage2 = (Stage) this.calendarButton.getScene().getWindow();
        stage2.close();
    }
    public void initialize() {
        seartching.sceneProperty().addListener(((observable, oldValue, newValue) -> {
            if (oldValue == null && newValue != null) {
                newValue.windowProperty().addListener((observable1, oldValue1, newValue1) -> {
                            if (oldValue1 == null && newValue1 != null) {
                                Stage stage = (Stage) seartching.getScene().getWindow();
                                doubleObjet db = (doubleObjet) stage.getUserData();
                                itemBus = db.getItem();
                                turista = db.getTurista();
                                try {
                                    loadInfo();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                );
            }
        }
        ));

    }

    private void loadInfo() throws IOException {
        boxItems.getChildren().clear();
        List<Publication> listPub = searchMan.getPublicationBasedOnWord(itemBus);
        for(int i = 0; i< listPub.size();i++) {
            if (listPub.get(i).isValidated()){
                HBox newHbox = new HBox();
                newHbox.setPrefWidth(200);
                newHbox.setPrefHeight(100);
                //IMAGEN
                Set<Photo> fotos = listPub.get(i).getPhotoList();
                Photo aux = fotos.iterator().next();
                Image newIm = aux.getImageFromByteArray(200, 150);
                ImageView newImag = new ImageView(newIm);
                newImag.setFitHeight(150);
                newImag.setFitWidth(200);
                newHbox.getChildren().add(newImag);
                VBox newVBox = new VBox();
                newVBox.prefHeight(150);
                newVBox.prefWidth(761);
                newVBox.setAlignment(Pos.CENTER);
                newHbox.getChildren().add(newVBox);
                newVBox.setSpacing(25);
                newHbox.setSpacing(25);
                Text titulo = new Text(listPub.get(i).getTitle());
                Text descripcion = new Text(listPub.get(i).getDescription());
                titulo.setFont(Font.font(null, FontWeight.BOLD, 20));
                newVBox.getChildren().add(titulo);
                newVBox.getChildren().add(descripcion);
                Button newButton = new Button();
                Publication pub = listPub.get(i);
                newButton.setOnAction(e -> {
                    try {
                        Next(e, pub);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                newButton.setText("Ver");
                newButton.setPrefSize(58, 27);
                HBox.setMargin(newButton, new Insets(50, 0, 0, 0));
                newHbox.getChildren().add(newButton);
                boxItems.getChildren().add(newHbox);
            }
        }
        boxItems.setSpacing(50);
    }
    void Next(ActionEvent event,Publication publicacion) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(experiencePage.class.getResourceAsStream("ExperiencePage.fxml"));
        Stage stage = new Stage();
        doubleObjet db = new doubleObjet();
        db.setPublicacion(publicacion);
        db.setTurista(turista);
        stage.setUserData(db);
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) seartching.getScene().getWindow();
        stage2.close();
    }
    public void buscar(ActionEvent event) throws IOException {
        itemBus = elementoBus.getText();
        loadInfo();
    }



}
