package App.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLabel extends JLabel {
    private final String pathOfImage = System.getProperty("user.dir")+"/src/Resources/ImageSaper.jpg";
    ImageLabel(int width, int height){
        this.setBounds(0, 0, width, height);
        try {
            BufferedImage mainImage = ImageIO.read(new File(pathOfImage));
            ImageIcon icon = new ImageIcon(mainImage);
            this.setIcon(icon);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Cannot find file with image", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}
