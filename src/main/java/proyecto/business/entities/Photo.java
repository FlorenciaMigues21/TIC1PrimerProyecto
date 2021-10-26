package proyecto.business.entities;

import com.sun.istack.NotNull;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Entity
public class Photo {

    private byte[] photo;
    /*private int height;
    private int width;
    private int hints;*/


    @Id
    @GeneratedValue
    private int idPhoto;

    public Photo() {
    }

    public Photo(byte[] photo) {
        this.photo = photo;
    }

    @Lob
    @NotNull
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    /*@NotNull
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @NotNull
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @NotNull
    public int getHints() {
        return hints;
    }

    public void setHints(int hints) {
        this.hints = hints;
    }*/

    public void getByteArrayImg(String imgPath) throws IOException {
        BufferedImage bufferimage = ImageIO.read(new File(imgPath));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(bufferimage, "jpg", output );
        this.photo= output.toByteArray();
        /*this.height = bufferimage.getHeight();
        this.width = bufferimage.getWidth();*/

    }


    public Image getImageFromByteArray(byte[] byteArray) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
        BufferedImage image = ImageIO.read(bais);
        return (Image) image;
    }
}
