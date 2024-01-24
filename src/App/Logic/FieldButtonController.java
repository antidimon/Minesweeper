package App.Logic;

import App.GUI.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FieldButtonController {
    private static final FieldButton[][] arrayOfButtons = App.GUI.Panels.getArrayOfButtons();
    private static FieldButton callButton;
    private static int countOfMines;
    public static void leftMouseButtonPressed(int row, int column){
        callButton = arrayOfButtons[row][column];
        switch (GameControl.getGameState()){
            case READY, FIRSTSTART -> makeActions();
            case STARTED -> openField(callButton);
        }
    }
    public static void rightMouseButtonPressed(int row, int column){
        if (GameControl.getGameState().equals(GameState.STARTED)) {
            callButton = arrayOfButtons[row][column];
            switch (callButton.getState()) {
                case CLOSED -> callButton.setFlag();
                case FLAGED -> callButton.unSetFlag();
            }
        }
    }
    private static void makeActions(){
        if (!tryCastInput()) return;
        GameControl.startGame();
        makeMinedFields();
        loopMinesToMakeNumbers();
        openField(callButton);
    }
    private static boolean tryCastInput(){
        try {
            countOfMines = Integer.parseInt(Panels.inputField.getText());
            if (countOfMines > 301) throw new IllegalArgumentException();
            return true;
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(MyFrame.getMainFrame(), "Not a digit", "WARNING", JOptionPane.WARNING_MESSAGE);
            return false;
        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(MyFrame.getMainFrame(), "Wrong amount", "WARNING", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    private static void makeMinedFields(){
        HashMap<Integer, Integer> mapOfMines = new HashMap<>();
        for (int i = 0; i < countOfMines; i++) {
            int randomRow, randomColumn;
            do {
                randomRow = (int) (Math.random() * 20);
                randomColumn = (int) (Math.random() * 20);
            } while (checks(randomRow, randomColumn, mapOfMines) || (mapOfMines.containsKey(randomRow) && mapOfMines.get(randomRow) == randomColumn));
            mapOfMines.put(randomRow, randomColumn);
            arrayOfButtons[randomRow][randomColumn].setTextOfField(FieldText.MINED);
        }
    }
    private static boolean checks(int row, int column, HashMap<Integer, Integer> map){
        if (row == callButton.getRow() && column == callButton.getColumn()) return true;
        if (checkIfMainButtonIsNear(row, column)) return true;
        return checkPlacementOfMines(row, column, true);
    }
    private static boolean checkIfMainButtonIsNear(int row, int column){
        for (FieldButton fb: getNeighbours(row, column)){
            if (fb.equals(callButton)) return true;
        }
        return false;
    }
    private static boolean checkPlacementOfMines(int row, int column, boolean checker){
        if ((row == 0 || row == 19) && (column == 0 || column == 19) && checkNeighboursForMines(row, column) == 3) return true;
        else if ((row == 0 || row == 19 || column == 0 || column == 19) && checkNeighboursForMines(row, column) == 5) return true;
        else if (checkNeighboursForMines(row, column) == 8) return true;
        else if (checker){
            for (FieldButton fb: getNeighbours(row, column)){
                if (checkPlacementOfMines(fb.getRow(), fb.getColumn(), false)) return true;
            }
            return false;
        }
        return false;
    }
    private static ArrayList<FieldButton> getNeighbours(int row, int column){
        ArrayList<FieldButton> neighbours = new ArrayList<>();
        for (int i = -1; i < 2; i++){
            if (row + i < 0 || row + i > 19) continue;
            for (int j = -1; j < 2; j++){
                if (column + j < 0 || column + j > 19 || (i==0 && j==0)) continue;
                neighbours.add(arrayOfButtons[row+i][column+j]);
            }
        }
        return neighbours;
    }
    private static int checkNeighboursForMines(int row, int column){
        int amountOfMinesNear = 0;
        for (FieldButton fb: getNeighbours(row, column)){
            if (fb.getTextOfField().equals(FieldText.MINED)) amountOfMinesNear++;
        }
        return amountOfMinesNear;
    }
    private static void loopMinesToMakeNumbers(){
        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++){
                if (i == callButton.getRow() && j == callButton.getColumn()) continue;
                if (!arrayOfButtons[i][j].getTextOfField().equals(FieldText.MINED)){
                    arrayOfButtons[i][j].setMinesNextToField(checkNeighboursForMines(i, j));
                }
            }
        }
    }
    private static void openField(FieldButton fb){
        if (fb.getState().equals(FieldState.CLOSED)) {
            switch (fb.getTextOfField()) {
                case MINED -> {
                    fb.makeOpenedField();
                    GameControl.defeat();
                }
                case NUMBERED -> {
                    fb.makeOpenedField();
                }
                case EMPTY -> {
                    openNeighboursRecursive(fb);
                }
            }
        }
    }
    private static void openNeighboursRecursive(FieldButton fb) {
        fb.makeOpenedField();
        for (FieldButton neighbour: getNeighbours(fb.getRow(), fb.getColumn())){
            openField(neighbour);
        }
    }
    protected static void openAllFields(){
        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++){
                if (arrayOfButtons[i][j].getState() != FieldState.OPENED){
                    arrayOfButtons[i][j].makeOpenedField();
                }
            }
        }
    }

    public static int getCountOfMines() {return countOfMines;}
}
