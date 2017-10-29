public class LockedCandidate extends Hint {

    public void lockedCandidate(MyJButton[][] sudokuGrid){
        if(lockedCandidate1(sudokuGrid)){
            return;
        }
        else if(lockedCandidate2(sudokuGrid)){
            return;
        }
        else if(lockedCandidate3(sudokuGrid)){
            return;
        }
        else if(lockedCandidate4(sudokuGrid)){
            return;
        }
        else if(lockedCandidate5(sudokuGrid)){
            return;
        }
        else if(lockedCandidate6(sudokuGrid)){
            return;
        }
        else if(lockedCandidate7(sudokuGrid)){
            return;
        }
        else if(lockedCandidate8(sudokuGrid)){
            return;
        }
        else if(lockedCandidate9(sudokuGrid)){
            return;
        }
        else{
            return;
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

        if(col == col1 && col != -1){
            checkCol(col + 1, valueFound, sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row1].getCandidateList().add(valueFound);
            return true;
        }
        else if(row == row1 && row != -1){
            checkRow(row + 1, valueFound, sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row1].getCandidateList().add(valueFound);
            return true;
        }
        else{
            return false;
        }
    }

    private boolean lockedCandidate2(MyJButton[][] sudokuGrid){
        int[] counter = new int[9];

        for (int i = 0; i < 9; ++i)
            counter[i] = 0; // initialize to 0

        for(int i = 3; i < 6; i++){   //rows
            for(int j = 0; j < 3; j++){   //cols
                if(sudokuGrid[i][j].getValue() == 0){
                    for(int a = 0; a < sudokuGrid[i][j].getCandidateList().size(); a++){
                        counter[(int) sudokuGrid[i][j].getCandidateList().get(a)-1] += 1;
                    }
                }
            }
        }

        int valueFound = -1;
        for(int i = 0; i < 9; i++){
            if(counter[i] == 2){
                valueFound = i + 1;
            }
        }

        int col = -1, row = -1, col1 = -1, row1 = -1;

        for (int i = 3; i < 6; ++i){
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

        if(col == col1 && col != -1){
            checkCol(col + 1, valueFound, sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row1].getCandidateList().add(valueFound);
            return true;
        }
        else if(row == row1 && row != -1){
            checkRow(row + 1, valueFound, sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row1].getCandidateList().add(valueFound);
            return true;
        }
        else{
            return false;
        }
    }

    private boolean lockedCandidate3(MyJButton[][] sudokuGrid){
        int[] counter = new int[9];

        for (int i = 0; i < 9; ++i)
            counter[i] = 0; // initialize to 0

        for(int i = 6; i < 9; i++){   //rows
            for(int j = 0; j < 3; j++){   //cols
                if(sudokuGrid[i][j].getValue() == 0){
                    for(int a = 0; a < sudokuGrid[i][j].getCandidateList().size(); a++){
                        counter[(int) sudokuGrid[i][j].getCandidateList().get(a)-1] += 1;
                    }
                }
            }
        }

        int valueFound = -1;
        for(int i = 0; i < 9; i++){
            if(counter[i] == 2){
                valueFound = i + 1;
            }
        }

        int col = -1, row = -1, col1 = -1, row1 = -1;

        for (int i = 6; i < 9; ++i){
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

        if(col == col1 && col != -1){
            checkCol(col + 1, valueFound, sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row1].getCandidateList().add(valueFound);
            return true;
        }
        else if(row == row1 && row != -1){
            checkRow(row + 1, valueFound, sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row1].getCandidateList().add(valueFound);
            return true;
        }
        else{
            return false;
        }
    }

    private boolean lockedCandidate4(MyJButton[][] sudokuGrid){
        int[] counter = new int[9];

        for (int i = 0; i < 9; ++i)
            counter[i] = 0; // initialize to 0

        for(int i = 0; i < 3; i++){   //rows
            for(int j = 3; j < 6; j++){   //cols
                if(sudokuGrid[i][j].getValue() == 0){
                    for(int a = 0; a < sudokuGrid[i][j].getCandidateList().size(); a++){
                        counter[(int) sudokuGrid[i][j].getCandidateList().get(a)-1] += 1;
                    }
                }
            }
        }

        int valueFound = -1;
        for(int i = 0; i < 9; i++){
            if(counter[i] == 2){
                valueFound = i + 1;
            }
        }

        int col = -1, row = -1, col1 = -1, row1 = -1;

        for (int i = 0; i < 3; ++i){
            for (int j = 3; j < 6; ++j){
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

        if(col == col1 && col != -1){
            checkCol(col + 1, valueFound, sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row1].getCandidateList().add(valueFound);
            return true;
        }
        else if(row == row1 && row != -1){
            checkRow(row + 1, valueFound, sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row1].getCandidateList().add(valueFound);
            return true;
        }
        else{
            return false;
        }
    }

    private boolean lockedCandidate5(MyJButton[][] sudokuGrid){
        int[] counter = new int[9];

        for (int i = 0; i < 9; ++i)
            counter[i] = 0; // initialize to 0

        for(int i = 3; i < 6; i++){   //rows
            for(int j = 3; j < 6; j++){   //cols
                if(sudokuGrid[i][j].getValue() == 0){
                    for(int a = 0; a < sudokuGrid[i][j].getCandidateList().size(); a++){
                        counter[(int) sudokuGrid[i][j].getCandidateList().get(a)-1] += 1;
                    }
                }
            }
        }

        int valueFound = -1;
        for(int i = 0; i < 9; i++){
            if(counter[i] == 2){
                valueFound = i + 1;
            }
        }

        int col = -1, row = -1, col1 = -1, row1 = -1;

        for (int i = 3; i < 6; ++i){
            for (int j = 3; j < 6; ++j){
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

        if(col == col1 && col != -1){
            checkCol(col + 1, valueFound, sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row1].getCandidateList().add(valueFound);
            return true;
        }
        else if(row == row1 && row != -1){
            checkRow(row + 1, valueFound, sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row1].getCandidateList().add(valueFound);
            return true;
        }
        else{
            return false;
        }
    }

    private boolean lockedCandidate6(MyJButton[][] sudokuGrid){
        int[] counter = new int[9];

        for (int i = 0; i < 9; ++i)
            counter[i] = 0; // initialize to 0

        for(int i = 6; i < 9; i++){   //rows
            for(int j = 3; j < 6; j++){   //cols
                if(sudokuGrid[i][j].getValue() == 0){
                    for(int a = 0; a < sudokuGrid[i][j].getCandidateList().size(); a++){
                        counter[(int) sudokuGrid[i][j].getCandidateList().get(a)-1] += 1;
                    }
                }
            }
        }

        int valueFound = -1;
        for(int i = 0; i < 9; i++){
            if(counter[i] == 2){
                valueFound = i + 1;
            }
        }

        int col = -1, row = -1, col1 = -1, row1 = -1;

        for (int i = 6; i < 9; ++i){
            for (int j = 3; j < 6; ++j){
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

        if(col == col1 && col != -1){
            checkCol(col + 1, valueFound, sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row1].getCandidateList().add(valueFound);
            return true;
        }
        else if(row == row1 && row != -1){
            checkRow(row + 1, valueFound, sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row1].getCandidateList().add(valueFound);
            return true;
        }
        else{
            return false;
        }
    }

    private boolean lockedCandidate7(MyJButton[][] sudokuGrid){
        int[] counter = new int[9];

        for (int i = 0; i < 9; ++i)
            counter[i] = 0; // initialize to 0

        for(int i = 0; i < 3; i++){   //rows
            for(int j = 6; j < 9; j++){   //cols
                if(sudokuGrid[i][j].getValue() == 0){
                    for(int a = 0; a < sudokuGrid[i][j].getCandidateList().size(); a++){
                        counter[(int) sudokuGrid[i][j].getCandidateList().get(a)-1] += 1;
                    }
                }
            }
        }

        int valueFound = -1;
        for(int i = 0; i < 9; i++){
            if(counter[i] == 2){
                valueFound = i + 1;
            }
        }

        int col = -1, row = -1, col1 = -1, row1 = -1;

        for (int i = 0; i < 3; ++i){
            for (int j = 6; j < 9; ++j){
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

        if(col == col1 && col != -1){
            checkCol(col + 1, valueFound, sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row1].getCandidateList().add(valueFound);
            return true;
        }
        else if(row == row1 && row != -1){
            checkRow(row + 1, valueFound, sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row1].getCandidateList().add(valueFound);
            return true;
        }
        else{
            return false;
        }
    }

    private boolean lockedCandidate8(MyJButton[][] sudokuGrid){
        int[] counter = new int[9];

        for (int i = 0; i < 9; ++i)
            counter[i] = 0; // initialize to 0

        for(int i = 3; i < 6; i++){   //rows
            for(int j = 6; j < 9; j++){   //cols
                if(sudokuGrid[i][j].getValue() == 0){
                    for(int a = 0; a < sudokuGrid[i][j].getCandidateList().size(); a++){
                        counter[(int) sudokuGrid[i][j].getCandidateList().get(a)-1] += 1;
                    }
                }
            }
        }

        int valueFound = -1;
        for(int i = 0; i < 9; i++){
            if(counter[i] == 2){
                valueFound = i + 1;
            }
        }

        int col = -1, row = -1, col1 = -1, row1 = -1;

        for (int i = 3; i < 6; ++i){
            for (int j = 6; j < 9; ++j){
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

        if(col == col1 && col != -1){
            checkCol(col + 1, valueFound, sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row1].getCandidateList().add(valueFound);
            return true;
        }
        else if(row == row1 && row != -1){
            checkRow(row + 1, valueFound, sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row1].getCandidateList().add(valueFound);
            return true;
        }
        else{
            return false;
        }
    }

    private boolean lockedCandidate9(MyJButton[][] sudokuGrid){
        int[] counter = new int[9];

        for (int i = 0; i < 9; ++i)
            counter[i] = 0; // initialize to 0

        for(int i = 6; i < 9; i++){   //rows
            for(int j = 6; j < 9; j++){   //cols
                if(sudokuGrid[i][j].getValue() == 0){
                    for(int a = 0; a < sudokuGrid[i][j].getCandidateList().size(); a++){
                        counter[(int) sudokuGrid[i][j].getCandidateList().get(a)-1] += 1;
                    }
                }
            }
        }

        int valueFound = -1;
        for(int i = 0; i < 9; i++){
            if(counter[i] == 2){
                valueFound = i + 1;
            }
        }

        int col = -1, row = -1, col1 = -1, row1 = -1;

        for (int i = 6; i < 9; ++i){
            for (int j = 6; j < 9; ++j){
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

        if(col == col1 && col != -1){
            checkCol(col + 1, valueFound, sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row1].getCandidateList().add(valueFound);
            return true;
        }
        else if(row == row1 && row != -1){
            checkRow(row + 1, valueFound, sudokuGrid);
            sudokuGrid[col][row].getCandidateList().add(valueFound);
            sudokuGrid[col1][row1].getCandidateList().add(valueFound);
            return true;
        }
        else{
            return false;
        }
    }
}
