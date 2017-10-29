public class Single extends Hint{

    public boolean single(MyJButton[][] sudokuGrid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuGrid[j][i].getCandidateList().size() == 1 && sudokuGrid[j][i].getValue() == 0) {
                    int value = (int) sudokuGrid[j][i].getCandidateList().get(0);       // get value of candidate
                    sudokuGrid[j][i].setValue(value);                   // set value
                    sudokuGrid[j][i].setText(Integer.toString(value));      // set text

                    checkRow(i + 1, value, sudokuGrid);
                    checkCol(j + 1, value, sudokuGrid);

                    checkGrids(sudokuGrid);
                    checkForWin(sudokuGrid);
                    return true;
                }
            }
        }
        return false;
    }
}