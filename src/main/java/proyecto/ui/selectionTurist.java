package proyecto.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.Main;
import proyecto.business.entities.Tourist;
import proyecto.business.entities.TouristOperator;
import proyecto.business.entities.Typeofactivities;
import proyecto.business.entities.User;
import proyecto.business.entities_managers.TypeofactivitiesManager;
import proyecto.business.entities_managers.UserManager;
import proyecto.business.exceptions.InvalidUserInformation;
import proyecto.business.exceptions.UserAlreadyExist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Component
public class selectionTurist {
    ObservableList<String> gusto = FXCollections.observableArrayList("Viaje Confort","Viaje alternativo","Viaje de Lujo");

    @Autowired
    private UserManager<Tourist> controlador;

    @Autowired
    private TypeofactivitiesManager manType;

    static Tourist userActual;

    @FXML
    private AnchorPane selection;

    @FXML
    Button siguiente;

    @FXML
    ChoiceBox<String> gustoViaje;

    @FXML
    VBox typeList;

    @FXML
    CheckBox verano;

    @FXML
    CheckBox invierno;

    @FXML
    CheckBox otono;

    @FXML
    CheckBox primavera;

    Collection<Typeofactivities> gustos;

    public void initialize() {
        selection.sceneProperty().addListener(((observable, oldValue, newValue) -> {
            if (oldValue == null && newValue != null) {
                newValue.windowProperty().addListener((observable1, oldValue1, newValue1) -> {
                            if (oldValue1 == null && newValue1 != null) {
                                Stage stage = (Stage) selection.getScene().getWindow();
                                userActual = (Tourist) stage.getUserData();
                                loadEstacion();
                            }
                        }
                );
            }
        }
        ));
    }

    @FXML
    public void saveSelect(ActionEvent actionEvent) throws IOException, InvalidUserInformation, UserAlreadyExist {
        System.out.println(userActual);
        handleOptions();
        choiceBoxOption();
        estaciones();
        userActual.setIntereses(gustos);
        controlador.addUser(userActual);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(Inicio.class.getResourceAsStream("Inicio.fxml"));
        Stage stage = new Stage();
        stage.setUserData(userActual);
        stage.setScene(new Scene(root));

        stage.show();
        Stage stage2 = (Stage) this.siguiente.getScene().getWindow();
        stage2.close();
    }



    //GUARDA GUSTOS
    private void handleOptions(){
        gustos = new ArrayList<>();
        for (int i = 0; i < typeList.getChildren().size();i++){
            CheckBox actual = (CheckBox)typeList.getChildren().get(i);
            if (actual.isSelected()){
                gustos.add(new Typeofactivities(actual.getId()));
            }
        }
    }
    private void choiceBoxOption(){
        if(gustoViaje.getValue().toString().equals("Viaje Confort") ){
            gustos.add(new Typeofactivities("Viaje Confort"));
        }
        else if(gustoViaje.getValue().toString().equals("Viaje alternativo")){
            gustos.add(new Typeofactivities("Viaje alternativo"));
        }
        else if(gustoViaje.getValue().toString().equals("Viaje de Lujo")) {
            gustos.add(new Typeofactivities("Viaje de Lujo"));
        }
    }
    private void estaciones(){
        if(invierno.isSelected()){
            gustos.add(new Typeofactivities("Invierno"));
        }
        else if(verano.isSelected()){
            gustos.add(new Typeofactivities("Verano"));
        }
        else if(otono.isSelected()){
            gustos.add(new Typeofactivities("Otoño"));
        }
        else if(primavera.isSelected()){
            gustos.add(new Typeofactivities("Primavera"));
        }
    }



    private void loadEstacion() {
        gustoViaje.getItems().addAll(gusto);
        ArrayList<Typeofactivities> listType = new ArrayList<>(manType.getAllActivityTypes());
        for (Typeofactivities typeofactivities : listType) {
            if (!typeofactivities.getName().equals("Verano") && !typeofactivities.getName().equals("Invierno") && !typeofactivities.getName().equals("Otoño") && !typeofactivities.getName().equals("Primavera")
                && !typeofactivities.getName().equals("Viaje Confort") && !typeofactivities.getName().equals("Viaje alternativo") && !typeofactivities.getName().equals("Viaje de Lujo")){
                CheckBox type = new CheckBox();
                type.setId(typeofactivities.getName());
                type.setText(typeofactivities.getName());
                typeList.getChildren().add(type);
            }
        }
    }
}
