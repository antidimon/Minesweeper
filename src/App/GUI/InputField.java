package App.GUI;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class InputField extends JTextField {
    InputField(int startX, int startY, int width, int height){
        this.setBounds(startX, startY, width, height);
        this.setBackground(Color.LIGHT_GRAY);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setBorder(new MatteBorder(1, 0, 1 ,1, Color.BLACK));
        this.setText("0");
        this.setFont(new Font("Arial", Font.BOLD, 12));
        this.setOpaque(true);
    }
}
