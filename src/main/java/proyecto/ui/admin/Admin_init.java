package proyecto.ui.admin;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.Main;
import proyecto.business.entities.Admin;
import proyecto.business.entities.Publication;
import proyecto.business.entities.Reservation;
import proyecto.business.entities.TouristOperator;
import proyecto.business.entities_managers.PublicationManager;
import proyecto.business.exceptions.PublicationCreationError;
import proyecto.business.exceptions.PublicationsLoadError;
import proyecto.ui.carrito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class Admin_init {

    public static Admin usuario;

    @Autowired
    PublicationManager pubManager;

    @FXML
    private AnchorPane ap;

    @FXML
    private ImageView back;
    @FXML
    private GridPane expAValidar;

    @FXML
    private GridPane expValidadas;

    @FXML
    private Button crearOp;

    ArrayList<Publication> publicVal;
    ArrayList<Publication> publicNoVal;
    public void initialize(){
        ap.sceneProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                newValue.windowProperty().addListener((observable1, oldValue1, newValue1) -> {
                            if (newValue1 != null) {
                                Stage stage = (Stage) ap.getScene().getWindow();
                                usuario = (Admin) stage.getUserData();
                                GetPublication();
                                setScroll();
                                setExperienciasValidadas();
                                setExrencieAValidar();
                            }
                        }
                );
            }
        }
        ));
    }

    private void GetPublication(){
        Collection<Publication> publicacionesVal = null;
        try {
            publicacionesVal = pubManager.getPublicationByValidated(true);
        } catch (PublicationsLoadError e) {
            e.printStackTrace();
        }
        Collection<Publication> publicacionesNoVal = null;
        try {
            publicacionesNoVal = pubManager.getPublicationByValidated(false);
        } catch (PublicationsLoadError e) {
            e.printStackTrace();
        }
        publicVal = new ArrayList<>(publicacionesVal);
        publicNoVal = new ArrayList<>(publicacionesNoVal);
    }

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
        removeRow(false);
        for(int i=0; i < publicNoVal.size() ; i++){
            expAValidar.addRow(i);
            Text titulo = new Text(publicNoVal.get(i).getTitle());
            expAValidar.add(titulo,0,i);
            Text operador = new Text(publicNoVal.get(i).getOperador().getUsername());
            expAValidar.add(operador,1,i);
            Button Validar = new Button();
            int finalI = i;
            Validar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    publicNoVal.get(finalI).setValidated(true);
                    try {
                        pubManager.createAndUpdatePublication(publicNoVal.get(finalI));
                    } catch (PublicationCreationError e) {
                        e.printStackTrace();
                    }
                    showAlert("La publicación fue aprobada","Ahora estará en la tabla siguiente");
                }
            });
            Validar.setPrefSize(58,27);
            Validar.setText("Validar");
            expAValidar.add(Validar,2,i);
            /*Button Ver = new Button();
            Ver.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    publicNoVal.get(finalI).setValidated(true);
                    showAlert("La publicación fue aprobada","Ahora estará en la tabla siguiente");
                }
            });
            expAValidar.add(Validar,2,i);*/
        }
    }

    private void setExperienciasValidadas(){
        removeRow(true);
        for(int i=0; i < publicVal.size() ; i++){
            expValidadas.addRow(i);
            Text titulo = new Text(publicVal.get(i).getTitle());
            expValidadas.add(titulo,0,i);
            Text operador = new Text(publicVal.get(i).getOperador().getUsername());
            expValidadas.add(operador,1,i);
            Text calificacion = new Text(String.valueOf(publicVal.get(i).getCalification()));
            expValidadas.add(calificacion,2,i);
        }
    }
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void AgregarOp(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(logInOperador.class.getResourceAsStream("LogInOperador.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void removeRow(boolean choose){
        if (choose) {
            while (expValidadas.getRowConstraints().size() > 0) {
                expValidadas.getRowConstraints().remove(0);
            }
        }
        else{
            while (expAValidar.getRowConstraints().size() > 0) {
                expAValidar.getRowConstraints().remove(0);
            }
        }
    }
}

