package proyecto.ui.component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import proyecto.business.entities.Comentary;
import proyecto.business.entities.Publication;
import proyecto.business.entities.Tourist;
import proyecto.business.entities_managers.ComentaryManager;
import proyecto.business.exceptions.ComentaryCreationError;
import proyecto.business.exceptions.DataBaseError;

public class Comentario {

    @Autowired
    private ComentaryManager comManager;

    public static Tourist turistaActual;

    public static Publication publiActual;

    @FXML
    private ChoiceBox<Integer> calificationNumber;

    @FXML
    private TextArea comentarioText;

    @FXML
    private Button guardarComentario;

    public void initialize() {
        AgregarCal();
    }

    private void AgregarCal(){
        for(int i=1;i<=5;i++){
            calificationNumber.getItems().add(i);
        }
    }

    @FXML
    public void CrearComentario(ActionEvent actionEvent) throws ComentaryCreationError, DataBaseError {
        if(comentarioText.getText()==null || calificationNumber.getValue() == null){
            showAlert("Por favor, complete todos los campos.");
        }
        else{
            Comentary newComentario = new Comentary(turistaActual,publiActual,calificationNumber.getValue(),comentarioText.getText());
            comManager.addComentary(newComentario);
            showAlert("Gracias por calificarnos y darnos tu opinión!");
            close(actionEvent);
        }


    }


    private void showAlert(String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
