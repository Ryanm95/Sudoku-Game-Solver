import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;


public class GUI extends JFrame implements ActionListener{

    private String values[];         // initial values from the textfile
    private MyJButton sudokuGrid[][];     // 9x9 grid of MyJButtons
    private Container container;
    private GridLayout grid;

    private final int rows = 9;
    private final int cols = 9;

    public GUI(){
        super("Sudoku");
        getContentPane().setLayout(new BorderLayout());

        grid = new GridLayout(9,9);
        container = getContentPane();
        container.setLayout( grid );

        setSize( 800, 800 );
        setVisible( true );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void setInitialBoard(){

    }
}
