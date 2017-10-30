/**
 * @author Edgar Martinez-Ayala and Ryan Moran
 * Solve class - Class that has a loop that runs till program is solved
 *               Prints messdage telling the player the puzzle has been solved '
 *               and then exits the program.
 *
 */

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
            try {
                System.out.println(" Scanning...");
                Thread.sleep(1000); // 1 second
            } catch (InterruptedException ex) {
                // handle error
            }
            hs.hiddenSingle(sudokuGrid);
            lc.lockedCandidate(sudokuGrid);
            np.processNakedPair(sudokuGrid);
        }
    }
}