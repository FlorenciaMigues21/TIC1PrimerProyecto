package proyecto.business.entities;

import com.sun.istack.NotNull;

import javax.imageio.ImageIO;
import javax.persistence.*;

import javafx.scene.image.Image;
import org.hibernate.engine.jdbc.LobCreator;
import org.hibernate.type.descriptor.sql.LobTypeMappings;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import javax.sql.rowset.serial.SerialBlob;

@Entity
public class Photo {

    @Lob
    @NotNull
    private Blob photo;

    @Id
    @GeneratedValue
    private int idPhoto;

    public Photo() {
    }

    public Photo(Blob photo) {
        this.photo = photo;
    }


    public byte[] getPhoto() {
        try {
            return photo.getBytes(1l, (int)photo.length());
        } catch (SQLException e) {
            e.printStackTrace();
            return new byte[]{};
        }
    }

    public void setPhoto(byte[] photo){
        try {
            this.photo = new SerialBlob(photo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getByteArrayImg(String imgPath) throws IOException {
        BufferedImage bufferimage = ImageIO.read(new File(imgPath));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(bufferimage, "jpg", output );
        this.setPhoto(output.toByteArray());
    }

    public Image getImageFromByteArray(byte[] byteArray) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
        BufferedImage image = ImageIO.read(bais);
        return new Image(bais);
    }
}
