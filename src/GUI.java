import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;

import javax.imageio.ImageIO;
import javax.swing.*;


public class GUI extends JFrame implements ActionListener{

    private MyJButton sudokuGrid[][] = new MyJButton[9][9];     // 9x9 grid of MyJButtons
    private JPanel container = new JPanel(new BorderLayout());
    private JPanel grid = new JPanel();
    private JPanel numberOptions = new JPanel();

    private JPanel p[][] = new JPanel[3][3];
    private final String values[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", " "};
    private MyJButton[] choices;

    public GUI(){
        super("Sudoku");
        getContentPane().setBackground(Color.gray);

        setUpSudokuGrid();              //
        setUpChoiceButtons();
        container.add(grid, BorderLayout.WEST);
        container.add(numberOptions);

        add(container);

        addMenu();  //Adds the menu bar to the window
        setSize( 800, 800 );
        setVisible( true );
    }

    public void actionPerformed(ActionEvent event) {

        MyJButton click = (MyJButton) event.getSource();
        MyJButton temp;
        int value;

//        if(click.getChoiceButtons() == true) {
//            temp = new MyJButton()
//        }
//        int row = temp.getRow();
//        int col = temp.getCol();
//        boolean phase = temp.getOriginalPiece();
//        int value = temp.getValue();



//        //Window displayed when puzzle is solve
//        JOptionPane.showMessageDialog(this, "Row: " + row + "\n " +
//                        "Col: " + col + "\n" + "Phase: " + phase + "\n Num:" + value,
//                "Position", JOptionPane.PLAIN_MESSAGE);
    }

    // Creates menu bar and attach it to GUI window
    // and adds all buttons like exit, add puzzle,
    // and load a puzzle from a file
    private void addMenu() {
        //set up File menu and its menu items
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');

        //set up Load Puzzle submenu item under File
        JMenuItem loadItem = new JMenuItem("Load Puzzle");
        loadItem.setMnemonic('L');
        fileMenu.add(loadItem);
        loadItem.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == new JMenuItem("Load Puzzle")) {
                            JFileChooser fc = new JFileChooser();
                            int i = fc.showOpenDialog(loadItem);
                            if (i == JFileChooser.APPROVE_OPTION) {
                                File f = fc.getSelectedFile();
                                //String filepath = f.getPath();
                                try {
                                    BufferedReader br = new BufferedReader(new FileReader(f));
                                    String s1= "", s2 = "";
                                    while ((s1 = br.readLine()) != null) {
                                        s2 += s1 + "\n";
                                    }
                                    //ta.setText(s2);
                                    //br.close();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                }
        );

        //set up Store Puzzle submenu item under File
        JMenuItem storeItem = new JMenuItem("Store Puzzle");
        storeItem.setMnemonic('S');
        fileMenu.add(storeItem);
        fileMenu.addSeparator();
        storeItem.addActionListener(
                new ActionListener() {  // anonymous inner class
                    // Loads new puzzle into program
                    public void actionPerformed(ActionEvent event) {
                        JOptionPane.showMessageDialog(GUI.this,
                                "Perform action",
                                "Store Puzzle", JOptionPane.PLAIN_MESSAGE);
                    }
                }  // end anonymous inner class
        ); // end call to addActionListener

        //set up Exit submenu item under File
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic('x');
        fileMenu.add(exitItem);
        exitItem.addActionListener(
                new ActionListener(){  // anonymous inner class
                    // terminate application when user clicks exitItem
                    public void actionPerformed(ActionEvent event){
                        System.exit(0);
                    }
                }  // end anonymous inner class
        ); // end call to addActionListener

        JMenu helpMenu = new JMenu("Help");
        fileMenu.setMnemonic('H');

        //set up How to play submenu item under Help
        JMenuItem helpItem = new JMenuItem("How to Play");
        helpItem.setMnemonic('p');
        helpMenu.add(helpItem);
        helpItem.addActionListener(
                new ActionListener(){  // anonymous inner class
                    public void actionPerformed(ActionEvent event){
                        howToPlay();
                    }
                }  // end anonymous inner class
        ); // end call to addActionListener

        //set up How to use interface submenu item under Help
        JMenuItem interfaceItem = new JMenuItem("How to Use Interface");
        interfaceItem.setMnemonic('i');
        helpMenu.add(interfaceItem);
        interfaceItem.addActionListener(
                new ActionListener(){  // anonymous inner class
                    public void actionPerformed(ActionEvent event){
                        howToUseGUI();
                    }
                }  // end anonymous inner class
        ); // end call to addActionListener

        //set up About submenu item under Help
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setMnemonic('A');
        helpMenu.add(aboutItem);
        aboutItem.addActionListener(
                new ActionListener(){  // anonymous inner class
                    // display message dialog when user selects About...
                    public void actionPerformed(ActionEvent event){
                        JOptionPane.showMessageDialog( GUI.this,
                                "Authors:\n   Edgar Martinez-Ayala -> emart9\n" +
                                        "   Ryan Moran                -> rmoran8\n",
                                "About",JOptionPane.PLAIN_MESSAGE );
                    }
                }  // end anonymous inner class
        ); // end call to addActionListener

        JMenu hintMenu = new JMenu("Hint");
        fileMenu.setMnemonic('h');

        //set up Check on Fill  submenu item under hint
        JCheckBoxMenuItem checkItem = new JCheckBoxMenuItem("Check on Fill");
        //checkItem.setMnemonic('c');
        hintMenu.add(checkItem);
        checkItem.addActionListener(
                new ActionListener(){  // anonymous inner class
                    public void actionPerformed(ActionEvent event){
//                        JOptionPane.showMessageDialog(GUI.this,
//                                "add toggle",
//                                "Check on Fill", JOptionPane.PLAIN_MESSAGE);
                    }
                }  // end anonymous inner class
        ); // end call to addActionListener\

        JMenuItem single = new JMenuItem("Single");
        hintMenu.add(single);
        single.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
//
                    }
                }
        );

