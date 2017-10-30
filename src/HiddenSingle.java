/**
 * @author Edgar Martinez-Ayala and Ryan Moran
 * HiddenSingle class - Class that handles the HiddenSingle algorithm.
 *                      The algorithm checks one box at a time for a
 *                      hidden single, when one is found it returns true
 *                      and stops the process, if none are found then it returns
 *                      false.
 *
 */

public class HiddenSingle extends Hint{

    public boolean hiddenSingle(MyJButton[][] sudokuGrid){
        //makes calls to hiddenSingle till one is found in the grid
        if(hiddenSingle1(sudokuGrid)){
            checkForWin(sudokuGrid);
            return true;
        }
        else if(hiddenSingle2(sudokuGrid)){
            checkForWin(sudokuGrid);
            return true;
        }
        else if(hiddenSingle3(sudokuGrid)){
            checkForWin(sudokuGrid);
            return true;
        }
        else if(hiddenSingle4(sudokuGrid)){
            checkForWin(sudokuGrid);
            return true;
        }
        else if(hiddenSingle5(sudokuGrid)){
            checkForWin(sudokuGrid);
            return true;
        }
        else if(hiddenSingle6(sudokuGrid)){
            checkForWin(sudokuGrid);
            return true;
        }
        else if(hiddenSingle7(sudokuGrid)){
            checkForWin(sudokuGrid);
            return true;
        }
        else if(hiddenSingle8(sudokuGrid)){
            checkForWin(sudokuGrid);
            return true;
        }
        else if(hiddenSingle9(sudokuGrid)){
            checkForWin(sudokuGrid);
            return true;
        }
        else{
            return false;
        }
    }

    private boolean hiddenSingle1(MyJButton[][] sudokuGrid){
        int[] biggest = new int[9];

        for (int i = 0; i < 9; ++i)
            biggest[i] = 0; // initialize to 0

        for(int i = 0; i < 3; i++){   //rows
            for(int j = 0; j < 3; j++){   //cols
                if(sudokuGrid[i][j].getValue() == 0){
                    for(int a = 0; a < sudokuGrid[i][j].getCandidateList().size(); a++){
                        biggest[(int) sudokuGrid[i][j].getCandidateList().get(a)-1] += 1;
                    }
                }
            }
        }

        int valueFound = -1;
        for(int i = 0; i < 9; i++){  //looks for value in array
            if(biggest[i] == 1){
                valueFound = i + 1;
            }
        }

        for (int i = 0; i < 3; ++i){
            for (int j = 0; j < 3; ++j){
                if (sudokuGrid[i][j].getCandidateList().contains(valueFound) && sudokuGrid[i][j].getValue() == 0){
                    sudokuGrid[i][j].setValue(valueFound);  //adds value onto button
                    sudokuGrid[i][j].setText(Integer.toString(valueFound));  //updates board text

                    checkRow(j + 1, valueFound, sudokuGrid);  //updates row candidate list
                    checkCol(i + 1, valueFound, sudokuGrid);  //updates col candidate list
                    checkGrids(sudokuGrid);  //updates all Grids
                    return true;
                }
            }
        }
        return false;
    }

    //Same as HiddenSingle1 but instead it checks box 2
    private boolean hiddenSingle2(MyJButton[][] sudokuGrid){
        int[] biggest = new int[9];

        for (int i = 0; i < 9; ++i)
            biggest[i] = 0; // initialize to 0

        for(int i = 3; i < 6; i++){   //rows
            for(int j = 0; j < 3; j++){   //cols
                if(sudokuGrid[i][j].getValue() == 0){
                    for(int a = 0; a < sudokuGrid[i][j].getCandidateList().size(); a++){
                        biggest[(int) sudokuGrid[i][j].getCandidateList().get(a)-1] += 1;
                    }
                }
            }
        }

        int valueFound = -1;
        for(int i = 0; i < 9; i++){
            if(biggest[i] == 1){
                valueFound = i + 1;
            }
        }

        for (int i = 3; i < 6; ++i){
            for (int j = 0; j < 3; ++j){
                if (sudokuGrid[i][j].getCandidateList().contains(valueFound) && sudokuGrid[i][j].getValue() == 0){
                    sudokuGrid[i][j].setValue(valueFound);
                    sudokuGrid[i][j].setText(Integer.toString(valueFound));

                    checkRow(j + 1, valueFound, sudokuGrid);
                    checkCol(i + 1, valueFound, sudokuGrid);
                    checkGrids(sudokuGrid);
                    return true;
                }
            }
        }
        return false;
    }

