import java.util.ArrayList;

public class Hint {

    protected void checkRow(int row, int value, MyJButton[][] sudokuGrid) {
        for (int i = 0; i < 9; i++) {
            sudokuGrid[i][row - 1].deleteCandidate(value);
        }
    }

    protected void checkCol(int col, int value, MyJButton[][] sudokuGrid) {
        for (int i = 0; i < 9; i++) {
            sudokuGrid[col - 1][i].deleteCandidate(value);
        }
    }

    protected void checkGrids(MyJButton[][] sudokuGrid) {
        checkGrid1(sudokuGrid);
        checkGrid2(sudokuGrid);
        checkGrid3(sudokuGrid);
        checkGrid4(sudokuGrid);
        checkGrid5(sudokuGrid);
        checkGrid6(sudokuGrid);
        checkGrid7(sudokuGrid);
        checkGrid8(sudokuGrid);
        checkGrid9(sudokuGrid);
    }

    protected void checkGrid1(MyJButton[][] sudokuGrid) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int value = sudokuGrid[i][j].getValue();
                for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 3; b++) {
                        sudokuGrid[a][b].deleteCandidate(value);
                    }
                }
            }
        }
    }

    protected void checkGrid2(MyJButton[][] sudokuGrid) {
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                int value = sudokuGrid[i][j].getValue();
                for (int a = 3; a < 6; a++) {
                    for (int b = 0; b < 3; b++) {
                        sudokuGrid[a][b].deleteCandidate(value);
                    }
                }
            }
        }
    }

    protected void checkGrid3(MyJButton[][] sudokuGrid) {
        for (int i = 6; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                int value = sudokuGrid[i][j].getValue();
                for (int a = 6; a < 9; a++) {
                    for (int b = 0; b < 3; b++) {
                        sudokuGrid[a][b].deleteCandidate(value);
                    }
                }
            }
        }
    }

    protected void checkGrid4(MyJButton[][] sudokuGrid) {
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 6; j++) {
                int value = sudokuGrid[i][j].getValue();
                for (int a = 0; a < 3; a++) {
                    for (int b = 3; b < 6; b++) {
                        sudokuGrid[a][b].deleteCandidate(value);
                    }
                }
            }
        }
    }

    protected void checkGrid5(MyJButton[][] sudokuGrid) {
        for (int i = 3; i < 6; i++) {
            for (int j = 3; j < 6; j++) {
                int value = sudokuGrid[i][j].getValue();
                for (int a = 3; a < 6; a++) {
                    for (int b = 3; b < 6; b++) {
                        sudokuGrid[a][b].deleteCandidate(value);
                    }
                }
            }
        }
    }

    protected void checkGrid6(MyJButton[][] sudokuGrid) {
        for (int i = 6; i < 9; i++) {
            for (int j = 3; j < 6; j++) {
                int value = sudokuGrid[i][j].getValue();
                for (int a = 6; a < 9; a++) {
                    for (int b = 3; b < 6; b++) {
                        sudokuGrid[a][b].deleteCandidate(value);
                    }
                }
            }
        }
    }

    protected void checkGrid7(MyJButton[][] sudokuGrid) {
        for (int i = 0; i < 3; i++) {
            for (int j = 6; j < 9; j++) {
                int value = sudokuGrid[i][j].getValue();
                for (int a = 0; a < 3; a++) {
                    for (int b = 6; b < 9; b++) {
                        sudokuGrid[a][b].deleteCandidate(value);
                    }
                }
            }
        }
    }

    protected void checkGrid8(MyJButton[][] sudokuGrid) {
        for (int i = 3; i < 6; i++) {
            for (int j = 6; j < 9; j++) {
                int value = sudokuGrid[i][j].getValue();
                for (int a = 3; a < 6; a++) {
                    for (int b = 6; b < 9; b++) {
                        sudokuGrid[a][b].deleteCandidate(value);
                    }
                }
            }
        }
    }

    protected void checkGrid9(MyJButton[][] sudokuGrid) {
        for (int i = 6; i < 9; i++) {
            for (int j = 6; j < 9; j++) {
                int value = sudokuGrid[i][j].getValue();
                for (int a = 6; a < 9; a++) {
                    for (int b = 6; b < 9; b++) {
                        sudokuGrid[a][b].deleteCandidate(value);
                    }
                }
            }
        }
    }
}
