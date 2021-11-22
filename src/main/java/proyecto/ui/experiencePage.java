package proyecto.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.Main;
import proyecto.business.entities.*;
import proyecto.business.entities_managers.ComentaryManager;
import proyecto.business.entities_managers.PublicationManager;
import proyecto.business.entities_managers.ReservationManager;
import proyecto.business.exceptions.*;
import proyecto.business.utils.Utilities;
import proyecto.ui.component.Comentario;

import java.awt.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

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
    private AnchorPane exper;

    @FXML
    private Label NombreAtraccion;

    @FXML
    private Button backButton;

    @FXML
    private VBox boxComentary;

    @FXML
    private Button buttonMenu;

    @FXML
    private Button buttonReserva;

    @FXML
    private Button calendarButton;

    @FXML
    private TextField cantPer;

    @FXML
    private VBox comentarios;

    @FXML
    private Text direccion;

    @FXML
    private DatePicker fechaReserva;

    @FXML
    private VBox higiene;

    @FXML
    private Button homeButton;

    @FXML
    private ChoiceBox<String> horario;

    @FXML
    private ImageView imagen1;

    @FXML
    private ImageView imagen2;

    @FXML
    private ImageView imagen3;

    @FXML
    private ImageView imagen4;

    @FXML
    private ImageView imagen5;

    @FXML
    private Text infoExp;

    @FXML
    private VBox itemsInc;

    @FXML
    private Button opinion;

    @FXML
    private Pane paneCal;

    @FXML
    private Text precio;

    @FXML
    private Label pun;

    @FXML
    private Label telefono;

    @FXML
    private Text total;

    @FXML
    Button calcular;
    boolean panelEstado = true;

    public void initialize() throws DataBaseError, IncompleteObjectException, IOException {
        exper.sceneProperty().addListener(((observable, oldValue, newValue) -> {
            if (oldValue == null && newValue != null) {
                newValue.windowProperty().addListener((observable1, oldValue1, newValue1) -> {
                            if (oldValue1 == null && newValue1 != null) {
                                Stage stage = (Stage) exper.getScene().getWindow();
                                doubleObjet db = (doubleObjet) stage.getUserData();
                                userActual = db.getTurista();
                                publicacionActual = db.getPublicacion();
                                panelReserva();
                                try {
                                    setItems();
                                } catch (IncompleteObjectException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                if (!publicacionActual.isReservationAvailable()) {
                                    buttonReserva.setVisible(false);
                                }
                                paneCal.setVisible(panelEstado);

                            }
                        }
                );
            }
        }
        ));

    }
    public void calcularTotal(ActionEvent event){
        int cantPerInt = Integer.parseInt(cantPer.getText());
        int precioInt = (int) publicacionActual.getPrecio();
        total.setText("$" + cantPerInt * precioInt);
    }
    //Setea los datos del panel de la experiencia correspondiente
    public void panelReserva(){
        String precioString = String.valueOf(publicacionActual.getPrecio());
        precio.setText(precioString);

        arreglarDate();
        subirHorarios();

    }

    private void arreglarDate(){
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {

                // deshabilitar las fechas futuras
                if (item.isAfter(LocalDate.ofInstant(publicacionActual.getDateto().toInstant(), ZoneId.systemDefault()))){
                    this.setDisable(false);
                }
                if (item.isBefore(LocalDate.ofInstant(publicacionActual.getDatefrom().toInstant(), ZoneId.systemDefault()))){
                    this.setDisable(false);
                }
            }
        };
        fechaReserva.setDayCellFactory(dayCellFactory);
    }

    //Setea los datos de la experiencia correspondiente
    public void setItems() throws IncompleteObjectException, IOException {
        try {
            ArrayList<String> arrayPrueba = new ArrayList<>();
            NombreAtraccion.setText(publicacionActual.getTitle());
            direccion.setText(publicacionActual.getUbication());
            infoExp.setText(publicacionActual.getDescription());
            telefono.setText(publicacionActual.getPhone());
            pun.setText(String.valueOf(publicacionActual.getCalification()));
            AgregarComentarios();
        } catch (DataBaseError e) {
            e.printStackTrace();
        }
        subirHorarios();
        UpPhotos();
        UpElements();

    }

    //Subir fotos
    private void UpPhotos() throws IOException {
        try {
            Set<Photo> fotos = publicacionActual.getPhotoList();
            Iterator<Photo> it = fotos.iterator();
            Image newImage1 = it.next().getImageFromByteArray(276, 214);
            imagen1.setImage(newImage1);
            Image newImage2 = it.next().getImageFromByteArray(142, 100);
            imagen2.setImage(newImage2);
            Image newImage3 = it.next().getImageFromByteArray(142, 100);
            imagen3.setImage(newImage3);
            Image newImage4 = it.next().getImageFromByteArray(142, 100);
            imagen4.setImage(newImage4);
            Image newImage5 = it.next().getImageFromByteArray(142, 100);
            imagen5.setImage(newImage5);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //Crea la reserva
    @FXML
    void Reserv(ActionEvent actionEvent) throws InvalidPublicationInformation, DataBaseError, ReservationCreationError, InvalidUserInformation {
        LocalDate datePick = fechaReserva.getValue();
        java.util.Date newDate = Utilities.createDate(datePick.getYear(), datePick.getMonthValue(),datePick.getDayOfMonth());
        Integer hora = horarioSelect();
        if(VerificReservation(hora)) {
            //Reservation newReser = new Reservation(userActual, publicacionActual, Integer.parseInt(cantPer.getText()), hora);
            showAlert("Su reserva fue guardada", "Puede ver el estado de la reserva en su itinerario");
            //resManager.addReservation(newReser);
        }
        else if(!turistaReservaDisp()){
           showAlert("Ya reservó!","Usted ya reservó, si desea cambiar su reserva, vaya a su itinerario.");
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
    //Agregar comentarios
    private void AgregarComentarios() throws DataBaseError, IncompleteObjectException {
        boxComentary.getChildren().clear();

        ArrayList<Comentary> comentarios = new ArrayList<>(comManager.getComentaryOfPublication(publicacionActual));
        if(comentarios.size()>0) {
            for (int i = 0; i < comentarios.size(); i++) {
                Label usuarioNombre = new Label(comentarios.get(i).getTurista().getUsername());
                usuarioNombre.setFont(Font.font(null, FontWeight.BOLD, 15));
                usuarioNombre.setTextFill(Color.BLUE);
                Text comentarioText = new Text((comentarios.get(i).getComantary()));
                VBox section = new VBox();
                String cssLayout = "-fx-border-color: black;\n" +
                        "-fx-border-width: 0.5;\n";

                section.setStyle(cssLayout);
                section.getChildren().add(usuarioNombre);
                section.getChildren().add(comentarioText);
                boxComentary.getChildren().add(section);
            }
        }
    }
    //CONVERSION DEL HORARIO
    private Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    //Subir elementos
    private void UpElements(){
        Set<IncludedInPublication> listInclud = publicacionActual.getIncluido();
        Set<Hygiene> listHigiene = publicacionActual.getMedidas_de_higiene();
        for(IncludedInPublication inc: listInclud){
            Label newItem = new Label(inc.getIncluido());
            itemsInc.getChildren().add(newItem);
        }
        for (Hygiene hig: listHigiene){
            Label newItem = new Label(hig.getMedidas());
            higiene.getChildren().add(newItem);
        }

    }

    //Hacer comentario
    @FXML
    void hacerComentario(ActionEvent actionEvent) throws IOException, DataBaseError, IncompleteObjectException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(proyecto.ui.component.Comentario.class.getResourceAsStream("Comentario.fxml"));
        Stage stage = new Stage();
        doubleObjet db = new doubleObjet();
        db.setPublicacion(publicacionActual);
        db.setTurista(userActual);
        stage.setUserData(db);
        stage.setScene(new Scene(root));
        stage.show();
        AgregarComentarios();
    }

    @FXML
    void closeMenu(ActionEvent actionEvent){
        panelEstado = !panelEstado;
    }

    @FXML
    void goHome(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(Inicio.class.getResourceAsStream("Inicio.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setUserData(userActual);
        stage.show();
        Stage stage2 = (Stage) this.exper.getScene().getWindow();
        stage2.close();
    }

    @FXML
    void goBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(Inicio.class.getResourceAsStream("Inicio.fxml"));
        Stage stage = new Stage();
        stage.setUserData(userActual);
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) this.exper.getScene().getWindow();
        stage2.close();
    }

    private void subirHorarios(){
        for(int i = publicacionActual.getHourStart(); i<publicacionActual.getHourFinish();i++){
            horario.getItems().add(i+":00");
        }
    }

    private Integer horarioSelect(){
        return Integer.parseInt(horario.getValue().substring(0,2));
    }

    private boolean VerificReservation(Integer hora) throws InvalidPublicationInformation, DataBaseError {
        Collection<Reservation> reser = resManager.getAllReservationFromPublication(publicacionActual);
        ArrayList<Reservation> reservasList = new ArrayList<>(reser);
        Integer aforo = publicacionActual.getAforo();
        for(int i = 0; i<reservasList.size();i++){
            if(reservasList.get(i).getHourStart() == hora){
                aforo -= reservasList.get(i).getHourStart();
            }
        }
        if(aforo - Integer.parseInt(cantPer.getText()) >= 0){
            return true;
        }
        else{
            return false;
        }
    }


}
