package proyecto.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import proyecto.Main;
import proyecto.ui.admin.logInOperador;

import java.io.IOException;

public class alertSign {


    @FXML
    private Button Next;

    @FXML
    private ChoiceBox<String> tipo;

    public void initialize(){
        upData();
    }

    private void upData(){
        tipo.getItems().addAll("Operador Turístico","Turista");
    }

    void NextTourist() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(signUpMenu.class.getResourceAsStream("LogIn.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void NextOperator() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(logInOperador.class.getResourceAsStream("LogInOperador.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        //MainOperator.operador = operador;
    }

    @FXML
    void Next(ActionEvent event) throws IOException {
        Stage stage2 = (Stage) this.Next.getScene().getWindow();
        stage2.close();
        if (tipo.getValue().equals("Operador Turístico")){
            NextOperator();
        }
        else if(tipo.getValue().equals("Turista")){
            NextTourist();
        }


    }

}
