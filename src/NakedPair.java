import java.util.ArrayList;

public class NakedPair extends Hint{
    private int[][] possiblePairs = new int[9][9];

    public void processNakedPair(MyJButton[][] sudokuGrid){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                    processRow(i, sudokuGrid);
                    //proccessCol(sudokuGrid);
            }
        }
    }

    public void processRow(int row, MyJButton[][] sudokuGrid){
        for(int i = 0; i < 9; i++){
            if(sudokuGrid[i][row].getValue() == 0 && sudokuGrid[i][row].getCandidateList().size() == 2) {
                ArrayList temp = sudokuGrid[i][row].getCandidateList();
                System.out.println(temp);
            }
        }

    }

    public void proccessCol(MyJButton[][] sudokuGrid){

    }
}
