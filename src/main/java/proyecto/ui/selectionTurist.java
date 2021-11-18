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

import java.io.IOException;
import java.util.ArrayList;

@Component
public class selectionTurist {
    ObservableList<String> gusto = FXCollections.observableArrayList("Viaje Confort","Viaje alternativo","Viaje de Lujo");

    @Autowired
    private UserManager<Tourist> controlador;

    @Autowired
    private TypeofactivitiesManager manType;

    static Tourist userActual;

    @FXML
    Button siguiente;

    @FXML
    ChoiceBox<String> gustoViaje;

    @FXML
    VBox typeList;


    public void initialize() {
        loadEstacion();
    }

    @FXML
    public void saveSelect(ActionEvent actionEvent){

    }


    private void handleOptions(){
        /*ArrayList<Type>

        if(fam.isSelected()){

        }
        else if(escap.isSelected()){

        }
        else if(luna.isSelected()){

        }
        else if(turism.isSelected()){

        }
        else if(cult.isSelected()){

        }
        else if(desc.isSelected()){

        }
        else if(mochi.isSelected()){

        }*/
    }
    private void choiceBoxOption(ChoiceBox<String> gustoViaje){
        if(gustoViaje.getValue().toString().equals("Viaje Confort") ){

        }
        else if(gustoViaje.getValue().toString().equals("Viaje alternativo")){

        }
        else if(gustoViaje.getValue().toString().equals("Viaje de Lujo")) {
        }
    }
    private void estaciones(CheckBox inv,CheckBox ver,CheckBox ot,CheckBox prim){
        if(inv.isSelected()){
            //ARREGLAR
        }
        else if(ver.isSelected()){
            //ARREGLAR
        }
        else if(ot.isSelected()){

        }
        else if(prim.isSelected()){

        }
    }



    private void loadEstacion() {
        gustoViaje.getItems().addAll(gusto);
        ArrayList<Typeofactivities> listType = new ArrayList<>(manType.getAllActivityTypes());
        for (Typeofactivities typeofactivities : listType) {
            CheckBox type = new CheckBox();
            type.setId(typeofactivities.getName());
            type.setText(typeofactivities.getName());
            typeList.getChildren().add(type);
        }
    }
}
