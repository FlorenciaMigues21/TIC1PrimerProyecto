package proyecto.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import proyecto.Main;

import java.awt.*;


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
        Scene scene = new Scene(root,700,400);
        //scene.getStylesheets().add(getClass().getResource("LogInStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    @Override
    public void stop() {
        Main.getContext().close();

    }
}
