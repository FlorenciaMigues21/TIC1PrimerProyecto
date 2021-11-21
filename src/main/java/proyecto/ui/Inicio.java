package proyecto.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.Main;
import proyecto.business.entities.Photo;
import proyecto.business.entities.Publication;
import proyecto.business.entities.Tourist;
import proyecto.business.entities.TouristOperator;
import proyecto.business.entities_managers.PhotoManager;
import proyecto.business.entities_managers.PublicationManager;
import proyecto.business.entities_managers.TypeofactivitiesManager;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class Inicio {

    static Tourist turista;


    @Autowired
    private TypeofactivitiesManager typeMan;

    @Autowired
    private PublicationManager pubMan;

    @Autowired
    private PhotoManager fotoMan;

    @FXML
    private AnchorPane inicio;

    @FXML
    private Button calendarButton;

    @FXML
    private Button homeButton;

    @FXML
    private HBox gustos1;

    @FXML
    private HBox gustos2;

    @FXML
    private Button setting;

    @FXML
    private Button buscar;

    @FXML
    private TextField item;

    ArrayList<Publication> list;

    public void initialize() throws IOException {
        inicio.sceneProperty().addListener(((observable, oldValue, newValue) -> {
            if (oldValue == null && newValue != null) {
                newValue.windowProperty().addListener((observable1, oldValue1, newValue1) -> {
                            if (oldValue1 == null && newValue1 != null) {
                                Stage stage = (Stage) inicio.getScene().getWindow();
                                turista = (Tourist) stage.getUserData();
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
        list = new ArrayList<>();
        gustos1.getChildren().clear();
        for(int i = 0; i<list.size();i++){
            VBox newItem = new VBox();
            newItem.setAlignment(Pos.CENTER);
            newItem.setPrefHeight(236);
            newItem.setPrefWidth(251);
            ArrayList<Photo> listaImage = new ArrayList<>(list.get(i).getPhotoList());
            Image newImage = listaImage.get(i).getImageFromByteArray(listaImage.get(i).getPhoto());
            ImageView imagen = new ImageView();
            imagen.setImage(newImage);
            imagen.setFitWidth(221);
            imagen.setFitHeight(150);
            newItem.getChildren().add(imagen);
            Text newText = new Text();
            newText.setText(list.get(i).getTitle());
            newItem.getChildren().add(newText);
            Button newButton = new Button();
            Publication pub = list.get(i);
            newButton.setText("Ir");
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
                doubleObjet db = new doubleObjet();
                db.setPublicacion(pub);
                db.setTurista(turista);
                stage.setUserData(db);
                stage.show();

            });
            newItem.getChildren().add(newButton);
            gustos1.getChildren().add(newItem);
        }
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

    @FXML
    void goSetting(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(selectionTurist.class.getResourceAsStream("SelectionTurist.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setUserData(turista);
        Stage stage2 = (Stage) this.setting.getScene().getWindow();
        stage2.close();
    }

    @FXML
    void goToSearch(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(search.class.getResourceAsStream("search.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        doubleObjet newDouble = new doubleObjet();
        newDouble.setTurista(turista);
        newDouble.setItem(item.getText());
        stage.setUserData(newDouble);
        Stage stage2 = (Stage) this.setting.getScene().getWindow();
        stage2.close();
    }


}
