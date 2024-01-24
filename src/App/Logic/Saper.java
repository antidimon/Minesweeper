package App.Logic;

import App.GUI.MyFrame;

import javax.swing.*;

public class Saper {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> MyFrame.createFrame("Saper"));
    }
}