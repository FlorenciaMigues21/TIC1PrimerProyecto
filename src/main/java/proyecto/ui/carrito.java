package proyecto.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.Main;
import proyecto.business.entities.Reservation;
import proyecto.business.entities.Tourist;
import proyecto.business.entities_managers.ReservationManager;
import proyecto.business.exceptions.DataBaseError;
import proyecto.business.exceptions.InvalidUserInformation;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

@Component
public class carrito {

    @Autowired
    private ReservationManager reservMan;

    static Tourist turista;

    @FXML
    private AnchorPane carr;
    @FXML
    private Button backButton;

    @FXML
    private Button homeButton;

    @FXML
    private GridPane tabla;

    ArrayList<Reservation> reservaciones;

    public void initialize() throws InvalidUserInformation, DataBaseError {
        carr.sceneProperty().addListener(((observable, oldValue, newValue) -> {
            if (oldValue == null && newValue != null) {
                newValue.windowProperty().addListener((observable1, oldValue1, newValue1) -> {
                            if (oldValue1 == null && newValue1 != null) {
                                Stage stage = (Stage) carr.getScene().getWindow();
                                turista = (Tourist) stage.getUserData();
                                try {
                                    loadInfo();
                                } catch (InvalidUserInformation | DataBaseError e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                );
            }
        }
        ));

    }

    private void loadInfo() throws InvalidUserInformation, DataBaseError {
        removeRow();
        reservaciones = new ArrayList<>(reservMan.getAllReservationFromTourist(turista));
        System.out.println(reservaciones.size());
        for(int i=0;i<reservaciones.size();i++){
            if (i==0){
                Text name = new Text(reservaciones.get(0).getPublication().getTitle());
                String start = reservaciones.get(0).getHourStart() + ":00";
                Text hora = new Text(start+" "+ LocalDate.ofInstant(reservaciones.get(0).getDate_reservation().toInstant(), ZoneId.systemDefault()));
                Text telefono = new Text(reservaciones.get(0).getPublication().getPhone());
                Text estado;
                if(reservaciones.get(0).isValidated_reservation()){
                    estado = new Text("Aprobada");
                }
                else{
                    estado = new Text("No aprobada aún");
                }
                tabla.add(name,0,0);
                tabla.add(hora,1,0);
                tabla.add(estado,2,0);
                tabla.add(telefono,3,0);
            }
            else{
                Text name = new Text(reservaciones.get(i).getPublication().getTitle());
                String start = reservaciones.get(i).getHourStart() + ":00";
                Text hora = new Text(start+reservaciones.get(i).getDate_reservation());
                Text telefono = new Text(reservaciones.get(i).getPublication().getPhone());
                Text estado;
                if(reservaciones.get(i).isValidated_reservation()){
                    estado = new Text("Aprobada");
                }
                else{
                    estado = new Text("No aprobada aún");
                }
                tabla.add(name,0,i);
                tabla.add(hora,1,i);
                tabla.add(estado,2,i);
                tabla.add(telefono,3,i);
            }
        }

    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(Inicio.class.getResourceAsStream("Inicio.fxml"));
        Stage stage = new Stage();
        stage.setUserData(turista);
        stage.setScene(new Scene(root));

        stage.show();
        Stage stage2 = (Stage) this.carr.getScene().getWindow();
        stage2.close();
    }

    @FXML
    void goHome(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(Inicio.class.getResourceAsStream("Inicio.fxml"));
        Stage stage = new Stage();
        stage.setUserData(turista);
        stage.setScene(new Scene(root));

        stage.show();
        Stage stage2 = (Stage) this.carr.getScene().getWindow();
        stage2.close();
    }

    private void removeRow(){

        while (tabla.getRowConstraints().size() > 0) {
            tabla.getRowConstraints().remove(0);
        }

    }
}
