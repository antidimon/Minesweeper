package App.GUI;

import App.Logic.FieldButtonController;
import App.Logic.GameControl;
import App.Logic.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FieldButton extends JButton {
    private final int row;
    private final int column;
    private FieldState state;
    private FieldText textOfField;
    private int minesNextToField;
    FieldButton(int row, int column){
        this.state = FieldState.CLOSED;
        this.textOfField = FieldText.EMPTY;
        this.row = row;
        this.column = column;
        this.setBackground(new Color(87, 86, 86));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)FieldButtonController.leftMouseButtonPressed(row, column);
                if (e.getButton() == MouseEvent.BUTTON3)FieldButtonController.rightMouseButtonPressed(row, column);
            }
        });
    }
    public void makeOpenedField(){
        this.setState(FieldState.OPENED);
        if (GameControl.getGameState()== GameState.STARTED) Panels.decreaseOneClosedField();
        switch (this.textOfField) {
            case NUMBERED -> {
                this.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/Resources/" + this.minesNextToField + ".png"));
            }
            case EMPTY -> {
                this.setText("");
                this.setBackground(Color.LIGHT_GRAY);
            }
            case MINED -> {this.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/Resources/Bomb.png"));}
        }
    }

    public int getRow() {return row;}
    public int getColumn() {return column;}
    public void setState(FieldState state){this.state = state;}
    public FieldState getState(){return this.state;}
    public FieldText getTextOfField() {return textOfField;}
    public void setTextOfField(FieldText textOfField) {this.textOfField = textOfField;}
    public void setMinesNextToField(int minesNextToField) {
        if (minesNextToField != 0) this.textOfField = FieldText.NUMBERED;
        this.minesNextToField = minesNextToField;
    }
    public void setFlag(){
        this.state = FieldState.FLAGED;
        this.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/Resources/flag.png"));
    }
    public void unSetFlag(){
        this.state = FieldState.CLOSED;
        this.setIcon(null);
    }
    public void setStartPosition(){
        this.state = FieldState.CLOSED;
        this.textOfField = FieldText.EMPTY;
        this.setIcon(null);
        this.minesNextToField = 0;
        this.setBackground(new Color(87, 86, 86));
    }
}
