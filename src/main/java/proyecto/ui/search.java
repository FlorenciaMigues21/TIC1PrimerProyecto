package proyecto.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        stage.setScene(new Scene(root));
        stage.show();
        stage.setUserData(turista);
        Stage stage2 = (Stage) this.seartching.getScene().getWindow();
        stage2.close();
    }

    @FXML
    void carrito(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(carrito.class.getResourceAsStream("carrito.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setUserData(turista);
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
                                loadInfo();
                            }
                        }
                );
            }
        }
        ));

    }

    private void loadInfo(){
        boxItems.getChildren().clear();
        List<Publication> listPub = searchMan.getPublicationBasedOnWord(itemBus);
        for(int i = 0; i< listPub.size();i++){
            HBox newHbox = new HBox();
            newHbox.setPrefWidth(200);
            newHbox.setPrefHeight(100);
            //IMAGEN
            ArrayList<Photo> fotos = new ArrayList<>(listPub.get(i).getPhotoList());
            Image newIm = new Image(Arrays.toString(fotos.get(i).getPhoto()));
            ImageView newImag = new ImageView(newIm);
            newImag.setFitHeight(150);
            newImag.setFitWidth(200);
            VBox newVBox = new VBox();
            newVBox.prefHeight(150);
            newVBox.prefWidth(761);
            Text titulo = new Text(listPub.get(i).getTitle());
            Text descripcion = new Text(listPub.get(i).getDescription());
            newVBox.getChildren().add(titulo);
            newVBox.getChildren().add(descripcion);
            Button newButton = new Button();
            Publication pub = listPub.get(i);
            newButton.setOnAction(e ->{
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                Parent root = null;
                try {
                    root = fxmlLoader.load(experiencePage.class.getResourceAsStream("ExperienciePage.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                experiencePage.publicacionActual = pub;
                experiencePage.userActual = turista;
            });
            newHbox.getChildren().add(newButton);
            boxItems.getChildren().add(newHbox);
        }
    }

    public void buscar(ActionEvent event){
        itemBus = elementoBus.getText();
        loadInfo();
    }



}
