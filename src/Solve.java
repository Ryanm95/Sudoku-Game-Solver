import javax.swing.*;

public class Solve {

    private Single s = new Single();
    private HiddenSingle hs = new HiddenSingle();
    private LockedCandidate lc = new LockedCandidate();
    private NakedPair np = new NakedPair();
    private Timer timer;

    //
    // Solves puzzle using 4 algorithms
    //
    public void solvePuzzle( MyJButton[][] sudokuGrid){
        while(true){
            s.single(sudokuGrid);
            //timer.setDelay(100);
            hs.hiddenSingle(sudokuGrid);
            lc.lockedCandidate(sudokuGrid);
            if(checkForWin(sudokuGrid)){
                //Window displayed when puzzle is solved
                JOptionPane.showMessageDialog(null, "You Have solved the Puzzle",
                "Winner", JOptionPane.PLAIN_MESSAGE);

                System.exit(0);
            }
        }
    }

    //
    // Checks sudoku board for a win
    //
    private boolean checkForWin( MyJButton[][] sudokuGrid){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(sudokuGrid[i][j].getCandidateList().size() != 0){
                    return false;
                }
            }
        }
        return true;
    }
}
