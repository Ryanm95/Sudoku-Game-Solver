import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;


public class GUI extends JFrame implements ActionListener{

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

        addMenu();  //Adds the menu bar to the window
        setSize( 800, 800 );
        setVisible( true );

        sudokuGrid = new MyJButton[9][9];       // grid of where numbers will be
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // Creates menu bar and attach it to GUI window
    // and adds all buttons like exit, add puzzle,
    // and load a puzzle from a file
    private void addMenu() {
        //set up File menu and its menu items
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');

        //set up Load Puzzle submenu item under File
        JMenu loadItem = new JMenu("Load Puzzle");
        loadItem.setMnemonic('L');
        fileMenu.add(loadItem);
        JMenuItem puzzleOne = new JMenuItem("Easy Puzzle 1");
        loadItem.add(puzzleOne);
        JMenuItem puzzleTwo = new JMenuItem("Easy Puzzle 2");
        loadItem.add(puzzleTwo);

        puzzleOne.addActionListener(
                new ActionListener() {  // anonymous inner class
                    // Loads new puzzle into program
                    public void actionPerformed(ActionEvent event) {
                        String fileName = "proj1data1.txt";
                        readfile(fileName);
                    }
                }  // end anonymous inner class
        ); // end call to addActionListener

        puzzleTwo.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String fileName = "proj1dAta2.txt";
                        readfile(fileName);
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
                        JOptionPane.showMessageDialog(GUI.this,
                                "ADD rules",
                                "How to Play", JOptionPane.PLAIN_MESSAGE);
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
                        JOptionPane.showMessageDialog(GUI.this,
                                "add how to use interface ",
                                "How to Use Interface", JOptionPane.PLAIN_MESSAGE);
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
        JMenuItem checkItem = new JMenuItem("Check on Fill");
        checkItem.setMnemonic('c');
        hintMenu.add(checkItem);
        checkItem.addActionListener(
                new ActionListener(){  // anonymous inner class
                    public void actionPerformed(ActionEvent event){
                        JOptionPane.showMessageDialog(GUI.this,
                                "add toggle",
                                "Check on Fill", JOptionPane.PLAIN_MESSAGE);
                    }
                }  // end anonymous inner class
        ); // end call to addActionListener\

        // Makes bar and adds all buttons to it
        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        bar.add(fileMenu);
        bar.add(helpMenu);
        bar.add(hintMenu);
    }

    private void readfile(String filename){
        String line = null;

        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
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
}
