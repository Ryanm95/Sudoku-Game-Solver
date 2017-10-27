import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;


public class GUI extends JFrame implements ActionListener{

    private MyJButton sudokuGrid[][] = new MyJButton[9][9];     // 9x9 grid of MyJButtons
    private JPanel container = new JPanel(new BorderLayout());
    private JPanel grid = new JPanel();
    private JPanel numberOptions = new JPanel();

    private JPanel p[][] = new JPanel[3][3];
    private final String values[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", ""};
    private MyJButton[] choices;
    private int value;
    private boolean choiceClickedFirst = false;
    private boolean eraserClicked = false;
    private boolean checkOnFill = false;

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

        for(int i = 0; i < values.length-1; ++i){
            if(event.getSource() == choices[i]){        // button we clicked was an number choice
                value = choices[i].getValue();
                choiceClickedFirst = true;
                eraserClicked = false;
                //System.out.println(value);
            }
        }
        if(event.getSource() == choices[9]){
            value = 0;
            eraserClicked = true;
            choiceClickedFirst = true;
            //System.out.println("Clicked");
        }
        if(click.getOriginalPiece() == false && click.getChoiceButtons() == false && choiceClickedFirst == true) {      // in sudoku grid... not original piece
            if (eraserClicked == true) {
                if (click.getValue() != 0) {
                    click.setText(" ");
                    click.setValue(value);
                    for (int i = 1; i < 10; i++) {
                        for (int j = 1; j < 10; j++) {
                            sudokuGrid[i - 1][j - 1].reinitalizeCandidateList();
                        }
                    }
                    for (int i = 1; i < 10; i++) {
                        for (int j = 1; j < 10; j++) {
                            checkRow(i, sudokuGrid[j - 1][i - 1].getValue());
                            checkCol(j, sudokuGrid[j - 1][i - 1].getValue());
                        }
                    }
                    checkGrids();
                }
                else{
                    //Window displayed when digit can't be place at this position
                    JOptionPane.showMessageDialog(this,
                            "Can't erase empty cell.",
                            "Error", JOptionPane.PLAIN_MESSAGE);
                }
            }
            else {
                if(checkOnFill) {
                    ArrayList candidate = click.getCandidateList();
                    if(candidate.contains(value)) {
                        click.setText(Integer.toString(value));
                        click.setValue(value);
                        checkRow(click.getRow(), value);
                        checkCol(click.getCol(), value);
                        checkGrids();
                    }
                    else{
                        //Window displayed when digit can't be place at this position
                        JOptionPane.showMessageDialog(this,
                                "Can't place digit at current position.",
                                "Error", JOptionPane.PLAIN_MESSAGE);
                    }
                }
                else{
                    click.setText(Integer.toString(value));
                    click.setValue(value);
                    checkRow(click.getRow(), value);
                    checkCol(click.getCol(), value);
                    checkGrids();
                }
            }
        }
        int row = click.getRow();
        int col = click.getCol();
        boolean phase = click.getOriginalPiece();
        int value = click.getValue();
        ArrayList c = click.getCandidateList();
        //Window displayed when puzzle is solved
        JOptionPane.showMessageDialog(this, "Row: " + row + "\n " +
                        "Col: " + col + "\n" + "Phase: " + phase + "\n Num:" + value + "\nList: " + c,
                "Position", JOptionPane.PLAIN_MESSAGE);
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
                        JFileChooser fc = new JFileChooser();
                        int i = fc.showOpenDialog(null);
                        if (i == JFileChooser.APPROVE_OPTION) {
                            File f = fc.getSelectedFile();
                            try {
                                BufferedReader br = new BufferedReader(new FileReader(f));
                                String s1 = "";
                                while ((s1 = br.readLine()) != null) {
                                    readfile(f);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
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

                        final JFileChooser SaveAs = new JFileChooser();
                        int actionDialog = SaveAs.showSaveDialog(null);
                        if (actionDialog != JFileChooser.APPROVE_OPTION) {
                            return;
                        }

                        File f = new File(SaveAs.getSelectedFile() +".txt");
                        try{
                            FileWriter fw = new FileWriter(f);
                            for(int i = 1; i < 10; i++) {
                                for (int j = 1; j < 10; j++) {
                                    if(sudokuGrid[i -1][j - 1].getValue() != 0){
                                        fw.write(Integer.toString(j) + " " + Integer.toString(i) +
                                                " " + Integer.toString(sudokuGrid[i -1][j -1].getValue()) + "\n");
                                    }
                                }
                            }
                            fw.close();
                        }catch(Exception ex){

                        }
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
                        checkOnFill = !checkOnFill;
                    }
                }  // end anonymous inner class
        ); // end call to addActionListener\

        JMenuItem single = new JMenuItem("Single");
        hintMenu.add(single);
        single.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        if(!single()){
                            JOptionPane.showMessageDialog(GUI.this, " No singles on board",
                                    "Single", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                }
        );

        JMenuItem hiddenSingle = new JMenuItem("Hidden Single");
        hintMenu.add(hiddenSingle);
        hiddenSingle.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        if(!hiddenSingle()){
                            JOptionPane.showMessageDialog(GUI.this, " No hidden singles found",
                                    "Hidden Single", JOptionPane.PLAIN_MESSAGE);
                        }
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

        JMenuItem solve = new JMenuItem("Solve Puzzle");
        hintMenu.add(solve);
        solve.addActionListener(
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
                sudokuGrid[i][j].reinitalizeCandidateList();
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

    private void readfile(File filename){         // read file selected .txt file
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

                checkRow(row, value);
                checkCol(col, value);
            }
            checkGrids();

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

    private boolean single(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(sudokuGrid[j][i].getCandidateList().size() == 1 && sudokuGrid[j][i].getValue() == 0){
                    int value = (int) sudokuGrid[j][i].getCandidateList().get(0);       // get value of candidate
                    sudokuGrid[j][i].setValue(value);                   // set value
                    sudokuGrid[j][i].setText(Integer.toString(value));      // set text

                    checkRow(i + 1, value);
                    checkCol(j + 1, value);

                    checkGrids();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hiddenSingle(){
        if(hiddenSingle1()){
            return true;
        }
        else if(hiddenSingle2()){
            return true;
        }
        else if(hiddenSingle3()){
            return true;
        }
        else if(hiddenSingle4()){
            return true;
        }
        else if(hiddenSingle5()){
            return true;
        }
        else if(hiddenSingle6()){
            return true;
        }
        else if(hiddenSingle7()){
            return true;
        }
        else if(hiddenSingle8()){
            return true;
        }
        else if(hiddenSingle9()){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean hiddenSingle1(){
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
        for(int i = 0; i < 9; i++){
            if(biggest[i] == 1){
                valueFound = i + 1;
            }
//            System.out.print(biggest[i] + " | ");   TODO delete later
        }

        //System.out.println("VALUE FOUND: " + valueFound);   //TODO delete later

        for (int i = 0; i < 3; ++i){
            for (int j = 0; j < 3; ++j){
                if (sudokuGrid[i][j].getCandidateList().contains(valueFound) && sudokuGrid[i][j].getValue() == 0){
                    sudokuGrid[i][j].setValue(valueFound);
                    sudokuGrid[i][j].setText(Integer.toString(valueFound));

                    checkRow(i + 1, valueFound);
                    checkCol(j + 1, valueFound);
                    checkGrids();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hiddenSingle2(){
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
//            System.out.print(biggest[i] + " | ");   TODO delete later
        }

        //System.out.println("VALUE FOUND: " + valueFound);   //TODO delete later

        for (int i = 3; i < 6; ++i){
            for (int j = 0; j < 3; ++j){
                if (sudokuGrid[i][j].getCandidateList().contains(valueFound) && sudokuGrid[i][j].getValue() == 0){
                    sudokuGrid[i][j].setValue(valueFound);
                    sudokuGrid[i][j].setText(Integer.toString(valueFound));

                    checkRow(i + 1, valueFound);
                    checkCol(j + 1, valueFound);
                    checkGrids();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hiddenSingle3(){
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
//            System.out.print(biggest[i] + " | ");   TODO delete later
        }

        //System.out.println("VALUE FOUND: " + valueFound);   //TODO delete later

        for (int i = 6; i < 9; ++i){
            for (int j = 0; j < 3; ++j){
                if (sudokuGrid[i][j].getCandidateList().contains(valueFound) && sudokuGrid[i][j].getValue() == 0){
                    sudokuGrid[i][j].setValue(valueFound);
                    sudokuGrid[i][j].setText(Integer.toString(valueFound));

                    checkRow(i + 1, valueFound);
                    checkCol(j + 1, valueFound);
                    checkGrids();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hiddenSingle4(){
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
//            System.out.print(biggest[i] + " | ");   TODO delete later
        }

        //System.out.println("VALUE FOUND: " + valueFound);   //TODO delete later

        for (int i = 0; i < 3; ++i){
            for (int j = 3; j < 6; ++j){
                if (sudokuGrid[i][j].getCandidateList().contains(valueFound) && sudokuGrid[i][j].getValue() == 0){
                    sudokuGrid[i][j].setValue(valueFound);
                    sudokuGrid[i][j].setText(Integer.toString(valueFound));

                    checkRow(i + 1, valueFound);
                    checkCol(j + 1, valueFound);
                    checkGrids();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hiddenSingle5(){
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
//            System.out.print(biggest[i] + " | ");   TODO delete later
        }

        //System.out.println("VALUE FOUND: " + valueFound);   //TODO delete later

        for (int i = 3; i < 6; ++i){
            for (int j = 3; j < 6; ++j){
                if (sudokuGrid[i][j].getCandidateList().contains(valueFound) && sudokuGrid[i][j].getValue() == 0){
                    sudokuGrid[i][j].setValue(valueFound);
                    sudokuGrid[i][j].setText(Integer.toString(valueFound));

                    checkRow(i + 1, valueFound);
                    checkCol(j + 1, valueFound);
                    checkGrids();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hiddenSingle6(){
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
//            System.out.print(biggest[i] + " | ");   TODO delete later
        }

        //System.out.println("VALUE FOUND: " + valueFound);   //TODO delete later

        for (int i = 6; i < 9; ++i){
            for (int j = 3; j < 6; ++j){
                if (sudokuGrid[i][j].getCandidateList().contains(valueFound) && sudokuGrid[i][j].getValue() == 0){
                    sudokuGrid[i][j].setValue(valueFound);
                    sudokuGrid[i][j].setText(Integer.toString(valueFound));

                    checkRow(i + 1, valueFound);
                    checkCol(j + 1, valueFound);
                    checkGrids();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hiddenSingle7(){
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
//            System.out.print(biggest[i] + " | ");   TODO delete later
        }

        //System.out.println("VALUE FOUND: " + valueFound);   //TODO delete later

        for (int i = 0; i < 3; ++i){
            for (int j = 6; j < 9; ++j){
                if (sudokuGrid[i][j].getCandidateList().contains(valueFound) && sudokuGrid[i][j].getValue() == 0){
                    sudokuGrid[i][j].setValue(valueFound);
                    sudokuGrid[i][j].setText(Integer.toString(valueFound));

                    checkRow(i + 1, valueFound);
                    checkCol(j + 1, valueFound);
                    checkGrids();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hiddenSingle8(){
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
//            System.out.print(biggest[i] + " | ");   TODO delete later
        }

        //System.out.println("VALUE FOUND: " + valueFound);   //TODO delete later

        for (int i = 3; i < 6; ++i){
            for (int j = 6; j < 9; ++j){
                if (sudokuGrid[i][j].getCandidateList().contains(valueFound) && sudokuGrid[i][j].getValue() == 0){
                    sudokuGrid[i][j].setValue(valueFound);
                    sudokuGrid[i][j].setText(Integer.toString(valueFound));

                    checkRow(i + 1, valueFound);
                    checkCol(j + 1, valueFound);
                    checkGrids();
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hiddenSingle9(){
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
//            System.out.print(biggest[i] + " | ");   TODO delete later
        }

        //System.out.println("VALUE FOUND: " + valueFound);   //TODO delete later

        for (int i = 6; i < 9; ++i){
            for (int j = 6; j < 9; ++j){
                if (sudokuGrid[i][j].getCandidateList().contains(valueFound) && sudokuGrid[i][j].getValue() == 0){
                    sudokuGrid[i][j].setValue(valueFound);
                    sudokuGrid[i][j].setText(Integer.toString(valueFound));

                    checkRow(i + 1, valueFound);
                    checkCol(j + 1, valueFound);
                    checkGrids();
                    return true;
                }
            }
        }
        return false;
    }



    private void checkRow(int row, int value){
        for(int i = 0; i < 9; i++){
            sudokuGrid[i][row - 1].deleteCandidate(value);
        }
    }

    private void checkCol(int col, int value){
        for(int i = 0; i < 9; i++){
            sudokuGrid[col - 1][i].deleteCandidate(value);
        }
    }

    private void checkGrids(){
        checkGrid1();
        checkGrid2();
        checkGrid3();
        checkGrid4();
        checkGrid5();
        checkGrid6();
        checkGrid7();
        checkGrid8();
        checkGrid9();
    }

    private void checkGrid1(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3 ; j++){
                int value = sudokuGrid[i][j].getValue();
                for(int a = 0; a < 3; a++) {
                    for (int b = 0; b < 3; b++) {
                        sudokuGrid[a][b].deleteCandidate(value);
                    }
                }
            }
        }
    }

    private void checkGrid2(){
        for(int i = 3; i < 6; i++){
            for(int j = 0; j < 3 ; j++){
                int value = sudokuGrid[i][j].getValue();
                for(int a = 3; a < 6; a++) {
                    for (int b = 0; b < 3; b++) {
                        sudokuGrid[a][b].deleteCandidate(value);
                    }
                }
            }
        }
    }

    private void checkGrid3(){
        for(int i = 6; i < 9; i++){
            for(int j = 0; j < 3 ; j++){
                int value = sudokuGrid[i][j].getValue();
                for(int a = 6; a < 9; a++) {
                    for (int b = 0; b < 3; b++) {
                        sudokuGrid[a][b].deleteCandidate(value);
                    }
                }
            }
        }
    }

    private void checkGrid4(){
        for(int i = 0; i < 3; i++){
            for(int j = 3; j < 6 ; j++){
                int value = sudokuGrid[i][j].getValue();
                for(int a = 0; a < 3; a++) {
                    for (int b = 3; b < 6; b++) {
                        sudokuGrid[a][b].deleteCandidate(value);
                    }
                }
            }
        }
    }

    private void checkGrid5(){
        for(int i = 3; i < 6; i++){
            for(int j = 3; j < 6 ; j++){
                int value = sudokuGrid[i][j].getValue();
                for(int a = 3; a < 6; a++) {
                    for (int b = 3; b < 6; b++) {
                        sudokuGrid[a][b].deleteCandidate(value);
                    }
                }
            }
        }
    }

    private void checkGrid6(){
        for(int i = 6; i < 9; i++){
            for(int j = 3; j < 6 ; j++){
                int value = sudokuGrid[i][j].getValue();
                for(int a = 6; a < 9; a++) {
                    for (int b = 3; b < 6; b++) {
                        sudokuGrid[a][b].deleteCandidate(value);
                    }
                }
            }
        }
    }

    private void checkGrid7(){
        for(int i = 0; i < 3; i++){
            for(int j = 6; j < 9 ; j++){
                int value = sudokuGrid[i][j].getValue();
                for(int a = 0; a < 3; a++) {
                    for (int b = 6; b < 9; b++) {
                        sudokuGrid[a][b].deleteCandidate(value);
                    }
                }
            }
        }
    }

    private void checkGrid8(){
        for(int i = 3; i < 6; i++){
            for(int j = 6; j < 9 ; j++){
                int value = sudokuGrid[i][j].getValue();
                for(int a = 3; a < 6; a++) {
                    for (int b = 6; b < 9; b++) {
                        sudokuGrid[a][b].deleteCandidate(value);
                    }
                }
            }
        }
    }

    private void checkGrid9(){
        for(int i = 6; i < 9; i++){
            for(int j = 6; j < 9 ; j++){
                int value = sudokuGrid[i][j].getValue();
                for(int a = 6; a < 9; a++) {
                    for (int b = 6; b < 9; b++) {
                        sudokuGrid[a][b].deleteCandidate(value);
                    }
                }
            }
        }
    }
}