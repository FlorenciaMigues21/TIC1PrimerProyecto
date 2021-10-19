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
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.Main;
import proyecto.business.entities.User;
import proyecto.business.entities_managers.UserManager;

import java.io.IOException;

@Component
public class selectionTurist {
    ObservableList<String> gusto = FXCollections.observableArrayList("Viaje Confort","Viaje alternativo","Viaje de Lujo");

    @Autowired
    private UserManager controlador;

    static User userActual;

    @FXML
    Button siguiente;

    @FXML
    ChoiceBox<String> gustoViaje;

    @FXML
    CheckBox familiar;

    @FXML
    CheckBox escapadasrurales;

    @FXML
    CheckBox lunademiel;

    @FXML
    CheckBox turismoaventura;

    @FXML
    CheckBox cultural;

    @FXML
    CheckBox descanso;

    @FXML
    CheckBox mochilero;

    @FXML
    CheckBox invierno;

    @FXML
    CheckBox primavera;

    @FXML
    CheckBox otono;

    @FXML
    CheckBox verano;


    public void initialize() {
        loadEstacion();
    }


    private void handleOptions(CheckBox fam,CheckBox escap,CheckBox luna,CheckBox turism,CheckBox cult,CheckBox desc,CheckBox mochi){


        if(fam.isSelected()){

        }
        else if(escap.isSelected()){
            //ARREGLAR
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

        }
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
    }
}
