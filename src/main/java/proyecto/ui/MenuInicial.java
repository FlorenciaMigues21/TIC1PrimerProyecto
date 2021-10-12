package proyecto.ui;


import org.springframework.beans.factory.annotation.Autowired;
import proyecto.Main;
import proyecto.business.entities.Tourist;
import proyecto.business.entities_managers.TouristManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import proyecto.business.entities_managers.UserManager;
import proyecto.business.exceptions.InvalidUserInformation;
import proyecto.business.exceptions.UserNotFound;

import java.io.IOException;


@Component
public class MenuInicial {

    @Autowired
    public UserManager<Tourist> controlador;

    @FXML
    private Button btnNext;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    private Button btnSignUp;

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnNext(ActionEvent event) {

        if (password.getText() == null || password.getText().equals("") ||
                username.getText() == null || username.getText().equals("")) {

            showAlert(
                    "Error",
                    "Please, complete all the fields");

        } else {

            try {

                String mail = username.getText();
                String passowrdUser = password.getText();

                try {

                    Tourist userSignIn = controlador.logIn(new Tourist(passowrdUser,mail));

                    if (userSignIn.getPassword().equals(passowrdUser))
                        showAlert("Login successful!" , "You have successfully signed into your count. You can close this window and continue using the product");

                    close(event);
                } catch (InvalidUserInformation | UserNotFound error) {
                    error.printStackTrace();
                    showAlert(
                            error.getMessage(),
                            "Please try again");
                }

            } catch (NumberFormatException e) {

                showAlert(
                        "Invalid Email or password",
                        "Please try again");

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

    @FXML
    void btnSignUp(ActionEvent event) throws IOException {
        Stage stage2 = (Stage) this.btnSignUp.getScene().getWindow();
        stage2.close();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(signUpMenu.class.getResourceAsStream("LogIn.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

