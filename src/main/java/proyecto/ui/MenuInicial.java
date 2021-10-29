package proyecto.ui;


import javafx.scene.control.ChoiceBox;
import org.springframework.beans.factory.annotation.Autowired;
import proyecto.Main;
import proyecto.business.entities.Admin;
import proyecto.business.entities.Tourist;
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
import proyecto.business.entities.TouristOperator;
import proyecto.business.entities_managers.UserManager;
import proyecto.business.exceptions.InvalidUserInformation;
import proyecto.business.exceptions.UserNotFound;
import proyecto.ui.operator.MainOperator;

import java.io.IOException;


@Component
public class MenuInicial {

    @Autowired
    public UserManager<Tourist> controlador;

    @Autowired
    public UserManager<TouristOperator> controladorOp;

    @Autowired
    public UserManager<Admin> controladorAdmin;

    @FXML
    private Button btnNext;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    private Button btnSignUp;

    @FXML
    private ChoiceBox<String> tipoUsuario;

    public void initialize() {
        loadTipoUsuario();
    }

    private void loadTipoUsuario(){
        String turista = "Turista";
        String operador = "Operador Turistico";
        String admin = "Administrador";
        tipoUsuario.getItems().addAll(turista,operador,admin);
    }

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }


    /*@FIXME
    void NextTourist(Tourist turista) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(selectionTurist.class.getResourceAsStream("SelectionTurist.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        selectionTurist.userActual = turista;
    }*/
    @FXML
    void NextOperator(TouristOperator operador) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(MainOperator.class.getResourceAsStream("ReservationView.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        MainOperator.operador = operador;
    }
    /*@FXML@FIXME
    void NextAdmin(Admin administrador) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(MainOperator.class.getResourceAsStream("ReservationView.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        MainOperator.operador = operador;
    }
    */
    @FXML
    void btnNext(ActionEvent event) {

        if (password.getText() == null || password.getText().equals("") ||
                username.getText() == null || username.getText().equals("") || tipoUsuario.getValue() == null) {

            showAlert(
                    "Error",
                    "Por favor, complete todos los datos");

        } else {

            String mail = username.getText();
            String passowrdUser = password.getText();
            if (tipoUsuario.getValue().equals("Turista")) {
                try {
                    Tourist userSignIn = controlador.logIn(new Tourist(passowrdUser, mail));
                    if (userSignIn.getPassword().equals(passowrdUser)) {

                        showAlert("Acceso correcto!");
                        close(event);

                    }else {
                        showAlert("Datos incorrectos", "Verifique el mail y la contraseña");
                    }
                }
                catch (InvalidUserInformation | UserNotFound e) {
                        e.printStackTrace();
                        showAlert(
                                e.getMessage(),
                                "Por favor, intente de nuevo.");
                    }
                } else if (tipoUsuario.getValue().equals("Operador Turistico")) {
                    try {
                        TouristOperator userSignIn = controladorOp.logIn(new TouristOperator(passowrdUser, mail));
                        if (userSignIn.getPassword().equals(passowrdUser)) {

                            showAlert("Acceso correcto!");
                            NextOperator(userSignIn);
                            close(event);

                        }else {
                            showAlert("Datos incorrectos", "Verifique el mail y la contraseña");
                        }
                    }
                    catch (InvalidUserInformation | UserNotFound | IOException e) {
                        e.printStackTrace();
                        showAlert(
                                e.getMessage(),
                                "Por favor, intente de nuevo.");
                    }

                }
            else{
                try {
                    Admin userSignIn = controladorAdmin.logIn(new Admin(passowrdUser, mail));
                    if (userSignIn.getPassword().equals(passowrdUser)) {

                        showAlert("Acceso correcto!");
                        close(event);

                    }else {
                        showAlert("Datos incorrectos", "Verifique el mail y la contraseña");
                    }
                }
                catch (InvalidUserInformation | UserNotFound e) {
                    e.printStackTrace();
                    showAlert(
                            e.getMessage(),
                            "Por favor, intente de nuevo.");
                }
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

