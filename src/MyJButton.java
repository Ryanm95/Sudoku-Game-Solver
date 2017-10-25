import javax.swing.*;
import java.lang.reflect.Array;
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

    public void setCol(int col){        // setters
        this.col = col;
    }

    public void setRow(int row){
        this.row = row;
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

    public void addCandidate(int number){       // adds number to array
        candidateList.add(number);
    }

    public boolean deleteCandidate(int number){        // delete number
        int index = candidateList.indexOf(number);  // gets index of number being searched for

        if(index != -1) {
            candidateList.remove(index);                // removes that index from array
            return true;                                // if found and deleted... return true
        }
        // not found so not deleted... return false
        return false;
    }
}
