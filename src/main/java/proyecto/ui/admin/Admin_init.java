package proyecto.ui.admin;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.business.entities.Publication;
import proyecto.business.entities.Reservation;
import proyecto.business.entities_managers.PublicationManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class Admin_init {

    @Autowired
    PublicationManager pubManager;

    @FXML
    private ImageView back;
    @FXML
    private GridPane expAValidar;

    @FXML
    private GridPane expValidadas;

    /*
    Collection<Publication> publicaciones = pubManager.getPublicationFromOperator();
    ArrayList<Publication> publicacionesList = new ArrayList<>(publicaciones);*/
    public void initialize(){
        //setScroll();
    }
    /*
    private void setScroll(){
        ScrollPane scrollPane = new ScrollPane(expAValidar);
        ScrollPane scrollPane1 = new ScrollPane(expValidadas);
        scrollPane1.setPrefSize(517, 200);
        scrollPane1.setContent(expValidadas);
        scrollPane1.setFitToHeight(true);
        scrollPane1.setPannable(true);
        scrollPane1.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPrefSize(517, 200);
        scrollPane.setContent(expAValidar);
        scrollPane.setFitToHeight(true);
        scrollPane.setPannable(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }


    private void setExrencieAValidar(){
        for(int i=0; i < publicaciones.size() ; i++){
            expAValidar.addRow(i+1);
            Text titulo = new Text(publicacionesList.get(i).getTitle());
            expAValidar.add(titulo,0,i+1);
            expAValidar.setHalignment(titulo, HPos.CENTER);
            Text operador = new Text(publicacionesList.get(i).getOperador().getUsername());
            expAValidar.add(operador,1,i+1);
            expAValidar.setHalignment(operador, HPos.CENTER);
            Button Validar = new Button();
            expAValidar.setHalignment(Validar, HPos.CENTER);
            int finalI = i;
            Validar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    publicacionesList.get(finalI).setValidated(true);
                    showAlert("La publicación fue aprobada","Ahora estará en la tabla siguiente");
                }
            });
        }
    }
    private void setExperienciasValidadas(){
        for(int i=0; i < publicaciones.size() ; i++){
            expValidadas.addRow(i+1);
            Text titulo = new Text(publicacionesList.get(i).getTitle());
            expValidadas.add(titulo,0,i+1);
            expValidadas.setHalignment(titulo, HPos.CENTER);
            Text operador = new Text(publicacionesList.get(i).getOperador().getUsername());
            expValidadas.add(operador,1,i+1);
            expValidadas.setHalignment(operador, HPos.CENTER);
            //AGREGAR CALIFICACION
        }
    }*/
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}

