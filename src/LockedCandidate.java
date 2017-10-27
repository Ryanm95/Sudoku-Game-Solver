public class LockedCandidate extends Hint {

    public void lockedCandidate(MyJButton[][] sudokuGrid){
        if(lockedCandidate1(sudokuGrid)){
            System.out.println("found");
        }
    }

    private boolean lockedCandidate1(MyJButton[][] sudokuGrid){
        int[] counter = new int[9];

        for (int i = 0; i < 9; ++i)
            counter[i] = 0; // initialize to 0

        for(int i = 0; i < 3; i++){   //rows
            for(int j = 0; j < 3; j++){   //cols
                if(sudokuGrid[i][j].getValue() == 0){
                    for(int a = 0; a < sudokuGrid[i][j].getCandidateList().size(); a++){
                        counter[(int) sudokuGrid[i][j].getCandidateList().get(a)-1] += 1;
                    }
                }
            }
        }
        //TODO delete for later
        for(int i = 0; i < 9; i++){
            System.out.print(counter[i] + " | ");
        }

        int valueFound = -1;
        for(int i = 0; i < 9; i++){
            if(counter[i] == 2){
                valueFound = i + 1;
            }
        }

        int col = -1, row = -1, col1 = -1, row1 = -1;

        for (int i = 0; i < 3; ++i){
            for (int j = 0; j < 3; ++j){
                if (sudokuGrid[i][j].getCandidateList().contains(valueFound) && sudokuGrid[i][j].getValue() == 0){
                    if(col == -1 && row == -1){
                        col = i;
                        row = j;
                    }
                    else{
                        col1 = i;
                        row1 = j;
                        break;
                    }
                }
            }
        }

        if(col == col1){
            System.out.println("\n1: " + valueFound);
            System.out.println("2: " + col + ", " + row);
            System.out.println("3: " + col1 + ", " + row1);

            checkCol(col + 1, valueFound, sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row1].getCandidateList().add(valueFound);
            return true;
        }
        else if(row == row1){
            System.out.println("\n4: " + valueFound);
            System.out.println("5: " + col + ", " + row);
            System.out.println("6: " + col1 + ", " + row1);

            checkRow(row + 1, valueFound, sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row1].getCandidateList().add(valueFound);
            return true;
        }
        else{
            return false;
        }

        //TODO get two positions of values  <----DONE
        //TODO check if they are in the same col or row
        //TODO if they are delete from col and row
        //TODO then add back the number at the two positions and return true
        //TODO if they aren't then return false and go to next grid

    }
}
