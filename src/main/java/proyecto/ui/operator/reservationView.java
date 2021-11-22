package proyecto.ui.operator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.Main;
import proyecto.business.entities.Publication;
import proyecto.business.entities.Reservation;
import proyecto.business.entities.TouristOperator;
import proyecto.business.entities_managers.ReservationManager;
import proyecto.business.exceptions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class reservationView {
    @Autowired
    private ReservationManager reserManager;

    public static Publication publicActual;

    @FXML
    private AnchorPane ap;
    @FXML
    private Button back;

    @FXML
    private Label nombreExp;

    @FXML
    private GridPane tablaReserva;

    public void initialize() throws InvalidPublicationInformation, DataBaseError {
        ap.sceneProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                newValue.windowProperty().addListener((observable1, oldValue1, newValue1) -> {
                            if (newValue1 != null) {
                                Stage stage = (Stage) ap.getScene().getWindow();
                                publicActual = (Publication) stage.getUserData();
                                try {
                                    agregarDatosReserva();
                                    NombreExperiencia();
                                } catch (InvalidPublicationInformation invalidPublicationInformation) {
                                    invalidPublicationInformation.printStackTrace();
                                } catch (DataBaseError dataBaseError) {
                                    dataBaseError.printStackTrace();
                                }
                            }
                        }
                );
            }
        }
        ));
    }

    //FALTA ID @FIXME
    private void agregarDatosReserva() throws InvalidPublicationInformation, DataBaseError {
        Collection<Reservation> reservas = reserManager.getAllReservationFromPublication(publicActual);
        ArrayList<Reservation> reservasList = new ArrayList<>(reservas);
        for(int i=0;i < reservas.size(); i++){
            tablaReserva.addRow(i);
            Text nombre = new Text(reservasList.get(i).getTurista().getUsername());
            tablaReserva.add(nombre,0,i);
            GridPane.setHalignment(nombre, HPos.CENTER);
            Text hora = new Text(reservasList.get(i).getHourStart()+":00"+"  "+ LocalDate.ofInstant(reservasList.get(i).getDate_reservation().toInstant(), ZoneId.systemDefault()));
            tablaReserva.add(hora,1,i);
            GridPane.setHalignment(hora, HPos.CENTER);
            Text cantPersonas = new Text(Integer.toString(reservasList.get(i).getCantidad()));
            tablaReserva.add(cantPersonas,2,i);
            GridPane.setHalignment(cantPersonas, HPos.CENTER);
            Text telefono = new Text(reservasList.get(i).getTurista().getPhone());
            tablaReserva.add(telefono,4,i);
            GridPane.setHalignment(telefono, HPos.CENTER);
            if(!reservasList.get(i).isValidated_reservation()) {
                Button Validar = new Button();
                int finalI = i;
                Validar.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        reservasList.get(finalI).setValidated_reservation(true);
                        try {
                            reserManager.addReservation(reservasList.get(finalI));
                        } catch (ReservationCreationError reservationCreationError) {
                            reservationCreationError.printStackTrace();
                        } catch (DataBaseError dataBaseError) {
                            dataBaseError.printStackTrace();
                        }
                        try {
                            agregarDatosReserva();
                        } catch (InvalidPublicationInformation e) {
                            e.printStackTrace();
                        } catch (DataBaseError e) {
                            e.printStackTrace();
                        }
                    }
                });
                Validar.setPrefSize(58, 27);
                Validar.setText("Validar");
                tablaReserva.add(Validar, 3, i);
                GridPane.setHalignment(Validar, HPos.CENTER);
            }
        }
    }

    private void NombreExperiencia(){
        nombreExp.setText(publicActual.getTitle());
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        Node source = (Node)  actionEvent.getSource();
        Stage stage1  = (Stage) source.getScene().getWindow();
        stage1.close();
    }

}
