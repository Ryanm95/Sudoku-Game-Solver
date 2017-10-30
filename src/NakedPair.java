public class NakedPair extends Hint{
    private int rowCount = 0;
    private int colCount = 0;

    public void processNakedPair(MyJButton[][] sudokuGrid) {
        int tmpCount = rowCount;
        while(tmpCount < 9){
            if(processRow(tmpCount, sudokuGrid)){
                tmpCount++;
                rowCount = tmpCount;
                return;
            }
            tmpCount++;
            rowCount = tmpCount;
        }

        if(tmpCount == 9){
            tmpCount = colCount;
            while(tmpCount < 9){
                if(processCol(tmpCount, sudokuGrid)){
                    tmpCount++;
                    colCount = tmpCount;
                    return;
                }
                tmpCount++;
                colCount = tmpCount;
            }
        }

        if(rowCount == 9 && colCount == 9){
            rowCount = 0;
            colCount = 0;
            return;
        }

        return;
    }

    private boolean processRow(int row, MyJButton[][] sudokuGrid){
        int[] counter = new int[9];

        for (int i = 0; i < 9; ++i)
            counter[i] = 0; // initialize to 0

        for(int i = 0; i < 9; i++){ //adds pairs to list
            if(sudokuGrid[i][row].getCandidateList().size() == 2 && sudokuGrid[i][row].getValue() == 0){
                for(int a = 0; a < sudokuGrid[i][row].getCandidateList().size(); a++) {
                    counter[(int) sudokuGrid[i][row].getCandidateList().get(a)-1] += 1;
                }
            }
        }

        int valueFound = -1;
        int i;
        for(i = 0; i < 9; i++){
            if(counter[i] == 2){
                valueFound = i + 1;
                break;
            }
        }

        int valueFound2 = -1;
        for(int j = i + 1; j < 9; j++){
            if(counter[j] == 2){
                valueFound2 = j + 1;
            }
        }

        int col = -1, col1 = -1;

        for (int j = 0; j < 9; ++j){
            if (sudokuGrid[j][row].getCandidateList().contains(valueFound) &&  sudokuGrid[j][row].getValue() == 0
                    && sudokuGrid[j][row].getCandidateList().contains(valueFound2)
                    && sudokuGrid[j][row].getCandidateList().size() == 2){
                if(col == -1){
                    col = j;
                }
                else{
                    col1 = j;
                    break;
                }
            }
        }

        if(col != -1 && col1 != -1){
            checkRow(row + 1, valueFound,sudokuGrid);
            checkRow(row + 1, valueFound2,sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col][row].getCandidateList().add(valueFound2);
            sudokuGrid[col1][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row].getCandidateList().add(valueFound2);
            return true;
        }

        return false;
    }

    private boolean processCol(int col, MyJButton[][] sudokuGrid){
        int[] counter = new int[9];

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
        for(i = 0; i < 9; i++){
            if(counter[i] == 2){
                valueFound = i + 1;
                break;
            }
        }

        int valueFound2 = -1;
        for(int j = i + 1; j < 9; j++){
            if(counter[j] == 2){
                valueFound2 = j + 1;
            }
        }

        int row = -1, row1 = -1;

        for (int j = 0; j < 9; ++j){
            if (sudokuGrid[col][j].getCandidateList().contains(valueFound) &&  sudokuGrid[col][j].getValue() == 0
                    && sudokuGrid[col][j].getCandidateList().contains(valueFound2)
                    && sudokuGrid[col][j].getCandidateList().size() == 2){
                if(row == -1){
                    row = j;
                }
                else{
                    row1 = j;
                    break;
                }
            }
        }

        if(row != -1 && row1 != -1){
            checkCol(col + 1, valueFound,sudokuGrid);
            checkCol(col + 1, valueFound2,sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col][row].getCandidateList().add(valueFound2);
            sudokuGrid[col][row1].getCandidateList().add(valueFound);
            sudokuGrid[col][row1].getCandidateList().add(valueFound2);
            return true;
        }

        return false;
    }

}