    //Same as HiddenSingle1 but instead it checks box 3
    private boolean hiddenSingle3(MyJButton[][] sudokuGrid){
        int[] biggest = new int[9];

        for (int i = 0; i < 9; ++i)
            biggest[i] = 0; // initialize to 0

        for(int i = 6; i < 9; i++){   //rows
            for(int j = 0; j < 3; j++){   //cols
                if(sudokuGrid[i][j].getValue() == 0){
                    for(int a = 0; a < sudokuGrid[i][j].getCandidateList().size(); a++){
                        biggest[(int) sudokuGrid[i][j].getCandidateList().get(a)-1] += 1;
                    }
                }
            }
        }

        int valueFound = -1;
        for(int i = 0; i < 9; i++){
            if(biggest[i] == 1){
                valueFound = i + 1;
            }

        }

        for (int i = 6; i < 9; ++i){
            for (int j = 0; j < 3; ++j){
                if (sudokuGrid[i][j].getCandidateList().contains(valueFound) && sudokuGrid[i][j].getValue() == 0){
                    sudokuGrid[i][j].setValue(valueFound);
                    sudokuGrid[i][j].setText(Integer.toString(valueFound));

                    checkRow(j + 1, valueFound, sudokuGrid);
                    checkCol(i + 1, valueFound, sudokuGrid);
                    checkGrids(sudokuGrid);
                    return true;
                }
            }
        }
        return false;
    }

    //Same as HiddenSingle1 but instead it checks box 4
    private boolean hiddenSingle4(MyJButton[][] sudokuGrid){
        int[] biggest = new int[9];

        for (int i = 0; i < 9; ++i)
            biggest[i] = 0; // initialize to 0

        for(int i = 0; i < 3; i++){   //rows
            for(int j = 3; j < 6; j++){   //cols
                if(sudokuGrid[i][j].getValue() == 0){
                    for(int a = 0; a < sudokuGrid[i][j].getCandidateList().size(); a++){
                        biggest[(int) sudokuGrid[i][j].getCandidateList().get(a)-1] += 1;
                    }
                }
            }
        }

        int valueFound = -1;
        for(int i = 0; i < 9; i++){
            if(biggest[i] == 1){
                valueFound = i + 1;
            }
        }

        for (int i = 0; i < 3; ++i){
            for (int j = 3; j < 6; ++j){
                if (sudokuGrid[i][j].getCandidateList().contains(valueFound) && sudokuGrid[i][j].getValue() == 0){
                    sudokuGrid[i][j].setValue(valueFound);
                    sudokuGrid[i][j].setText(Integer.toString(valueFound));

                    checkRow(j + 1, valueFound, sudokuGrid);
                    checkCol(i + 1, valueFound, sudokuGrid);
                    checkGrids(sudokuGrid);
                    return true;
                }
            }
        }
        return false;
    }

    //Same as HiddenSingle1 but instead it checks box 5
    private boolean hiddenSingle5(MyJButton[][] sudokuGrid){
        int[] biggest = new int[9];

        for (int i = 0; i < 9; ++i)
            biggest[i] = 0; // initialize to 0

        for(int i = 3; i < 6; i++){   //rows
            for(int j = 3; j < 6; j++){   //cols
                if(sudokuGrid[i][j].getValue() == 0){
                    for(int a = 0; a < sudokuGrid[i][j].getCandidateList().size(); a++){
                        biggest[(int) sudokuGrid[i][j].getCandidateList().get(a)-1] += 1;
                    }
                }
            }
        }

        int valueFound = -1;
        for(int i = 0; i < 9; i++){
            if(biggest[i] == 1){
                valueFound = i + 1;
            }
        }

        for (int i = 3; i < 6; ++i){
            for (int j = 3; j < 6; ++j){
                if (sudokuGrid[i][j].getCandidateList().contains(valueFound) && sudokuGrid[i][j].getValue() == 0){
                    sudokuGrid[i][j].setValue(valueFound);
                    sudokuGrid[i][j].setText(Integer.toString(valueFound));

                    checkRow(j + 1, valueFound, sudokuGrid);
                    checkCol(i + 1, valueFound, sudokuGrid);
                    checkGrids(sudokuGrid);
                    return true;
                }
            }
        }
        return false;
    }

    //Same as HiddenSingle1 but instead it checks box 6
    private boolean hiddenSingle6(MyJButton[][] sudokuGrid){
        int[] biggest = new int[9];

        for (int i = 0; i < 9; ++i)
            biggest[i] = 0; // initialize to 0

        for(int i = 6; i < 9; i++){   //rows
            for(int j = 3; j < 6; j++){   //cols
                if(sudokuGrid[i][j].getValue() == 0){
                    for(int a = 0; a < sudokuGrid[i][j].getCandidateList().size(); a++){
                        biggest[(int) sudokuGrid[i][j].getCandidateList().get(a)-1] += 1;
                    }
                }
            }
        }

        int valueFound = -1;
        for(int i = 0; i < 9; i++){
            if(biggest[i] == 1){
                valueFound = i + 1;
            }
        }

        for (int i = 6; i < 9; ++i){
            for (int j = 3; j < 6; ++j){
                if (sudokuGrid[i][j].getCandidateList().contains(valueFound) && sudokuGrid[i][j].getValue() == 0){
                    sudokuGrid[i][j].setValue(valueFound);
                    sudokuGrid[i][j].setText(Integer.toString(valueFound));

                    checkRow(j + 1, valueFound, sudokuGrid);
                    checkCol(i + 1, valueFound, sudokuGrid);
                    checkGrids(sudokuGrid);
                    return true;
                }
            }
        }
        return false;
    }

