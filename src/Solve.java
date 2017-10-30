import javax.swing.*;

public class Solve extends Hint {

    private Single s = new Single();
    private HiddenSingle hs = new HiddenSingle();
    private LockedCandidate lc = new LockedCandidate();
    private NakedPair np = new NakedPair();
    private Timer timer = new Timer(1000, null);

    //
    // Solves puzzle using 4 algorithms
    //
    public void solvePuzzle(MyJButton[][] sudokuGrid) {
        while (true) {

            if(s.single(sudokuGrid)){
                timer.setDelay(1000);
            }
            if(hs.hiddenSingle(sudokuGrid)){
                timer.setDelay(1000);
            }
            lc.lockedCandidate(sudokuGrid);
            np.processNakedPair(sudokuGrid);
        }
    }
}
