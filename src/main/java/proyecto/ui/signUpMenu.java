package proyecto.ui;


import org.springframework.beans.factory.annotation.Autowired;
import proyecto.business.entities.User;
import proyecto.business.entities_managers.UserManager;
import proyecto.business.exceptions.InvalidUserInformation;
import proyecto.business.exceptions.UserAlreadyExist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class signUpMenu {

    @Autowired
    private UserManager controlador;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    private TextField confirmPassword;

    @FXML
    private Button btnSignUp;

    @FXML
    private TextField pais;

    @FXML
    private TextField telefono;

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void SignUp(ActionEvent event) {

        if (password.getText() == null || password.getText().equals("") ||
                username.getText() == null || username.getText().equals("")
                || confirmPassword.getText() == null || confirmPassword.getText().equals("")
                || email.getText() == null || email.getText().equals("")) {

            showAlert(
                    "Error",
                    "Please, complete all the fields");

        } else {

            try {

                String name = username.getText();
                String passwordUser = password.getText();
                String ConfirmPassword = confirmPassword.getText();
                String mail = email.getText();
                String tel = telefono.getText();
                if (passwordUser.equals(ConfirmPassword)) {
                    controlador.addUser(mail, passwordUser,ConfirmPassword, name);
                    showAlert("Your account was created!", "Please login to access");
                    close(event);
                }else{
                    showAlert("Incorrect passwords","Incorrect passwords");
                }
            } catch (NumberFormatException e) {

                showAlert(
                        "Invalid Information",
                        "Please try again");

            } catch (InvalidUserInformation | UserAlreadyExist invalidUserInformation) {
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


}
