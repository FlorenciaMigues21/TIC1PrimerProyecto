package proyecto.business.entities_managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.business.entities.Country;
import proyecto.business.persistence.CountryDAO;

import java.util.Collection;

@Service
public class CountryManager {

    @Autowired
    CountryDAO controller;

    public Collection<Country> getAllCountries(){
        return controller.findAll();
    }

}