        JMenuItem hiddenSingle = new JMenuItem("Hidden Single");
        hintMenu.add(hiddenSingle);
        hiddenSingle.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {

                    }
                }
        );

        JMenuItem lockedCandidate = new JMenuItem("Locked Candidate");
        hintMenu.add(lockedCandidate);
        lockedCandidate.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {

                    }
                }
        );

        JMenuItem nakedPairs = new JMenuItem("Naked Pairs");
        hintMenu.add(nakedPairs);
        nakedPairs.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {

                    }
                }
        );

        // Makes bar and adds all buttons to it
        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        bar.add(fileMenu);
        bar.add(helpMenu);
        bar.add(hintMenu);
    }

    private void setUpChoiceButtons(){
        choices = new MyJButton[values.length];

        for(int i = 0; i < values.length; ++i){
            choices[i] = new MyJButton(values[i]);
            choices[i].setValue(i + 1);
            choices[i].setChoiceButtons(true);
            choices[i].addActionListener(this);
            choices[i].setPreferredSize(new Dimension(55,60));
            numberOptions.add(choices[i]);
        }

        try {
            Image img = ImageIO.read(getClass().getResource("eraser.png"));
            choices[values.length -1 ].setIcon(new ImageIcon(img));
            choices[values.length -1 ].setHorizontalTextPosition(SwingConstants.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpSudokuGrid(){
        for(int rows = 0; rows <= 8; rows++){       // makes buttons
            for(int col = 0; col <= 8; col++){
                sudokuGrid[col][rows] = new MyJButton(" ", col + 1, rows + 1, 0, false, false);
                sudokuGrid[col][rows].addActionListener(this);
            }
        }

        for(int x = 0; x <= 2; x++){        // make panel for grid to sit on
            for(int y = 0; y <= 2; y++){
                p[x][y] = new JPanel(new GridLayout(3, 3));
            }
        }

        grid.setLayout(new GridLayout(3, 3, 10,10));


        for(int i = 0; i <= 2; i++){            // builds  the board
            for(int u = 0; u <= 2; u++){
                for(int x = 0; x <= 2; x++) {
                    for (int y = 0; y <= 2; y++) {
                        p[u][i].add(sudokuGrid[y + u * 3][x + i * 3]);
                    }
                }
                grid.add(p[u][i]);
            }
        }
    }

    private void clearBoard(){
        for(int i = 0; i < 9; ++i){
            for(int j = 0; j < 9; ++j){
                sudokuGrid[i][j].setText(" ");
                sudokuGrid[i][j].setValue(0);
                sudokuGrid[i][j].setOriginalPiece(false);
            }
        }
    }

    private void howToPlay(){
        JOptionPane.showMessageDialog(GUI.this,
                                "Sudoku is a puzzle that often uses a 9x9 grid of 81 cells. The grid is divided into rows, columns\n" +
                                        "and boxes. The boxes are 3x3 sub-grids of 9 cells. Thus each row, column and box contains 9\n" +
                                        "cells. The object is the fill in the numbers from 1 to 9 so that each row, column and box contain\n" +
                                        "each number from 1 to 9 only once",
                                "How to Play", JOptionPane.PLAIN_MESSAGE);
    }

    private void readfile(String filename){         // read file selected .txt file
        String line;
        String[] parts;
        int col, row, value;

        clearBoard();

        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                parts = line.split(" ", 3);
                row = Integer.parseInt(parts[0]);
                col = Integer.parseInt(parts[1]);
                value = Integer.parseInt(parts[2]);

                sudokuGrid[col - 1][row - 1].setText(parts[2]);   //updates grid text to new text
                sudokuGrid[col - 1][row - 1].setValue(value);   //updates grid value  to new value
                sudokuGrid[col - 1][row - 1].setOriginalPiece(true);  //updates original button to true

            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + filename + "'");
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    private void howToUseGUI(){
        JOptionPane.showMessageDialog(GUI.this,
                "File:\n   Click Load Puzzle to load a puzzle from your machine\n" +
                         "   Click Store Puzzle to save current progress on a puzzle to your machine\n" +
                         "   Click Exit to exit the program\n" +
                         "Hint:\n   Click Check On Fill to have program check if you made a valid move\n" +
                         "   Click Single to fill in one open space\n" +
                         "   Click Hidden Single to fill in one open space\n" +
                         "   Click Locked Candidate to narrow down the number of values for that cell\n" +
                         "   Click Naked Pairs to narrow down the number of values for that cell\n",
                    "How to Use Interface", JOptionPane.PLAIN_MESSAGE);
    }
}