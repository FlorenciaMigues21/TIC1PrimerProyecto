package proyecto.ui.operator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.business.entities.*;
import proyecto.business.entities_managers.PhotoManager;
import proyecto.business.entities_managers.PublicationManager;
import proyecto.business.entities_managers.TypeofactivitiesManager;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Component
public class ExperienceDisplayOperator {

    @Autowired
    PublicationManager publicMan;

    @Autowired
    TypeofactivitiesManager typeActMan;

    @Autowired
    PhotoManager photoMan;

    @Autowired
    TypeofactivitiesManager typeManager;

    public static TouristOperator operador;

    @FXML
    private TextField telefono;

    @FXML
    private TextField direccion;

    @FXML
    private TextField Horario;

    @FXML
    private Button agregarAsp;

    @FXML
    private Button agregarHorario;

    @FXML
    private Button agregarMedida;

    @FXML
    private SplitMenuButton aspHigiene;

    @FXML
    private SplitMenuButton aspIncluidos;

    @FXML
    private Button back;

    @FXML
    private Button cargaImagenes;

    @FXML
    private CheckBox cultural;

    @FXML
    private CheckBox descanso;

    @FXML
    private TextArea descripcion;

    @FXML
    private CheckBox escapadasrurales;

    @FXML
    private CheckBox familiar;

    @FXML
    private DatePicker fechaFin;

    @FXML
    private DatePicker fechaIni;

    @FXML
    private ChoiceBox<?> gustoViaje;

    @FXML
    private TextField horarioMins;

    @FXML
    private SplitMenuButton horariosDisp;

    @FXML
    private CheckBox invierno;

    @FXML
    private TextField itemInc;

    @FXML
    private TextField itemMed;

    @FXML
    private CheckBox lunademiel;

    @FXML
    private CheckBox mochilero;

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
    private TextField titulo;

    @FXML
    private CheckBox turismoaventura;

    @FXML
    private CheckBox verano;

    @FXML
    private TextField aforo;

    @FXML
    void goBack(ActionEvent event) {
        System.out.println("Hi");
    }

    Collection<Typeofactivities> tiposActividad = typeManager.getAllActivityTypes();
    ArrayList<Typeofactivities> tiposActividadList = new ArrayList<>(tiposActividad);
    ArrayList<Hygiene> listasHigiene = new ArrayList<>();
    ArrayList<IncludedInPublication> listasIncluidos = new ArrayList<>();

    public void initialize() {}


    public void saveExperience(ActionEvent actionEvent){
        if (titulo.getText() == null || precio.getText().equals("") ||
                descripcion.getText() == null || aspHigiene.getItems() == null || aspIncluidos.getItems() == null ||
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
            /*boolean esTelefonoInt = integerInvalido(telefono.getText());*/
            if(esPrecioInt){
                newPublication.setCantidad(Float.parseFloat(precio.getText()));
            }
            newPublication.setPhone(telefono.getText());
            newPublication.setUbication(direccion.getText());
            newPublication.setDescription(descripcion.getText());
            newPublication.setDatefrom(convertToDateViaSqlDate(fechaIni.getValue()));
            newPublication.setDateto(convertToDateViaSqlDate(fechaFin.getValue()));
            newPublication.setMedidas_de_higiene(listasHigiene);
            newPublication.setIncluido(listasIncluidos);
            //FALTA TEMA HORARIOS


        }
    }

