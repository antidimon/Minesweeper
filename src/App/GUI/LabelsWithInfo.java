package App.GUI;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class LabelsWithInfo extends JLabel {
    LabelsWithInfo(LabelNames name, int startX, int startY, int width, int height){
        this.setBounds(startX, startY, width, height);
        this.setBackground(Color.LIGHT_GRAY);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setOpaque(true);
        this.setFont(new Font("Arial", Font.BOLD, 12));
        switch (name){
            case STRING_BOMBS -> {
                this.setBorder(new MatteBorder(1, 1, 1 ,1, Color.BLACK));
                this.setText("Set amount of bombs");
            }
            case STRING_UNOPENED_FIELDS -> {
                this.setBorder(new MatteBorder(0, 1, 1 ,1, Color.BLACK));
                this.setText("Summ of unopened fields");
            }
            case NUMBER_UNOPENEDFIELDS -> {
                this.setBorder(new MatteBorder(0, 0, 1 ,1, Color.BLACK));
                this.setText("400");
            }
            case STRING_TIMER -> {
                this.setBorder(new MatteBorder(1, 1, 1 ,1, Color.BLACK));
                this.setText("Timer");
            }
            case NUMBER_TIMER ->{
                this.setBorder(new MatteBorder(1, 0, 1 ,1, Color.BLACK));
                this.setText("00:00");
            }
        }
    }
}
