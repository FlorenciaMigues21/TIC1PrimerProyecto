package proyecto.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.business.entities.Reservation;
import proyecto.business.entities.Tourist;
import proyecto.business.entities_managers.ReservationManager;
import proyecto.business.exceptions.DataBaseError;
import proyecto.business.exceptions.InvalidUserInformation;

import java.sql.Time;
import java.util.ArrayList;

@Component
public class carrito {

    @Autowired
    private ReservationManager reservMan;

    static Tourist turista;

    @FXML
    private Button backButton;

    @FXML
    private Button calendarButton;

    @FXML
    private Button homeButton;

    @FXML
    private GridPane tabla;

    ArrayList<Reservation> reservaciones;

    public void initialize() throws InvalidUserInformation, DataBaseError {
        loadInfo();
    }

    private void loadInfo() throws InvalidUserInformation, DataBaseError {
        reservaciones = new ArrayList<>(reservMan.getAllReservationFromTourist(turista));
        for(int i=0;i<reservaciones.size();i++){
            if (i==0){
                Text name = new Text(reservaciones.get(0).getPublication().getTitle());
                String start = reservaciones.get(0).getHourStart() + ":00";
                Text hora = new Text(start);
                Text telefono = new Text(reservaciones.get(0).getPublication().getPhone());
                GridPane.setRowIndex(name,0);
                GridPane.setColumnIndex(name,0);
                GridPane.setRowIndex(hora,0);
                GridPane.setColumnIndex(hora,1);
                GridPane.setRowIndex(telefono,0);
                GridPane.setColumnIndex(telefono,2);
            }
            else{
                Text name = new Text(reservaciones.get(i).getPublication().getTitle());
                String start = reservaciones.get(i).getHourStart() + ":00";
                Text hora = new Text(start);
                Text telefono = new Text(reservaciones.get(i).getPublication().getPhone());
                GridPane.setColumnIndex(name,0);
                GridPane.setRowIndex(hora,0);
                GridPane.setColumnIndex(hora,1);
                GridPane.setRowIndex(telefono,0);
                GridPane.setColumnIndex(telefono,2);
            }
        }

    }

    @FXML
    void goBack(ActionEvent event) {

    }

    @FXML
    void goHome(ActionEvent event) {

    }
}
