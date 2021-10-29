package proyecto.ui.component;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;

@Component
public class PaneCalendar {}
    /*
    public Pane getPane(Integer precio, ArrayList<String> horarios, Integer cantPersonas){
        ImageView icono = new ImageView();
        Image image = new Image("user.png");
        icono.setImage(image);
        icono.setFitHeight(25);
        icono.setFitWidth(25);
        icono.setLayoutX(13);
        icono.setLayoutY(30);
        Label precioLab = new Label("$" + precio);
        precioLab.setFont(Font.font("Cambria", 18));
        precioLab.setLayoutX(60);
        precioLab.setLayoutY(49);
        Label selFecha = new Label("Seleccione Fecha");
        selFecha.prefHeight(26);
        selFecha.prefWidth(118);
        selFecha.setFont(Font.font("Arial",18));
        selFecha.setLayoutX(41);
        selFecha.setLayoutY(130);
        DatePicker date = new DatePicker();
        date.setLayoutX(26);
        date.setLayoutY(165);
        date.setPrefWidth(150);
        date.setPrefHeight(25);
        Label selHorario = new Label("Seleccione horario");
        selHorario.prefHeight(131);
        selHorario.prefWidth(26);
        selHorario.setFont(Font.font("Arial",18));
        selHorario.setLayoutX(41);
        selHorario.setLayoutY(130);
        ChoiceBox<String> horariosDisp = new ChoiceBox<>();
        horariosDisp.getItems().addAll(horarios);
        ArrayList<Integer> cantMax = new ArrayList<>();
        cantMax = agregarNumeros(cantPersonas);
        ChoiceBox<Integer> cantidadPer = new ChoiceBox<>();
        cantidadPer.getItems().addAll(cantMax);
        Text precioPorPersonas = new Text("$" + precio + " x " + cantidadPer.getValue());
        precioPorPersonas.setLayoutX(9);
        precioPorPersonas.setLayoutY(392);
        precioPorPersonas.setFont(Font.font("Arial",18));
        Text precioSemiTotal = new Text("$" + precio * cantidadPer.getValue());
        precioSemiTotal.setLayoutY(392);
        precioSemiTotal.setLayoutX(107);
        precioSemiTotal.setFont(Font.font("Arial",18));
        Line linea = new Line();
        line.set
        Pane pane = new Pane();
        pane.setPrefSize(200, 680);
        pane.getChildren().add(precioLab);
        pane.setStyle("-fx-background-color: #"+color);
        return pane;
    }*/

    // Agregar cantidad de personas
     /*private ArrayList<Integer> agregarNumeros(Integer cantMax){
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=1; i<=cantMax;i++){
            arr.add(i);
        }
        return arr;
    }




}

      */
