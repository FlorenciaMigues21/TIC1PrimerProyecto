package proyecto.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.Main;
import proyecto.business.entities.*;
import proyecto.business.entities_managers.ComentaryManager;
import proyecto.business.entities_managers.PublicationManager;
import proyecto.business.entities_managers.ReservationManager;
import proyecto.business.exceptions.DataBaseError;
import proyecto.business.exceptions.InvalidPublicationInformation;
import proyecto.business.exceptions.InvalidUserInformation;
import proyecto.business.exceptions.ReservationCreationError;
import proyecto.ui.component.Comentario;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class experiencePage {


    @Autowired
    private PublicationManager pubManager;

    @Autowired
    private ReservationManager resManager;

    @Autowired
    private ComentaryManager comManager;

    static Tourist userActual;

    static  Publication publicacionActual;

    @FXML
    private Button backButton;

    @FXML
    private Button buttonMenu;

    @FXML
    private Button calendarButton;

    @FXML
    private Button buttonReserva;

    @FXML
    private TextField cantPer;

    @FXML
    private DatePicker fechaReserva;

    @FXML
    private ChoiceBox<Time> horario;

    @FXML
    private Pane paneCal;

    @FXML
    private Text precio;

    @FXML
    private Text total;

    @FXML
    private Label NombreAtraccion;

    @FXML
    private VBox comentarios;

    @FXML
    private Text direccion;

    @FXML
    private VBox higiene;

    @FXML
    private Text infoExp;

    @FXML
    private VBox itemsInc;

    @FXML
    private Button opinion;

    @FXML
    private Label telefono;

    @FXML
    private Label pun;

    @FXML
    private VBox boxComentary;

    private Boolean panelEstado = false;

    /*Collection<Comentary> comentariosCol = comManager.getComentaryOfPublication(publicacionActual);
    ArrayList<Comentary> comentariosList = new ArrayList<>(comentariosCol);*/

    public void initialize() {

        setItems();
        panelReserva();
        paneCal.setVisible(panelEstado);
    }

    //Setea los datos del panel de la experiencia correspondiente
    public void panelReserva(){
        String precioString = String.valueOf(publicacionActual.getCantidad());
        precio.setText(precioString);
        if(cantPer.getText() != null){
            int cantPerInt = Integer.parseInt(cantPer.getText());
            int precioInt = (int) publicacionActual.getCantidad();
            total.setText("$" + String.valueOf(cantPerInt * precioInt));
        }
        else{
            total.setText("0");
        }
        //Horarios

    }

    //Setea los datos de la experiencia correspondiente
    public void setItems(){
        ArrayList<String> arrayPrueba = new ArrayList<String>();
        NombreAtraccion.setText(publicacionActual.getTitle());
        direccion.setText(publicacionActual.getUbication());
        infoExp.setText(publicacionActual.getDescription());
        telefono.setText(publicacionActual.getPhone());
        horario.getItems().addAll(publicacionActual.getHourStart());
        /*float calificacion = Calificacion();*/
        /*puntaje.setText(Float.toString(calificacion));
        AgregarComentarios();*/
        //telefono.setText(publicacionActual.get);
        /*for(int i=0;i<arrayPrueba.size();i++){
            Label userName = new Label();
            userName.setText();
            Text comentario = new Text();
            comentario.setText();
            comentarios.getChildren().add(userName + comentario);
            Label itemHigiene = new Label();
            itemHigiene.setText();
            higiene.getChildren().add(itemHigiene);
            Label itemInc = new Label();
            itemsInc.getChildren().add(itemInc);*/
    }

    //Crea la reserva
    @FXML
    void Reserv(ActionEvent actionEvent) throws InvalidPublicationInformation, DataBaseError, ReservationCreationError, InvalidUserInformation {
        LocalDate datePick = fechaReserva.getValue();
        //ARREGLAR TEMA FECHA @FIXME
        //ARREGLAR TEMA HORARIO
       Collection<Reservation> reser = resManager.getAllReservationFromPublication(publicacionActual);
       ArrayList<Reservation> reservasList = new ArrayList<>(reser);
       int cantDisponible = cantidadReservas(reservasList);
       int cantPersonas = Integer.parseInt(cantPer.getText());
       if(cantDisponible-cantPersonas < 0){
           String cantAux = String.valueOf(cantDisponible-cantPersonas);
           showAlert("No hay lugares disponibles","Quedan " + cantAux + " lugares disponibles.");
       }
        else if(!turistaReservaDisp()){
           showAlert("Ya reservó!","Usted ya reservó, si desea cambiar su reserva, vaya a su itinerario.");
       }
       else{
           Reservation nuevaReserva = new Reservation(userActual,publicacionActual,cantPersonas,horario.getValue());
           resManager.addReservation(nuevaReserva);
           showAlert("Su reserva fue guardada","Puede ver el estado de la reserva en su itinerario");
       }
    }

    //Devuelve la cantidad
    private int cantidadReservas(ArrayList<Reservation> reservas){
        int cantidad = publicacionActual.getAforo();
        for(int i = 0; i < reservas.size();i++){
            cantidad -= reservas.get(i).getCantidad();
        }

        return cantidad;
    }

    //Se fija que el turista no haya recibido ya
    private boolean turistaReservaDisp() throws InvalidUserInformation, DataBaseError {
        Collection<Reservation> reser = resManager.getAllReservationFromTourist(userActual);
        ArrayList<Reservation> reservasList = new ArrayList<>(reser);
        for (Reservation reservation : reservasList) {
            if (reservation.getPublication() == publicacionActual) {
                return false;
            }
        }
        return true;
    }

    //Alerta
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
    /*
    //Calificación
    private float Calificacion(){
        int suma = 0;
        float resultado = 0;
        if(comentariosCol.size()>0) {
            for (int i = 0; i < comentariosCol.size(); i++) {
                suma += comentariosList.get(i).getCalification();
            }
            resultado = suma / comentariosList.size();
        }
        return resultado;
    }*/
/*
    //Agregar comentarios
    private void AgregarComentarios(){
        if(comentariosCol.size()>0) {
            for (int i = 0; i < comentariosCol.size(); i++) {
                Label usuarioNombre = new Label(comentariosList.get(i).getTurista().getUsername());
                Text comentarioText = new Text((comentariosList.get(i).getComantary()));
                VBox section = new VBox();
                section.getChildren().add(usuarioNombre);
                section.getChildren().add(comentarioText);
                boxComentary.getChildren().add(section);
            }
        }
    }*/
    //CONVERSION DEL HORARIO
    private Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    //Subir elementos
    private void UpElements(){
        ArrayList<IncludedInPublication> listInclud = new ArrayList<>(publicacionActual.getIncluido());
        ArrayList<Hygiene> listHigiene = new ArrayList<>(publicacionActual.getMedidas_de_higiene());
        for(int i=0;i<publicacionActual.getIncluido().size();i++){
            Label newItem = new Label(listInclud.get(i).getIncluido());
            itemsInc.getChildren().add(newItem);
        }
        for (int i=0;i<publicacionActual.getMedidas_de_higiene().size();i++){
            Label newItem = new Label(listHigiene.get(i).getMedidas());
            higiene.getChildren().add(newItem);
        }

    }

    //Hacer comentario
    @FXML
    void hacerComentario(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(proyecto.ui.component.Comentario.class.getResourceAsStream("Comentario.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Comentario.publiActual = publicacionActual;
        Comentario.turistaActual = userActual;
    }

    @FXML
    void closeMenu(ActionEvent actionEvent){
        panelEstado = !panelEstado;
    }

    @FXML
    void goHome(ActionEvent actionEvent){

        //@FIXME
    }

    @FXML
    void goBack(ActionEvent actionEvent){
        //@FIXME
    }
}
