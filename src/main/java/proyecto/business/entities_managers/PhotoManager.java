package proyecto.business.entities_managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.business.entities.Photo;
import proyecto.business.exceptions.DataBaseError;
import proyecto.business.persistence.PhotoDAO;

import java.io.IOException;
import java.util.Collection;

@Service
public class PhotoManager {

    @Autowired
    private PhotoDAO controller;

    public Photo getAllByIdPhoto(int photoId){
        return controller.getByIdPhoto(photoId);
    }

    public void savePhoto(Photo photo) throws IOException, DataBaseError {
        if (photo == null)
            throw new IOException();
        try {
            controller.save(photo);
        }catch(Exception e){
            throw new DataBaseError("Errro al guardar imagen",e.getMessage());
        }
    }

}
