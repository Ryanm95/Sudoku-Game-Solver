/**
 * @author Edgar Martinez-Ayala and Ryan Moran
 * NakedPair class - Class that handles the naked pair algorithm.
 *                   It first checks rows for a naked pair and stops once
 *                   it finds it. Then once no more naked pairs are found in rows
 *                   it starts to check columns. After columns then it starts over
 *                   checking rows and then columns till puzzle is solved.
 */

public class NakedPair extends Hint{
    private int rowCount = 0;  //row last checked
    private int colCount = 0;  //col last checked

    public void processNakedPair(MyJButton[][] sudokuGrid) {
        int tmpCount = rowCount;
        while(tmpCount < 9){
            if(processRow(tmpCount, sudokuGrid)){   //checks to see if naked pair is found for rows
                tmpCount++;
                rowCount = tmpCount;  //naked pair found ro row count is updated for when used again
                return;
            }
            tmpCount++;
            rowCount = tmpCount;
        }

        if(tmpCount == 9){
            tmpCount = colCount;
            while(tmpCount < 9){
                if(processCol(tmpCount, sudokuGrid)){   //checks to see if naked pair is found for col
                    tmpCount++;
                    colCount = tmpCount;
                    return;
                }
                tmpCount++;
                colCount = tmpCount;
            }
        }

        if(rowCount == 9 && colCount == 9){   //once both have been checked it resets the rows and col
            rowCount = 0;
            colCount = 0;
            return;
        }

        return;
    }

    //Checks to see if row contains a naked pair and deletes
    //the values from everywhere except from the pair if a naked
    //pair was found.
    private boolean processRow(int row, MyJButton[][] sudokuGrid){
        int[] counter = new int[9];  //array the holds the num of times a num appears

        for (int i = 0; i < 9; ++i)
            counter[i] = 0; // initialize to 0

        for(int i = 0; i < 9; i++){ //adds pairs to list
            if(sudokuGrid[i][row].getCandidateList().size() == 2 && sudokuGrid[i][row].getValue() == 0){
                for(int a = 0; a < sudokuGrid[i][row].getCandidateList().size(); a++) {
                    counter[(int) sudokuGrid[i][row].getCandidateList().get(a)-1] += 1;
                }
            }
        }

        int valueFound = -1;   //looks for first value that appears only twice
        int i;
        for(i = 0; i < 9; i++){
            if(counter[i] == 2){
                valueFound = i + 1;
                break;
            }
        }

        int valueFound2 = -1;  //looks for second value that appears only twice
        for(int j = i + 1; j < 9; j++){
            if(counter[j] == 2){
                valueFound2 = j + 1;
            }
        }

        int col = -1, col1 = -1;  //col holders that get col position of where num is located in sudoku grid

        for (int j = 0; j < 9; ++j){
            if (sudokuGrid[j][row].getCandidateList().contains(valueFound) &&  sudokuGrid[j][row].getValue() == 0
                    && sudokuGrid[j][row].getCandidateList().contains(valueFound2)
                    && sudokuGrid[j][row].getCandidateList().size() == 2){
                if(col == -1){
                    col = j;  //updates col
                }
                else{
                    col1 = j; //updates col1
                    break;
                }
            }
        }

        if(col != -1 && col1 != -1){  //if naked pair is found
            checkRow(row + 1, valueFound,sudokuGrid);   //deletes value from row
            checkRow(row + 1, valueFound2,sudokuGrid);  ////deletes value2 from row

            //adds numbers back into candidate list from naked pair
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col][row].getCandidateList().add(valueFound2);
            sudokuGrid[col1][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row].getCandidateList().add(valueFound2);
            return true;
        }

        return false;
    }

    //Checks to see if col contains a naked pair and deletes
    //the values from everywhere except from the pair if a naked
    //pair was found.
    private boolean processCol(int col, MyJButton[][] sudokuGrid){
        int[] counter = new int[9];  //array the holds the num of times a num appears

        for (int i = 0; i < 9; ++i)
            counter[i] = 0; // initialize to 0

        for(int i = 0; i < 9; i++){ //adds pairs to list
            if(sudokuGrid[col][i].getCandidateList().size() == 2 && sudokuGrid[col][i].getValue() == 0){
                for(int a = 0; a < sudokuGrid[col][i].getCandidateList().size(); a++) {
                    counter[(int) sudokuGrid[col][i].getCandidateList().get(a)-1] += 1;
                }
            }
        }

        int valueFound = -1;
        int i;
        for(i = 0; i < 9; i++){   //looks for first value that appears only twice
            if(counter[i] == 2){
                valueFound = i + 1;
                break;
            }
        }

        int valueFound2 = -1;
        for(int j = i + 1; j < 9; j++){  //looks for second value that appears only twice
            if(counter[j] == 2){
                valueFound2 = j + 1;
            }
        }

        int row = -1, row1 = -1; //row holders that get col position of where num is located in sudoku grid

        for (int j = 0; j < 9; ++j){
            if (sudokuGrid[col][j].getCandidateList().contains(valueFound) &&  sudokuGrid[col][j].getValue() == 0
                    && sudokuGrid[col][j].getCandidateList().contains(valueFound2)
                    && sudokuGrid[col][j].getCandidateList().size() == 2){
                if(row == -1){
                    row = j;  //updates row
                }
                else{
                    row1 = j;  //updates row1
                    break;
                }
            }
        }

        if(row != -1 && row1 != -1){
            checkCol(col + 1, valueFound,sudokuGrid);  //deletes value from row
            checkCol(col + 1, valueFound2,sudokuGrid);  //deletes value2 from row

            //adds numbers back into candidate list from naked pair
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col][row].getCandidateList().add(valueFound2);
            sudokuGrid[col][row1].getCandidateList().add(valueFound);
            sudokuGrid[col][row1].getCandidateList().add(valueFound2);
            return true;
        }

        return false;
    }

}