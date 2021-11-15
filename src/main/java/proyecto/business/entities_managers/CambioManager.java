package proyecto.business.entities_managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.business.entities.Cambio;
import proyecto.business.entities.Divisa;
import proyecto.business.exceptions.DataBaseError;
import proyecto.business.exceptions.IncompleteObjectException;
import proyecto.business.persistence.CambioDAO;

import java.util.Collection;

@Service
public class CambioManager {

    @Autowired
    CambioDAO controller;

    public Cambio getCambioDeDivisas(Divisa divisaInicial, Divisa divisaFinal) throws IncompleteObjectException, DataBaseError {
        if (divisaInicial == null || divisaFinal == null)
            throw new IncompleteObjectException("Error divisas incompletas");
        try {
            return controller.findByDivisaOrigenAndDivisaCambio(divisaInicial, divisaFinal);
        }catch(Exception e){
            e.printStackTrace();
            throw new DataBaseError(e.getMessage(),"Error al obtener el cambio de las divisas " + e.getMessage());
        }
    }

}
