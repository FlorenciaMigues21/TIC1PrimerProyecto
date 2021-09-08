package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class JavaFXApplication extends Application {

    private static ConfigurableApplicationContext applicationContext;

    static Stage primaryStage;
    private Parent root;
    /*@Autowired
    /*private PersonController personController; //Conexion con la base de datos*/

    @Override
    public void init() throws Exception{
        /*String[] args = getParameters().getRaw().toArray(new String[0]);

        /*this.applicationContext = new SpringApplicationBuilder()
                .sources(EjemploTICGui.class)
                .run(args);*/
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        JavaFXApplication.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        root = fxmlLoader.load(Principal.class.getResourceAsStream("principalPage.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @Override
    public void stop() {
        Main.getContext().close();

    }
}
