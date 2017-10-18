import javax.swing.*;

public class MyJButton extends JButton{

        private int col;        // col coordinate
        private int row;        // row coordinate
        private int value;      // int value
        private boolean originalPiece; //

        public MyJButton(String text){
            super(text);
        }

        public MyJButton(String text, int col, int row, int value, boolean originalValue){
            super(text);
            this.col = col;
            this.row = row;
            this.value = value;
            this.originalPiece = originalValue;
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

        public boolean getOriginalPiece(){ return originalPiece;}

        public void setCol(int col){        // setters
            this.col = col;
        }

        public void setRow(int row){
            this.row = row;
        }

        public void setValue(int value){
            this.value = value;
        }

        public void setOriginalPiece(boolean value) {this.originalPiece = value;}
}
