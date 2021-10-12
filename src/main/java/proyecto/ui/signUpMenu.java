package proyecto.ui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import proyecto.Main;
import proyecto.business.entities.Country;
import proyecto.business.entities.Tourist;
import proyecto.business.entities.Typeofactivities;
import proyecto.business.entities_managers.TouristManager;
import proyecto.business.exceptions.InvalidUserInformation;
import proyecto.business.exceptions.UserAlreadyExist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class signUpMenu {

    @Autowired
    private TouristManager controlador;

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
    private TextField telefono;

    @FXML
    private Button atras;

    @FXML
    private ComboBox<String> pais;

    ObservableList<String> countries = FXCollections.observableArrayList("Afghanistan","Albania","Algeria","Andorra","Angola","Anguilla","Antigua &amp; Barbuda","Argentina","Armenia","Aruba","Australia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia","Bosnia &amp; Herzegovina","Botswana","Brazil","British Virgin Islands","Brunei","Bulgaria","Burkina Faso","Burundi","Cambodia","Cameroon","Cape Verde","Cayman Islands","Chad","Chile","China","Colombia","Congo","Cook Islands","Costa Rica","Cote D Ivoire","Croatia","Cruise Ship","Cuba","Cyprus","Czech Republic","Denmark","Djibouti","Dominica","Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea","Estonia","Ethiopia","Falkland Islands","Faroe Islands","Fiji","Finland","France","French Polynesia","French West Indies","Gabon","Gambia","Georgia","Germany","Ghana","Gibraltar","Greece","Greenland","Grenada","Guam","Guatemala","Guernsey","Guinea","Guinea Bissau","Guyana","Haiti","Honduras","Hong Kong","Hungary","Iceland","India","Indonesia","Iran","Iraq","Ireland","Isle of Man","Israel","Italy","Jamaica","Japan","Jersey","Jordan","Kazakhstan","Kenya","Kuwait","Kyrgyz Republic","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macau","Macedonia","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Mauritania","Mauritius","Mexico","Moldova","Monaco","Mongolia","Montenegro","Montserrat","Morocco","Mozambique","Namibia","Nepal","Netherlands","Netherlands Antilles","New Caledonia","New Zealand","Nicaragua","Niger","Nigeria","Norway","Oman","Pakistan","Palestine","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal","Puerto Rico","Qatar","Reunion","Romania","Russia","Rwanda","Saint Pierre &amp; Miquelon","Samoa","San Marino","Satellite","Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone","Singapore","Slovakia","Slovenia","South Africa","South Korea","Spain","Sri Lanka","St Kitts &amp; Nevis","St Lucia","St Vincent","St. Lucia","Sudan","Suriname","Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand","Timor L'Este","Togo","Tonga","Trinidad &amp; Tobago","Tunisia","Turkey","Turkmenistan","Turks &amp; Caicos","Uganda","Ukraine","United Arab Emirates","United Kingdom","Uruguay","Uzbekistan","Venezuela","Vietnam","Virgin Islands (US)","Yemen","Zambia","Zimbabwe");


    public void initialize() {
        addCountries();
    }

    private void addCountries(){
        pais.getItems().addAll(countries);
    }

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }


    @FXML
    void Back(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Stage stage2 = (Stage) this.btnSignUp.getScene().getWindow();
        stage2.close();
        Parent root = fxmlLoader.load(MenuInicial.class.getResourceAsStream("principalPage.fxml"));
        Scene scene = new Scene(root,700,400);
        scene.getStylesheets().add(getClass().getResource("LogInStyle.css").toExternalForm());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }


    void Next() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(selectionTurist.class.getResourceAsStream("SelectionTurist.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
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
                String pais_residente = pais.getValue();
                Date fecha = Date.valueOf("2000-03-08");
                Collection<Typeofactivities> actividades = new ArrayList<>();
                actividades.add(new Typeofactivities("Sky"));
                if (passwordUser.equals(ConfirmPassword)) {
                    controlador.addTourist(new Tourist(passwordUser,name,mail,false,95684021,new Country("Uruguay"),true,fecha,actividades));
                    showAlert("Tu cuenta fue creada!");
                    Next();

                }else{
                    showAlert("Incorrect passwords","Incorrect passwords");
                }
            } catch (NumberFormatException e) {

                showAlert(
                        "Invalid Information",
                        "Please try again");

            } catch (InvalidUserInformation | UserAlreadyExist | IOException invalidUserInformation) {
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
