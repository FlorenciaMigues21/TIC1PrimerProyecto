package proyecto.ui.operator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import proyecto.business.exceptions.DataBaseError;
import proyecto.business.exceptions.InvalidPublicationInformation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class reservationView {
    @Autowired
    private ReservationManager reserManager;

    public static Publication publicActual;

    @FXML
    private Button back;

    @FXML
    private Label nombreExp;

    @FXML
    private GridPane tablaReserva;

    public void initialize() throws InvalidPublicationInformation, DataBaseError {
        agregarDatosReserva();
        NombreExperiencia();
    }
    //FALTA ID @FIXME
    private void agregarDatosReserva() throws InvalidPublicationInformation, DataBaseError {
        Collection<Reservation> reservas = reserManager.getAllReservationFromPublication(publicActual);
        ArrayList<Reservation> reservasList = new ArrayList<>(reservas);
        for(int i=0;i < reservas.size(); i++){
            tablaReserva.addRow(i+1);
            Text nombre = new Text(reservasList.get(i).getTurista().getUsername());
            tablaReserva.add(nombre,0,i+1);
            //Text fecha = new Text(reservasList.get(i).) @FIXME ARREGLAR FECHA
            Text cantPersonas = new Text(Integer.toString(reservasList.get(i).getCantidad()));
            tablaReserva.add(cantPersonas,2,i+1);
            Text telefono = new Text(reservasList.get(i).getTurista().getPhone());
            tablaReserva.add(telefono,3,i+1);
        }
    }

    private void NombreExperiencia(){
        nombreExp.setText(publicActual.getTitle());
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(MainOperator.class.getResourceAsStream("ReservationView.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Node source = (Node)  actionEvent.getSource();
        Stage stage1  = (Stage) source.getScene().getWindow();
        stage1.close();
    }

}
