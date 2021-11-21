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
import javafx.scene.layout.AnchorPane;
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

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class MainOperator {

    public static TouristOperator operadorTurist;

    @Autowired
    private PublicationManager publiManager;

    @Autowired
    private ReservationManager reservManager;

    @FXML
    private AnchorPane ap;
    @FXML
    private Button back;

    @FXML
    private Button newExperience;

    @FXML
    private GridPane tabla;

    ArrayList<Publication> publicList;

    public void initialize() throws InvalidUserInformation, PublicationsLoadError, InvalidPublicationInformation, DataBaseError {
        ap.sceneProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                newValue.windowProperty().addListener((observable1, oldValue1, newValue1) -> {
                            if (newValue1 != null) {
                                Stage stage = (Stage) ap.getScene().getWindow();
                                operadorTurist = (TouristOperator) stage.getUserData();
                                try {
                                    System.out.println(operadorTurist);
                                    addExperiencies();
                                } catch (InvalidUserInformation e) {
                                    e.printStackTrace();
                                } catch (PublicationsLoadError e) {
                                    e.printStackTrace();
                                } catch (InvalidPublicationInformation e) {
                                    e.printStackTrace();
                                } catch (DataBaseError e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                );
            }
        }
        ));
    }


    private void addExperiencies() throws InvalidUserInformation, PublicationsLoadError, InvalidPublicationInformation, DataBaseError {
       if (publiManager.getPublicationFromOperator(operadorTurist) != null) {
           publicList = new ArrayList<>(publiManager.getPublicationFromOperator(operadorTurist));
           for (int i = 0; i < publicList.size(); i++) {
               tabla.addRow(i);
               Text titulo = new Text(publicList.get(i).getTitle());
               tabla.add(titulo, 0, i);
               GridPane.setHalignment(titulo, HPos.CENTER);
               if (publicList.get(i).isValidated()) {
                   Text validado = new Text("Validada");
                   tabla.add(validado, 1, i);
                   Text calificacion = new Text(String.valueOf(publicList.get(i).getCalification()));
                   tabla.add(calificacion, 3, i);
               } else {
                   Text novalidado = new Text("No validada");
                   tabla.add(novalidado, 1, i);
                   Text calificacion = new Text("-");
                   tabla.add(calificacion, 3, i);
               }
               ArrayList<Reservation> reservasList = new ArrayList<>(reservManager.getAllReservationFromPublication(publicList.get(i)));
               Text cantidadReservas = new Text(Integer.toString(reservasList.size()));
               tabla.add(cantidadReservas, 2, i);
               GridPane.setHalignment(cantidadReservas, HPos.CENTER);

               Button botonReserva = new Button();
               GridPane.setHalignment(botonReserva, HPos.CENTER);
               int finalI = i;
               botonReserva.setOnAction(new EventHandler<ActionEvent>() {
                   @Override
                   public void handle(ActionEvent event) {
                       try {
                           Next(event, publicList.get(finalI));
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
               });

           }
       }
    }

    void Next(ActionEvent event,Publication publicacion) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(reservationView.class.getResourceAsStream("ReservationUsersView.fxml"));
        Stage stage = new Stage();
        stage.setUserData(publicacion);
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    public void AddExperience(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        // Step 3
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(ExperienceDisplayOperator.class.getResourceAsStream("ExperienceDisplayOperator.fxml"));
        stage.setUserData(operadorTurist);
        stage.setScene(new Scene(root));
        stage.show();
    }



}
