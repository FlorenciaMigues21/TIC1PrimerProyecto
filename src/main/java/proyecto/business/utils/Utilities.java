package proyecto.business.utils;

import java.util.Date;

public class Utilities {

    /*
    *   PARA USAR ESTE METODO HAY QUE MANDAR EL AÑO QUE SE QUIERA ALMACENAR DE MANERA LITERAL,
    *   EJEMPLO
    *   SI QUEREMOS MANDAR LA FECHA "2000-03-01"
    *   LAS VARIABLES DEBEN SER LAS SIGUIENTES
    *   AÑO = 2000, MES = 3, DIA = 1
    */
    public static Date createDate(int anio, int mes, int dia){
        return new Date(anio-1900,mes,dia);
    }

    public static boolean TimeVerification(int hora){
        return hora > -1 && hora < 24 ? true : false;
    }

}
