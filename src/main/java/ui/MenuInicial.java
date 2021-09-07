package ui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuInicial {


    @FXML
    private Button btnNext;

    @FXML
    private TextField passworrd;

    @FXML
    private TextField username;

    @FXML
    private Button btnSignUp;

    @FXML
    void SignIn(ActionEvent event) {
        if (passworrd.getText() == null || passworrd.getText().equals("") ||
                username.getText() == null || username.getText().equals("")) {

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {

            try {

                Long document = Long.valueOf(passworrd.getText());
                String name = txtName.getText();
                String address = username.getText();

                try {

                    clientMgr.addClient(document, name, address);

                    showAlert("Cliente agregado", "Se agrego con exito el cliente!");

                    close(event);
                } catch (InvalidClientInformation invalidClientInformation) {
                    showAlert(
                            "Informacion invalida !",
                            "Se encontro un error en los datos ingresados.");
                } catch (ClientAlreadyExists clientAlreadyExists) {
                    showAlert(
                            "Documento ya registrado !",
                            "El documento indicado ya ha sido registrado en el sistema).");
                }

            } catch (NumberFormatException e) {

                showAlert(
                        "Datos incorrectos !",
                        "El documento no tiene el formato esperado (numerico).");

            }
        }

    }
}
