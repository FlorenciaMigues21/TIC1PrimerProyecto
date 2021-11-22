package proyecto.business.entities;

import com.sun.istack.NotNull;

import javax.imageio.ImageIO;
import javax.persistence.*;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

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
    @Column(name = "id_photo")
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

    public Image getImageFromByteArray(int width, int height) throws IOException {
        WritableImage img =  new WritableImage(width, height);
        ByteArrayInputStream bais = new ByteArrayInputStream(this.getPhoto());
        BufferedImage image = ImageIO.read(bais);
        img = SwingFXUtils.toFXImage(image,null);
        return (Image) img;
    }
}
