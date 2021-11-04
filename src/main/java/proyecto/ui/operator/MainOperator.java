package proyecto.ui.operator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.Main;
import proyecto.business.entities.Publication;
import proyecto.business.entities.Reservation;
import proyecto.business.entities.Tourist;
import proyecto.business.entities.TouristOperator;
import proyecto.business.entities_managers.PublicationManager;
import proyecto.business.entities_managers.ReservationManager;
import proyecto.business.entities_managers.UserManager;
import proyecto.business.exceptions.DataBaseError;
import proyecto.business.exceptions.InvalidPublicationInformation;
import proyecto.business.exceptions.InvalidUserInformation;
import proyecto.business.exceptions.PublicationsLoadError;
import proyecto.ui.selectionTurist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class MainOperator {

    @Autowired
    private PublicationManager publiManager;

    @Autowired
    private ReservationManager reservManager;

    @Autowired
    private UserManager<TouristOperator> usuario;

    public static TouristOperator operador;


    @FXML
    private Button back;

    @FXML
    private Button newExperience;

    @FXML
    private GridPane tabla;

    public void initialize() throws InvalidUserInformation, PublicationsLoadError, InvalidPublicationInformation, DataBaseError {
        addExperiencies();
    }

    private void addExperiencies() throws InvalidUserInformation, PublicationsLoadError, InvalidPublicationInformation, DataBaseError {
        Collection<Publication> publicaciones = publiManager.getPublicationFromOperator(operador);
        ArrayList<Publication> publicList = new ArrayList<>(publicaciones);
        for(int i=0; i < publicList.size() ; i++){
            tabla.addRow(i+1);
            Text titulo = new Text(publicList.get(i).getTitle());
            tabla.add(titulo,0,i+1);
            tabla.setHalignment(titulo, HPos.CENTER);
            if (publicList.get(i).isValidated()){
                Text estado = new Text("Validada");
            }else{
                Text estado = new Text("No validada");
            }
            Collection<Reservation> reservas = reservManager.getAllReservationFromPublication(publicList.get(i));
            ArrayList<Reservation> reservasList = new ArrayList<>(reservas);
            Text cantidadReservas = new Text(Integer.toString(reservas.size()));
            tabla.add(cantidadReservas,2,i+1);
            tabla.setHalignment(cantidadReservas, HPos.CENTER);
            Button botonReserva = new Button();
            tabla.setHalignment(botonReserva, HPos.CENTER);
            int finalI = i;
            botonReserva.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Next(publicList.get(finalI));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    void Next(Publication publicacion) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(reservationView.class.getResourceAsStream("ReservationUsersView.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        reservationView.publicActual = publicacion;
    }

    //AGREGAR CALIFICACION




}
