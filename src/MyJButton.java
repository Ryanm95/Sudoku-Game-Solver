import javax.swing.*;

public class MyJButton extends JButton{

        private int col;
        private int row;
        private int value;

        public MyJButton(String text){
            super(text);
        }

        public MyJButton(String text, int col, int row, int value){
            super(text);
            this.col = col;
            this.row = row;
            this.value = value;
        }

        public int getRow(){        // getters
            return row;
        }

        public int getCol(){
            return col;
        }

        public int getValue(){
            return value;
        }

        public void setCol(int col){        // setters
            this.col = col;
        }

        public void setRow(int row){
            this.row = row;
        }

        public void setValue(){
            this.value = value;
        }
}
