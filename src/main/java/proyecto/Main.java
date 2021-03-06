package proyecto;

import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import proyecto.business.entities_managers.Busqueda.IndexingService;
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
