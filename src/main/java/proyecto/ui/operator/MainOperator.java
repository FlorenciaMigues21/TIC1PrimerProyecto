package proyecto.ui.operator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import proyecto.ui.MenuInicial;
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

    @FXML
    private Button exit;

    ArrayList<Publication> publicList;

    public void initialize() throws InvalidUserInformation, PublicationsLoadError, InvalidPublicationInformation, DataBaseError {
        ap.sceneProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                newValue.windowProperty().addListener((observable1, oldValue1, newValue1) -> {
                            if (newValue1 != null) {
                                Stage stage = (Stage) ap.getScene().getWindow();
                                operadorTurist = (TouristOperator) stage.getUserData();
                                addExperiencies();
                            }
                        }
                );
            }
        }
        ));
    }


    private void addExperiencies(){
       try{
           publicList = new ArrayList<>(publiManager.getPublicationFromOperator(operadorTurist));
           for (int i = 0; i < publicList.size(); i++) {
               tabla.addRow(i);
               Text titulo = new Text(publicList.get(i).getTitle());
               tabla.add(titulo, 0, i);
               GridPane.setHalignment(titulo, HPos.CENTER);
               if (publicList.get(i).isValidated()) {
                   Text validado = new Text("Validada");
                   tabla.add(validado, 1, i);
                   GridPane.setHalignment(validado, HPos.CENTER);
                   Text calificacion = new Text(String.valueOf(publicList.get(i).getCalification()));
                   tabla.add(calificacion, 4, i);
                   GridPane.setHalignment(calificacion, HPos.CENTER);
               } else {
                   Text novalidado = new Text("No validada");
                   tabla.add(novalidado, 1, i);
                   GridPane.setHalignment(novalidado, HPos.CENTER);
                   Text calificacion = new Text("No disponible");
                   tabla.add(calificacion, 4, i);
                   GridPane.setHalignment(calificacion, HPos.CENTER);
               }
               Button botonReserva = new Button();
               int finalI = i;
               botonReserva.setOnAction(new EventHandler<ActionEvent>() {
                   @Override
                   public void handle(ActionEvent event) {
                       try {
                           Next(event, publicList.get(finalI));
                           System.out.println(publicList.get(finalI));
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
               });
               botonReserva.setPrefSize(58,27);
               botonReserva.setText("Ir");
               tabla.add(botonReserva,3,i);
               GridPane.setHalignment(botonReserva, HPos.CENTER);
               ArrayList<Reservation> reservasList = new ArrayList<>(reservManager.getAllReservationFromPublication(publicList.get(i)));
               Text cantidadReservas = new Text(Integer.toString(reservasList.size()));
               tabla.add(cantidadReservas, 2, i);
               GridPane.setHalignment(cantidadReservas, HPos.CENTER);
               Button eliminar = new Button();
               eliminar.setOnAction(new EventHandler<ActionEvent>() {
                   @Override
                   public void handle(ActionEvent event) {
                        publiManager.deletePublication(publicList.get(finalI));
                        showAlert("Publicación eliminada","Cuando vuelva a entrar, la publicación ya no se verá.");
                   }
               });
               eliminar.setPrefSize(58,27);
               eliminar.setText("Eliminar");
               tabla.add(eliminar,5,i);
           }
       }
       catch (InvalidUserInformation e){
           System.out.println("No hay publicaciones");
       } catch (PublicationsLoadError publicationsLoadError) {
           publicationsLoadError.printStackTrace();
       } catch (InvalidPublicationInformation invalidPublicationInformation) {
           invalidPublicationInformation.printStackTrace();
       } catch (DataBaseError dataBaseError) {
           dataBaseError.printStackTrace();
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
    @FXML
    void salir(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(MenuInicial.class.getResourceAsStream("principalPage.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) this.ap.getScene().getWindow();
        stage2.close();
    }
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }


}
