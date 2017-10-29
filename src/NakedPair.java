import java.util.ArrayList;
import java.util.List;


public class NakedPair extends Hint{
    private  ArrayList<ArrayList<Integer>> possiblePair;

    private void setUparray(){
        possiblePair = new ArrayList<ArrayList<Integer>>(9);
        for(int i = 0; i < 9; i++){
            possiblePair.add(new ArrayList<Integer>(2));
        }
    }

    public void processNakedPair(MyJButton[][] sudokuGrid){
        for(int i = 0; i < 9; i++){
            setUparray();
            //possiblePair.add(new ArrayList<Integer>();
            if(processRow(i, sudokuGrid) == 1){
                return;
            }
            //System.out.println();
            //proccessCol(i, sudokuGrid);
        }
    }

    public int processRow(int row, MyJButton[][] sudokuGrid){
        int index = 0;
        for(int i = 0; i < 9; i++){
            if(sudokuGrid[i][row].getValue() == 0 && sudokuGrid[i][row].getCandidateList().size() == 2) {   // if not original button and CL is 2
                //ArrayList temp = sudokuGrid[i][row].getCandidateList();
                ArrayList temp = new ArrayList(sudokuGrid[i][row].getCandidateList());      // copy CL
                possiblePair.get(i).addAll(temp);           // add CL to array
                //System.out.println(possiblePair.get(i));
            }

        }
        index = comparePairs(possiblePair);     // get index of one of the matching candidate lists
        if(index != -1) {                        // if there is one matching
            for (int i = 0; i < 9; i++) {          // loop though sudoku grid on that row and delete all occurances of those numbers
                if (!sudokuGrid[i][row].getCandidateList().equals(possiblePair.get(index))) {
                    int num1 = 6;       // test number
                    //num1 = possiblePair.get(index - 1).get(0);    TODO: get the first and second number from the matching arraylist
                    //int num2 = possiblePair.get(index).get(1);
                    for (int j = 0; j < 9; j++) {
                        sudokuGrid[i][row].deleteCandidate(num1);       // TODO: delete both numbers from row except the two matching
                        // sudokuGrid[i][row].deleteCandidate(num2);
                    }
                    //checkRow(i + 1, num1, sudokuGrid);
                    //checkRow(i + 1, num2, sudokuGrid);
                }
            }
            return 1;
        }
        return  -1;
    }

    public void proccessCol(int col){

    }

    private int comparePairs(ArrayList<ArrayList<Integer>> possiblePair){
        for(int i = 0; i < 8; i++){                 // start at the front of the list
            for(int j = i + 1; j < 9; j++){         // compare to the next element in the list
                if(possiblePair.get(i).equals(possiblePair.get(j))) {        // if they are the same
                    return j;
                }
            }
        }
        return -1;
    }
}