    private ArrayList<Typeofactivities> handleOptions(CheckBox fam,CheckBox escap,CheckBox luna,CheckBox turism,CheckBox cult,CheckBox desc,CheckBox mochi){
        ArrayList<Typeofactivities> tipoDeActividad = new ArrayList<>();

        if(fam.isSelected()){
            Typeofactivities familiaAct = tipoDeActividad("Familiar");
            tipoDeActividad.add(familiaAct);
        }
        else if(escap.isSelected()){
            Typeofactivities escapadaAct = tipoDeActividad("Escapadas rurales");
            tipoDeActividad.add(escapadaAct);
        }
        else if(luna.isSelected()){
            Typeofactivities LunaAct = tipoDeActividad("Luna de miel");
            tipoDeActividad.add(LunaAct);
        }
        else if(turism.isSelected()){
            Typeofactivities aventAct = tipoDeActividad("Turismo aventura");
            tipoDeActividad.add(aventAct);
        }
        else if(cult.isSelected()){
            Typeofactivities cultAct = tipoDeActividad("Cultural");
            tipoDeActividad.add(cultAct);
        }
        else if(desc.isSelected()){
            Typeofactivities descAct = tipoDeActividad("Descanso");
            tipoDeActividad.add(descAct);
        }
        else if(mochi.isSelected()){
            Typeofactivities mochiAct = tipoDeActividad("De mochilero");
            tipoDeActividad.add(mochiAct);
        }

        return tipoDeActividad;

    }

    //@FIXME
    private void choiceBoxOption(ChoiceBox<String> gustoViaje){
        if(gustoViaje.getValue().toString().equals("Viaje Confort") ){

        }
        else if(gustoViaje.getValue().toString().equals("Viaje alternativo")){

        }
        else if(gustoViaje.getValue().toString().equals("Viaje de Lujo")) {
        }
    }

    //@FIXME
    private void estaciones(CheckBox inv,CheckBox ver,CheckBox ot,CheckBox prim){
        if(inv.isSelected()){
            //ARREGLAR
        }
        else if(ver.isSelected()){
            //ARREGLAR
        }
        else if(ot.isSelected()){

        }
        else if(prim.isSelected()){

        }
    }

    //RETORNA EL TIPO DE ACTIVIDAD
    private Typeofactivities tipoDeActividad(String nombre){
        Typeofactivities tipo = null;
        for(int i=0; i<tiposActividadList.size(); i++){
            if (tiposActividadList.get(i).getName().equals(nombre)){
                tipo = tiposActividadList.get(i);
            }
        }
        return tipo;
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
        MenuItem itemIncAg = new MenuItem();
        itemIncAg.setText(itemInc.getText());
        IncludedInPublication newItem = new IncludedInPublication();
        newItem.setIncluido(itemIncAg.getText());
        listasIncluidos.add(newItem);
        aspIncluidos.getItems().add(itemIncAg);
    }
    //Agregar aspecto incluido, falta agregar a la publicacion
    @FXML
    public void medidasIncluidas(ActionEvent actionEvent) {
        MenuItem itemMedAg = new MenuItem();
        itemMedAg.setText(itemMed.getText());
        Hygiene medida = new Hygiene();
        medida.setMedidas(itemMed.getText());
        listasHigiene.add(medida);
        aspHigiene.getItems().add(itemMedAg);
    }

    //Agregar horarios incluido, falta agregar a la publicacion
    @FXML
    private void horariosIncluidos(ActionEvent actionEvent) {
        MenuItem itemHorAg = new MenuItem();
        boolean horarioCorrecto = integerInvalido(Horario.getText());
        boolean minutosCorrecto = integerInvalido(horarioMins.getText());

        if(!horarioCorrecto || !minutosCorrecto || Integer.parseInt(Horario.getText()) > 24 || Integer.parseInt(Horario.getText()) < 0 || Integer.parseInt(horarioMins.getText()) > 59 || Integer.parseInt(horarioMins.getText()) < 0){
            showAlert("Error al ingresar el horario","Verifique si las horas y los minutos son adecuados.");
        }
        else{
            itemHorAg.setText(Horario.getText() + horarioMins.getText());
            horariosDisp.getItems().add(itemHorAg);
        }
    }
    private Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }


    @FXML
    private void cargarImagenes(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files","*.pdf"));
        List<File> lista = fileChooser.showOpenMultipleDialog(null);
        for (File file : lista){
            System.out.println(file.getAbsolutePath());
        }
    }



}
