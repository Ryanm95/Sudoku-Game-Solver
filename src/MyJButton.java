/**
 * @authors Edgar Martinez-Ayala and Ryan Moran
 * MyJButton class - Class that contains the games Button
 * 					 object that holds all button values along
 * 					 with the position that the button currently
 * 					 holds on the game board.
 */
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MyJButton extends JButton{

    private int col;        // col coordinate
    private int row;        // row coordinate
    private int value;      // int value
    private boolean originalPiece; // a button is default for the puzzle if true
    private boolean choiceButtons;
    private ArrayList candidateList = new ArrayList(Arrays.asList(1,2,3,4,5,6,7,8,9));

    public MyJButton(String text){
        //
        super(text);
    }

    public MyJButton(String text, int col, int row, int value, boolean originalValue, boolean choiceButtons){
        super(text);
        this.col = col;
        this.row = row;
        this.value = value;
        this.originalPiece = originalValue;
        this.choiceButtons = choiceButtons;
    }

    public int getRow(){        // getters
        return row;
    }

    public int getCol(){
        return col;
    }

    public int getValue(){
        return value;
    }

    public boolean getOriginalPiece(){
        return originalPiece;
    }

    public boolean getChoiceButtons(){
        return choiceButtons;
    }

    public ArrayList getCandidateList() {
        return candidateList;
    }

    public void reinitalizeCandidateList(){
        candidateList.clear();
        for(int i = 1; i < 10; i++){
            candidateList.add(i);
        }
    }

    public void setValue(int value){
        this.value = value;
    }

    public void setOriginalPiece(boolean value) {
        this.originalPiece = value;
    }

    public void setChoiceButtons(boolean choiceButtons){
        this.choiceButtons = choiceButtons;
    }

    public void deleteCandidate(int number){        // delete number
        int index = candidateList.indexOf(number);  // gets index of number being searched for

        if(index != -1) {
            candidateList.remove(index);                // removes that index from array
        }
    }
}
