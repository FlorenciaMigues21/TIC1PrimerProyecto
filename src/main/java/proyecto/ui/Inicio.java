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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.Main;
import proyecto.business.entities.*;
import proyecto.business.entities_managers.PhotoManager;
import proyecto.business.entities_managers.PublicationManager;
import proyecto.business.entities_managers.TypeofactivitiesManager;
import proyecto.business.exceptions.DataBaseError;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

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
    private Button exit;

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
                                System.out.println(turista);
                                try {
                                    loadInfo();
                                    loadInfo1();
                                } catch (IOException | DataBaseError e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                );
            }
        }
        ));

    }


    private void loadInfo() throws IOException, DataBaseError {
        list = new ArrayList<>(pubMan.getRecomendedPublications(turista.getMail()));
        gustos1.getChildren().clear();
        for(int i = 0; i<list.size();i++){
            VBox newItem = new VBox();
            newItem.setAlignment(Pos.CENTER);
            newItem.setPrefHeight(236);
            newItem.setPrefWidth(251);
            Set<Photo> fotos = list.get(i).getPhotoList();
            Photo aux = fotos.iterator().next();
            Image newIm = aux.getImageFromByteArray(200,150);
            ImageView imagen = new ImageView();
            imagen.setImage(newIm);
            imagen.setFitWidth(221);
            imagen.setFitHeight(150);
            newItem.getChildren().add(imagen);
            Text newText = new Text();
            newText.setText(list.get(i).getTitle());
            newText.setFont(Font.font(null, FontWeight.BOLD, 15));
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
                doubleObjet db = new doubleObjet();
                db.setPublicacion(pub);
                db.setTurista(turista);
                stage.setUserData(db);
                stage.setScene(new Scene(root));
                stage.show();

            });
            newItem.getChildren().add(newButton);
            gustos1.getChildren().add(newItem);
        }
    }
    private void loadInfo1() throws IOException, DataBaseError {
        ArrayList<Publication> publicaciones = new ArrayList<>();
        ArrayList<String> estaciones = returnType();
        for(int j=0;j<estaciones.size();j++){
            publicaciones.addAll((pubMan.getRecomendedPublicationsByGroupByEspecific(turista.getMail(),estaciones.get(j))));
        }
        gustos2.getChildren().clear();
        for(int i = 0; i<publicaciones.size();i++){
            VBox newItem = new VBox();
            newItem.setAlignment(Pos.CENTER);
            newItem.setPrefHeight(126);
            newItem.setPrefWidth(162);
            Set<Photo> fotos = list.get(i).getPhotoList();
            Photo aux = fotos.iterator().next();
            Image newIm = aux.getImageFromByteArray(200,150);
            ImageView imagen = new ImageView();
            imagen.setImage(newIm);
            imagen.setFitWidth(139);
            imagen.setFitHeight(85);
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
                doubleObjet db = new doubleObjet();
                db.setPublicacion(pub);
                db.setTurista(turista);
                stage.setUserData(db);
                stage.setScene(new Scene(root));
                stage.show();

            });
            newItem.getChildren().add(newButton);
            gustos2.getChildren().add(newItem);
        }
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

    @FXML
    void goSetting(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(selectionTurist.class.getResourceAsStream("SelectionTurist.fxml"));
        Stage stage = new Stage();
        stage.setUserData(turista);
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) this.setting.getScene().getWindow();
        stage2.close();
    }

    @FXML
    void goToSearch(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(search.class.getResourceAsStream("search.fxml"));
        Stage stage = new Stage();
        doubleObjet newDouble = new doubleObjet();
        newDouble.setTurista(turista);
        newDouble.setItem(item.getText());
        stage.setUserData(newDouble);
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) this.setting.getScene().getWindow();
        stage2.close();
    }

    @FXML
    void salir(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(MenuInicial.class.getResourceAsStream("principalPage.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) this.setting.getScene().getWindow();
        stage2.close();
    }

    private ArrayList<String> returnType(){
        ArrayList<Typeofactivities> types = new ArrayList<>(turista.getIntereses());
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0;i<types.size();i++){
            if(types.get(i).getName().equals("Primavera") || types.get(i).getName().equals("Otoño") || types.get(i).getName().equals("Invierno") || types.get(i).getName().equals("Verano")){
                result.add(types.get(i).getName());
            }
        }
        return result;
    }




}
