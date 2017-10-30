import javax.swing.*;

public class Solve extends Hint {

    private Single s = new Single();
    private HiddenSingle hs = new HiddenSingle();
    private LockedCandidate lc = new LockedCandidate();
    private NakedPair np = new NakedPair();

    //
    // Solves puzzle using 4 algorithms
    //
    public void solvePuzzle(MyJButton[][] sudokuGrid) {
        while (true) {

            s.single(sudokuGrid);
            hs.hiddenSingle(sudokuGrid);
            lc.lockedCandidate(sudokuGrid);
            np.processNakedPair(sudokuGrid);
        }
    }
}
