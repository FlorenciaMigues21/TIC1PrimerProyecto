package proyecto.ui;


import javafx.scene.control.ChoiceBox;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
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
import proyecto.business.entities_managers.Busqueda.IndexingService;
import proyecto.business.entities_managers.UserManager;
import proyecto.business.exceptions.InvalidUserInformation;
import proyecto.business.exceptions.UserNotFound;
import proyecto.ui.admin.Admin_init;
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
    @Autowired
    private IndexingService indMan;

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
        try {
            indMan.initiateIndexing();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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


    @FXML
    void NextTourist(Tourist turista) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(Inicio.class.getResourceAsStream("Inicio.fxml"));
        Stage stage = new Stage();
        stage.setUserData(turista);
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void NextOperator(TouristOperator operadorTur) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(MainOperator.class.getResourceAsStream("ReservationView.fxml"));
        Stage stage = new Stage();
        stage.setUserData(operadorTur);
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void NextAdmin(Admin user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(Admin_init.class.getResourceAsStream("mainAdmin.fxml"));
        Stage stage = new Stage();
        stage.setUserData(user);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void btnNext(ActionEvent event) throws IOException {

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
                        NextTourist(userSignIn);
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
                        System.out.println(userSignIn);
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
                        NextAdmin(userSignIn);

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

