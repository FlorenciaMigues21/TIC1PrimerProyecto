package proyecto.ui.operator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.scene.text.Text;
import proyecto.Main;
import proyecto.business.entities.*;
import proyecto.business.entities_managers.PhotoManager;
import proyecto.business.entities_managers.PublicationManager;
import proyecto.business.entities_managers.TypeofactivitiesManager;
import javafx.stage.Stage;
import proyecto.business.exceptions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;


@Component
public class ExperienceDisplayOperator{
    ObservableList<String> gusto = FXCollections.observableArrayList("Viaje Confort","Viaje alternativo","Viaje de Lujo");

    @Autowired
    private PublicationManager publicMan;

    @Autowired
    private TypeofactivitiesManager manType;

    @Autowired
    private PhotoManager photoMan;


    public static TouristOperator operador;

    @FXML
    private AnchorPane ap;

    @FXML
    private TextField Horario;

    @FXML
    private TextField aforo;

    @FXML
    private Button agregarAsp;

    @FXML
    private Button agregarHorario;

    @FXML
    private Button agregarMedida;

    @FXML
    private Button back;

    @FXML
    private CheckBox boolreserv;

    @FXML
    private Button cargaImagenes;

    @FXML
    private TextArea descripcion;

    @FXML
    private TextField direccion;

    @FXML
    private DatePicker fechaFin;

    @FXML
    private DatePicker fechaIni;

    @FXML
    private ChoiceBox<String> gustoViaje;

    @FXML
    private TextField horarioMins;

    @FXML
    private VBox horarios;

    @FXML
    private CheckBox invierno;

    @FXML
    private TextField itemInc;

    @FXML
    private TextField itemMed;

    @FXML
    private RadioButton no;

    @FXML
    private CheckBox otono;

    @FXML
    private TextField precio;

    @FXML
    private CheckBox primavera;

    @FXML
    private Button save;

    @FXML
    private RadioButton si;

    @FXML
    private TextField telefono;

    @FXML
    private TextField titulo;

    @FXML
    private VBox typeList;

    @FXML
    private ScrollPane typeScroll;

    @FXML
    private CheckBox verano;

    @FXML
    private VBox aspIncluidos;

    @FXML
    private VBox higieneInc;

    @FXML
    private TextField Horario1;

    @FXML
    private TextField horarioMins1;



    ArrayList<Hygiene> listasHigiene= new ArrayList<>();
    ArrayList<IncludedInPublication> listasIncluidos = new ArrayList<>();
    ArrayList<Typeofactivities> tipoActividad = new ArrayList<>();
    Collection<Photo> listaFotos;

    @FXML
    public void initialize() throws InvalidUserInformation, PublicationsLoadError, InvalidPublicationInformation, DataBaseError {
        ap.sceneProperty().addListener(((observable, oldValue, newValue) -> {
                    if (oldValue == null && newValue != null) {
                        newValue.windowProperty().addListener((observable1, oldValue1, newValue1) -> {
                                    if (oldValue1 == null && newValue1 != null) {
                                        Stage stage = (Stage) ap.getScene().getWindow();
                                        operador = (TouristOperator) stage.getUserData();
                                        loadEstacion();
                                    }
                                }
                        );
                    }
                }
                ));
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(MainOperator.class.getResourceAsStream("ReservationView.fxml"));
        Stage stage = new Stage();
        stage.setUserData(operador);
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage2 = (Stage) this.ap.getScene().getWindow();
        stage2.close();
    }

    //GUARDA INFORMACION
    public void saveExperience(ActionEvent actionEvent) throws IOException, ParseException, PublicationCreationError {
        if (titulo.getText() == null || precio.getText().equals("") ||
                descripcion.getText() == null || higieneInc.getChildren().size() == 0 || higieneInc.getChildren().size() == 0 ||
                fechaIni.getValue() == null || fechaFin.getValue() == null || direccion.getText() == null || direccion.getText().equals("")) {
            showAlert(
                    "Error",
                    "Por favor, complete todos los campos");
        }


        else{
            Publication newPublication = new Publication();
            if(aforo.getText() == null){
                newPublication.setAforo(0);
            }
            else{
                boolean esInt = integerInvalido(aforo.getText());
                if(esInt){
                    newPublication.setAforo(Integer.parseInt(precio.getText()));
                }
            }
            boolean esPrecioInt = integerInvalido(precio.getText());


            if(esPrecioInt){
                newPublication.setCantidad(Float.parseFloat(precio.getText()));
            }
            newPublication.setPrecio(Integer.parseInt(precio.getText()));
            newPublication.setTitle(titulo.getText());
            newPublication.setOperador(operador);
            newPublication.setPhone(telefono.getText());
            newPublication.setUbication(direccion.getText());
            newPublication.setDescription(descripcion.getText());
            newPublication.setDatefrom(convertToDateViaSqlDate(fechaIni.getValue()));
            newPublication.setDateto(convertToDateViaSqlDate(fechaFin.getValue()));
            newPublication.setMedidas_de_higiene(listasHigiene);
            newPublication.setIncluido(listasIncluidos);
            subaTipoAct();
            choiceBoxOption();
            estaciones();
            newPublication.setListaActividadades(tipoActividad);
            newPublication.setPhotoList(listaFotos);

            if (si.isSelected()){
                newPublication.setReservationAvailable(true);
                if (boolreserv.isSelected()){
                    newPublication.setUniqueReservation(false);
                    newPublication.setHourStart(convertTime(Horario.getText(),horarioMins.getText()));
                    newPublication.setHourStart(convertTime(Horario1.getText(),horarioMins1.getText()));
                }
                else{
                    newPublication.setUniqueReservation(true);
                }
            }else{
                newPublication.setReservationAvailable(false);
                newPublication.setUniqueReservation(false);
            }
            goBack(actionEvent);
            publicMan.createAndUpdatePublication(newPublication);
        }
    }


