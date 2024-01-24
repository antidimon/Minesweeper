package App.GUI;

import App.Logic.FieldButtonController;
import App.Logic.GameControl;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static App.GUI.LabelNames.*;

public class Panels extends JPanel {
    private static FieldButton[][] arrayOfButtons;
    public static JTextField inputField;
    public static JLabel amountClosedFields, timerLabel;
    Panels(int panelStartX, int panelStartY, int panelWidth, int panelHeight, int id) {
        arrayOfButtons = new FieldButton[20][20];
        this.setBounds(panelStartX, panelStartY, panelWidth, panelHeight);
        if (id == 1) {
            this.setLayout(null);
            this.add(new ImageLabel(panelWidth/2, panelHeight));
            this.add(new LabelsWithInfo(STRING_BOMBS, panelStartX+270,panelStartY+20, 150, 30));
            this.add(inputField = new InputField(panelStartX+420, panelStartY+20, 60, 30));
            this.add(new LabelsWithInfo(LabelNames.STRING_UNOPENED_FIELDS, panelStartX+270, panelStartY+50, 150, 30));
            this.add(amountClosedFields = new LabelsWithInfo(NUMBER_UNOPENEDFIELDS, panelStartX+420, panelStartY+50, 60, 30));
            this.add(new LabelsWithInfo(STRING_TIMER,panelStartX+300, panelStartY+90, 75, 40));
            timerLabel = new LabelsWithInfo(NUMBER_TIMER, panelStartX+375, panelStartY+90, 75,40);
            this.add(timerLabel);
            this.add(makeStartButton(panelStartX+300, panelStartY+135, 150, 40));
        } else if (id == 2){
            this.setLayout(new GridLayout(20, 20));
            for (int i = 0; i < 20; i++){
                for (int j = 0; j < 20; j++){
                    FieldButton button = new FieldButton(i, j);
                    arrayOfButtons[i][j] = button;
                    this.add(button);
                }
            }

        }
    }
    private JButton makeStartButton(int startX, int startY, int width, int height){
        JButton button = new JButton();
        button.setBounds(startX, startY, width, height);
        button.setBackground(Color.LIGHT_GRAY);
        button.setText("New game");
        button.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameControl.restartGame();
            }
        });
        return button;
    }
    public static void decreaseOneClosedField(){
        int count = Integer.parseInt(amountClosedFields.getText()) - 1;
        amountClosedFields.setText(String.valueOf(count));
        if (count == FieldButtonController.getCountOfMines()) GameControl.win();
    }
    public static void makeStartPosition(){
        inputField.setText("0");
        amountClosedFields.setText("400");
        timerLabel.setText("00:00");
        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++){
                arrayOfButtons[i][j].setStartPosition();
            }
        }
    }
    public static FieldButton[][] getArrayOfButtons() {return arrayOfButtons;}
}
