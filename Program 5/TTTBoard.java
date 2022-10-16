/**
 * The function of this class is to model a Tic-Tac-Toe board. Each position of the TTT board is 
 * referred as an ordered pair. The upper left position is (0,0) and r increases downard and c 
 * increases to the right.
 *
 * @author Dominic Nance (3405)
 * @version 10 March 2018
 */
public class TTTBoard {
   private char[][] board;
   private int size;
   public static final int DEFAULT_SIZE = 3;
   
   /**
    * This constructor creates a TTT board given a parameter and fills in the 2D array with empty
    * character spaces. It takes a parameter indicating the size of the TTT board. If the size is 
    * less than one, then the constructor throws an IllegalArgumentException.
    *
    * @param sizeOfBoard - this indicates the size of the board.
    * @throws IllegalArgumentException - when the size the user gives is less than 1.
    */
   public TTTBoard(int sizeOfBoard) {
      if (sizeOfBoard < 1) {
         throw new IllegalArgumentException("Error: size can't be less than one.");
      } else {
         size = sizeOfBoard;
   
         // Initializes board
         board = new char[size][size];
         
         for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
               board[r][c] = ' ';
            }
         }
      }
   }
   
   /**
    * This constructor creates a default TTT board with the size of 3 and fills in the 2D array
    * with empty character spaces.
    */
   public TTTBoard() {
      size = DEFAULT_SIZE;
      
      // Initializes board
      board = new char[size][size];
      
      for (int r = 0; r < size; r++) {
         for (int c = 0; c < size; c++) {
            board[r][c] = ' ';
         }
      }
   }
   
   /**
    * This method returns the character that is at the position given r and c. If no character is
    * at that position, the method returns ' '.
    *
    * @param r - the row where the point is at.
    * @param c - the column where the point is at.
    * @return either returns 'x', 'o', or ' ' (if either 'x' or 'o' is not found).
    */
   public char get(int r, int c) {
      if (r > size - 1 || c > size - 1) {
         throw new IndexOutOfBoundsException("Error: coordinates are out of the board size.");
      } else {
         return board[r][c];
      }
   }
   
   /**
    * This method sets a position, using r and c, with a character. If the given coordinates (r and c)
    * are out of bounds, then an IndexOutOfBoundsException is thrown.
    *
    * @param row - the row where the point is at.
    * @param col - the column where the point is at.
    * @param character - the character that's placed given the point.
    * @throws IndexOutOfBoundsException - when the input is out of the bounds of thee TTT board.
    */
   public void set(int row, int col, char character) {
      if (row > size - 1 || col > size - 1) {
         throw new IndexOutOfBoundsException("Error: coordinates are out of the board size.");
      } else {
         board[row][col] = character;
      }
   }
   
   /**
    * This method returns the size of the dimensions of the TTT board.
    *
    * @return the size of the TTT board.
    */
   public int size() {
      return size;
   }
   
   /**
    * This method returns a character (or the winner) if the character has the dimension of the TTT
    * board in a row. Whether it's horizontally, vertically, or diagonally.
    *
    * @return the character that wins.
    */
   public char winner() {
      boolean determine = true;
      char fill = ' ';
      
      // Check the rows
      for (int i = 0; i <= 1; i++) {
         if (i == 0) {
            fill = 'X';
         } else if (i == 1) {
            fill = 'O';
         } 
         for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
               if (board[r][c] == fill) {
                  determine = determine && true;
               } else {
                  determine = false; 
               }
            }
            if (determine) {
               return fill;
            }
            determine = true;
         }
      }
      
      // Check the columns
      for (int i = 0; i <= 1; i++) {
         if (i == 0) {
            fill = 'X';
         } else if (i == 1) {
            fill = 'O';
         }
         for (int c = 0; c < size; c++) {
            for (int r = 0; r < size; r++) {
               if (board[r][c] == fill) {
                  determine = determine && true;
               } else {
                  determine = false;
               }
            }
            
            if (determine) {
               return fill;
            } 
            determine = true; 
         }
      }
      
      // Check diagonal (top to bottom)
      for (int i = 0; i <= 1; i++) {
         if (i == 0) {
            fill = 'X';
         } else if (i == 1) {
            fill = 'O';
         }
                  
         for (int rc = 0; rc < size; rc++) {
            if (board[rc][rc] == fill) {
               determine = determine && true;
            } else {
               determine = false;
            }         
         }
         
         if (determine) {
            return fill;
         }
         determine = true;
      }
      
      // Is the reverse going down to up
      int countReverse = 0;
      
      // Check diagonal (bottom to top)
      for (int i = 0; i <= 1; i++) {
         if (i == 0) {
            fill = 'X';
         } else if (i == 1) {
            fill = 'O';
         }
         
         for (int rc = size - 1; rc >= 0; rc--) {
            if (board[rc][countReverse] == fill) {
               determine = determine && true;
            } else {
               determine = false;
            }
            countReverse++;             
         }
         if (determine) {
            return fill;
         }
         determine = true;
         countReverse = 0;
      }
      
      return ' ';
   }
   
   /**
    * This method returns a single String representation of the TTT board.
    *
    * @return String representation of the TTT board.
    */
   public String toString() {
      String s = "";
      int row = 0;
      int col = 0;
      for (int r = 0; r < 2 * size - 1; r++) {
         for (int c = 0; c < 2 * size - 1; c++) {
            if (c % 2 == 0 && r % 2 == 0) {
               s = s + board[row][col];
               if (col != size - 1) {
                  col++;
               } else if (col == size -1) {
                  col = 0;
                  row++;
               }
               // s = s + board[r % size][c % size];                                   
            } else if (c % 2 != 0 && r % 2 == 0) {
               s = s + "|";
            } else if (c % 2 == 0 && r % 2 != 0) {
               s = s + "-";
            } else if (c % 2 != 0 && r % 2 != 0) {
               s = s + "+";
            }
         }
         s = s + "\n";
      }
      return s;
   }   
}