    private void loadEstacion() {
        gustoViaje.getItems().addAll(gusto);
        ArrayList<Typeofactivities> listType = new ArrayList<>(manType.getAllActivityTypes());
        for (Typeofactivities typeofactivities : listType) {
            if (!typeofactivities.getName().equals("Verano") && !typeofactivities.getName().equals("Invierno") && !typeofactivities.getName().equals("Otoño") && !typeofactivities.getName().equals("Primavera")
                    && !typeofactivities.getName().equals("Confort") && !typeofactivities.getName().equals("Alternativo") && !typeofactivities.getName().equals("Lujo")){
                CheckBox type = new CheckBox();
                type.setId(typeofactivities.getName());
                type.setText(typeofactivities.getName());
                typeList.getChildren().add(type);
            }
        }
    }
    private void subaTipoAct(){
        for (int i = 0; i < typeList.getChildren().size();i++){
            CheckBox actual = (CheckBox)typeList.getChildren().get(i);
            if (actual.isSelected()){
                tipoActividad.add(new Typeofactivities(actual.getId()));
            }
        }
    }

    private void choiceBoxOption(){
        if(gustoViaje.getValue().toString().equals("Viaje Confort") ){
            tipoActividad.add(new Typeofactivities("Viaje Confort"));
        }
        else if(gustoViaje.getValue().toString().equals("Viaje alternativo")){
            tipoActividad.add(new Typeofactivities("Viaje alternativo"));
        }
        else if(gustoViaje.getValue().toString().equals("Viaje de Lujo")) {
            tipoActividad.add(new Typeofactivities("Viaje de Lujo"));
        }
    }
    private void estaciones(){
        if(invierno.isSelected()){
            tipoActividad.add(new Typeofactivities("Invierno"));
        }
        else if(verano.isSelected()){
            tipoActividad.add(new Typeofactivities("Verano"));
        }
        else if(otono.isSelected()){
            tipoActividad.add(new Typeofactivities("Otoño"));
        }
        else if(primavera.isSelected()){
            tipoActividad.add(new Typeofactivities("Primavera"));
        }
    }




    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    //Validar un integer
    private boolean integerInvalido(String text){
        boolean resultado = false;
        try{
            Integer.parseInt(precio.getText());
            resultado = true;
        }catch (NumberFormatException exception){
            showAlert("Error","Por favor, complete el precio y en caso de tener aforo también, con un número");
        }

        return resultado;
    }

    //Agregar aspecto incluido, falta agregar a la publicacion
    @FXML
    private void aspectosIncluidos(ActionEvent actionEvent){
        Text incluido = new Text();
        IncludedInPublication newItem = new IncludedInPublication();
        newItem.setIncluido(itemInc.getText());
        incluido.setText(itemInc.getText());
        listasIncluidos.add(newItem);
        aspIncluidos.getChildren().add(incluido);
    }
    //Agregar aspecto incluido, falta agregar a la publicacion
    @FXML
    public void medidasIncluidas(ActionEvent actionEvent) {
        Text medida = new Text();
        Hygiene newItem = new Hygiene();
        newItem.setMedidas(itemMed.getText());
        medida.setText(itemMed.getText());
        listasHigiene.add(newItem);
        higieneInc.getChildren().add(medida);
    }

    //Agregar horarios incluido, falta agregar a la publicacion

    private Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    private Time convertTime(String hours,String min) throws ParseException {
        String hourComeplete = hours+":"+min+":00";
        SimpleDateFormat sdf = new SimpleDateFormat(hourComeplete);
        long ms = sdf.parse(hourComeplete).getTime();
        Time t = new Time(ms);
        return t;
    }



    @FXML
    private void cargarImagenes(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(null);
        listaFotos = new ArrayList<>();
        Photo newPhoto = new Photo();
        newPhoto.getByteArrayImg(imgFile.getPath());
        listaFotos.add(newPhoto);


        /*
        // Mostar la imagen
        if (imgFile != null) {
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            ivImagen.setImage(image);
        }*/
    }


}



