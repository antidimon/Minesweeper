package App.Logic;

import App.GUI.FieldButton;
import App.GUI.Panels;
import java.util.Timer;
import javax.swing.*;
import java.awt.*;

public class GameControl {
    private static GameState gameState = GameState.FIRSTSTART;
    private static TimerOfGame timerTask;
    private static Timer timer;
    public static void win(){
        makeDialog("Win");
        gameState = GameState.WINNED;
        stopTimerAndOpenFields();
    }
    public static void defeat(){
        makeDialog("Blow");
        gameState = GameState.DEFEATED;
        stopTimerAndOpenFields();
    }
    public static void restartGame(){
        gameState = GameState.READY;
        Panels.makeStartPosition();
        timerTask.stop();
        Panels.timerLabel.setText("0");
    }
    public static void startGame(){
        gameState = GameState.STARTED;
        timer = new Timer();
        timerTask = new TimerOfGame();
        timerTask.setStartTime(System.currentTimeMillis());
        timer.schedule(timerTask, 100, 10);
    }
    private static void makeDialog(String name){
        JDialog d = new JDialog();
        d.setSize(new Dimension(320, 320));
        d.setLocationRelativeTo(null);
        d.add(new JLabel(new ImageIcon(System.getProperty("user.dir")+"/src/Resources/" + name + ".png")));
        d.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        d.setVisible(true);
    }
    private static void stopTimerAndOpenFields(){
        timerTask.stop();
        FieldButtonController.openAllFields();
    }
    public static GameState getGameState() {return gameState;}

}
