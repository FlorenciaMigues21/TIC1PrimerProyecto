package proyecto.ui.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.Main;
import proyecto.business.entities.Country;
import proyecto.business.entities.Tourist;
import proyecto.business.entities.TouristOperator;
import proyecto.business.entities_managers.UserManager;
import proyecto.business.exceptions.InvalidUserInformation;
import proyecto.business.exceptions.UserAlreadyExist;
import proyecto.ui.operator.MainOperator;

import java.io.IOException;
import java.sql.Date;

@Component
public class logInOperador {

    @Autowired
    UserManager<TouristOperator> userMan;

    @FXML
    private Button atras;

    @FXML
    private Button btnSignUp;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private TextField email;

    @FXML
    private TextField nombreOp;

    @FXML
    private PasswordField password;

    @FXML
    private TextField telefono;

    @FXML
    private TextField username;

    void Next() throws IOException {
        Stage stage2 = (Stage) this.btnSignUp.getScene().getWindow();
        stage2.close();
    }

    @FXML
    void SignUp(ActionEvent event) {

        if (password.getText() == null || password.getText().equals("") ||
                username.getText() == null || username.getText().equals("")
                || confirmPassword.getText() == null || confirmPassword.getText().equals("")
                || email.getText() == null || email.getText().equals("") || nombreOp.getText() == null || nombreOp.getText().equals("")) {

            showAlert(
                    "Error",
                    "Por favor, complete todos los espacios");

        } else {

            try {

                String name = username.getText();
                String passwordUser = password.getText();
                String ConfirmPassword = confirmPassword.getText();
                String mail = email.getText();
                String tel = telefono.getText();
                String nombre = nombreOp.getText();
                if (passwordUser.equals(ConfirmPassword)) {
                    TouristOperator operador = new TouristOperator(passwordUser,name,mail,false,tel,false,0,nombre);
                    userMan.addUser(operador);
                    Next();

                }else{
                    showAlert("Incorrect passwords","Incorrect passwords");
                }
            } catch (NumberFormatException | InvalidUserInformation | UserAlreadyExist e) {

                showAlert(
                        "Invalid Information",
                        "Please try again");

            } catch (IOException invalidUserInformation) {
                invalidUserInformation.printStackTrace();
            }
        }
    }
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
    private void showAlert(String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
