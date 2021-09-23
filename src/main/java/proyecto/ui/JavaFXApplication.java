package proyecto.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.Main;
import proyecto.business.entities_managers.UserController;


public class JavaFXApplication extends Application  {

    private Parent root;



    @Override
    public void init() throws Exception {

    }


    @Override //Falta main
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        root = fxmlLoader.load(MenuInicial.class.getResourceAsStream("principalPage.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    @Override
    public void stop() {
        Main.getContext().close();

    }
}
