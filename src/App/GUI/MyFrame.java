package App.GUI;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private static final int widthOfWindow = 500, heightOfWindow = 700;
    private static JFrame mainFrame;
    public static void createFrame(String s){
        mainFrame = new JFrame(s);
        mainFrame.setSize(new Dimension(widthOfWindow+25, heightOfWindow+50));
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(null);
        mainFrame.add(new Panels(5, 5, widthOfWindow, heightOfWindow*2/7, 1));
        mainFrame.add(new Panels(5, heightOfWindow*2/7+7, widthOfWindow, heightOfWindow*5/7, 2));
        mainFrame.setVisible(true);
    }

    public static JFrame getMainFrame() {return mainFrame;}
}
