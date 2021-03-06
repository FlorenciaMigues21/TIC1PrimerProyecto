package proyecto.ui.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.business.entities.TouristOperator;
import proyecto.business.entities_managers.UserManager;
import proyecto.business.exceptions.InvalidUserInformation;
import proyecto.business.exceptions.UserAlreadyExist;
import proyecto.business.exceptions.UserNotFound;

@Component
public class delete {

    @Autowired
    UserManager<TouristOperator> userMan;

    @FXML
    private Button eliminar;

    @FXML
    private TextField mail;

    public void initialize(){

    }

    @FXML
    void eliminar(ActionEvent event){
        try {
            TouristOperator operador = userMan.findByMail(mail.getText());
            userMan.deleteUser(operador);
            showAlert("Eliminado","Operador eliminado");
            Stage stage2 = (Stage) this.eliminar.getScene().getWindow();
            stage2.close();
        } catch (InvalidUserInformation | UserNotFound e) {
            showAlert("No se encontró el operaodor turístico","Verifique si no se ha eliminado ya");
            Stage stage2 = (Stage) this.eliminar.getScene().getWindow();
            stage2.close();
        } catch (UserAlreadyExist userAlreadyExist) {
            userAlreadyExist.printStackTrace();
            Stage stage2 = (Stage) this.eliminar.getScene().getWindow();
            stage2.close();
        }


    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}
