package proyecto.business.entities_managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.business.entities.Cambio;
import proyecto.business.entities.Divisa;
import proyecto.business.persistence.CambioDAO;

import java.util.Collection;

@Service
public class CambioManager {

    @Autowired
    CambioDAO controller;

    public Cambio getCambioDeDivisas(Divisa divisaInicial, Divisa divisaFinal){
        return controller.findByDivisaOrigenAndDivisaCambio(divisaInicial,divisaFinal);
    }

}
