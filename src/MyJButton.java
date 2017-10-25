import javax.swing.*;
import java.util.ArrayList;

public class MyJButton extends JButton{

    private int col;        // col coordinate
    private int row;        // row coordinate
    private int value;      // int value
    private boolean originalPiece; // a button is default for the puzzle if true
    private boolean choiceButtons;
    private ArrayList<Integer> candidateList = new ArrayList(9);

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
}