    //Same as HiddenSingle1 but instead it checks box 7
    private boolean hiddenSingle7(MyJButton[][] sudokuGrid){
        int[] biggest = new int[9];

        for (int i = 0; i < 9; ++i)
            biggest[i] = 0; // initialize to 0

        for(int i = 0; i < 3; i++){   //rows
            for(int j = 6; j < 9; j++){   //cols
                if(sudokuGrid[i][j].getValue() == 0){
                    for(int a = 0; a < sudokuGrid[i][j].getCandidateList().size(); a++){
                        biggest[(int) sudokuGrid[i][j].getCandidateList().get(a)-1] += 1;
                    }
                }
            }
        }

        int valueFound = -1;
        for(int i = 0; i < 9; i++){
            if(biggest[i] == 1){
                valueFound = i + 1;
            }
        }

        for (int i = 0; i < 3; ++i){
            for (int j = 6; j < 9; ++j){
                if (sudokuGrid[i][j].getCandidateList().contains(valueFound) && sudokuGrid[i][j].getValue() == 0){
                    sudokuGrid[i][j].setValue(valueFound);
                    sudokuGrid[i][j].setText(Integer.toString(valueFound));

                    checkRow(j + 1, valueFound, sudokuGrid);
                    checkCol(i + 1, valueFound, sudokuGrid);
                    checkGrids(sudokuGrid);
                    return true;
                }
            }
        }
        return false;
    }

    //Same as HiddenSingle1 but instead it checks box 8
    private boolean hiddenSingle8(MyJButton[][] sudokuGrid){
        int[] biggest = new int[9];

        for (int i = 0; i < 9; ++i)
            biggest[i] = 0; // initialize to 0

        for(int i = 3; i < 6; i++){   //rows
            for(int j = 6; j < 9; j++){   //cols
                if(sudokuGrid[i][j].getValue() == 0){
                    for(int a = 0; a < sudokuGrid[i][j].getCandidateList().size(); a++){
                        biggest[(int) sudokuGrid[i][j].getCandidateList().get(a)-1] += 1;
                    }
                }
            }
        }

        int valueFound = -1;
        for(int i = 0; i < 9; i++){
            if(biggest[i] == 1){
                valueFound = i + 1;
            }
        }

        for (int i = 3; i < 6; ++i){
            for (int j = 6; j < 9; ++j){
                if (sudokuGrid[i][j].getCandidateList().contains(valueFound) && sudokuGrid[i][j].getValue() == 0){
                    sudokuGrid[i][j].setValue(valueFound);
                    sudokuGrid[i][j].setText(Integer.toString(valueFound));

                    checkRow(j + 1, valueFound, sudokuGrid);
                    checkCol(i + 1, valueFound, sudokuGrid);
                    checkGrids(sudokuGrid);
                    return true;
                }
            }
        }
        return false;
    }

    //Same as HiddenSingle1 but instead it checks box 9
    private boolean hiddenSingle9(MyJButton[][] sudokuGrid){
        int[] biggest = new int[9];

        for (int i = 0; i < 9; ++i)
            biggest[i] = 0; // initialize to 0

        for(int i = 6; i < 9; i++){   //rows
            for(int j = 6; j < 9; j++){   //cols
                if(sudokuGrid[i][j].getValue() == 0){
                    for(int a = 0; a < sudokuGrid[i][j].getCandidateList().size(); a++){
                        biggest[(int) sudokuGrid[i][j].getCandidateList().get(a)-1] += 1;
                    }
                }
            }
        }

        int valueFound = -1;
        for(int i = 0; i < 9; i++){
            if(biggest[i] == 1){
                valueFound = i + 1;
            }
        }

        for (int i = 6; i < 9; ++i){
            for (int j = 6; j < 9; ++j){
                if (sudokuGrid[i][j].getCandidateList().contains(valueFound) && sudokuGrid[i][j].getValue() == 0){
                    sudokuGrid[i][j].setValue(valueFound);
                    sudokuGrid[i][j].setText(Integer.toString(valueFound));

                    checkRow(j + 1, valueFound, sudokuGrid);
                    checkCol(i + 1, valueFound, sudokuGrid);
                    checkGrids(sudokuGrid);
                    return true;
                }
            }
        }
        return false;
    }
}
