package proyecto;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import proyecto.ui.JavaFXApplication;

@SpringBootApplication
public class Main {


    private static ConfigurableApplicationContext context;

    public static void main(String[] args){
        Main.context = SpringApplication.run(Main.class);
        Application.launch(JavaFXApplication.class, args);
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }

}
