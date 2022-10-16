import java.util.Random;
import java.awt.Point;

/**
 * The purpose of this class is to make an artifical move on the TTT board. It's only function
 * is to move using one method referred to as 'move'. In order to perform the whole function 'move'
 * multiple private methods are called such as 'count', which count the number of blank spaces
 * on the board. 
 *
 * The program goes through multiple if statements to determine what move to make. If the board is 
 * full, an IllegalArgumentException is called. If there's an opportunity for the computer to make
 * a winning move, then that move is taken. If there's an opportunity for the opponent to win, then
 * the computer makes it's best effort to block the move.
 *
 * @author Dominic Nance (3405)
 * @version 28 March 2019
 */
public class TTTAI {
   private static Point coordinates = new Point();
   /**
    * The purpose of this method is to make a move given the current circumstances of the TTT board.
    * If there's any possibility of a winning move, or blocking the player's winning move, it'll
    * make that move.
    *
    * @param board - the TTT board.
    * @param who - the character that's used for each player. Either 'X' or 'O'.
    */
   public static void move(TTTBoard board, char who) {
      // Determines which character the opponent is
      char opponent;
      if (who == 'X') {
         opponent = 'O';
      } else {
         opponent = 'X';
      }      
            
      // Random numbers for coordinates if no special move is done
      int r1 = 0;
      int r2 = 0;
      
      // Declare and intialize Random
      Random rand = new Random();
      
      // Helps the program to keep using random numbers until a pair is valid.
      boolean flag = true;  
      
      // If there's already a winner, throw an exception
      if (board.winner() != ' ') {
         throw new IllegalArgumentException("Error. There's already a winner.");    
  
      // If board is bad, throw an exception
      } else if (count(board) == (board.size() * board.size())) {
         throw new IllegalArgumentException("Error: Board is already full.");
         
      // If there's an opportunity to win, take the winning move
      } else if (check(board, who) && board.get((int) coordinates.getX(), (int) coordinates.getY()) == ' ') {
         board.set((int) coordinates.getX(), (int) coordinates.getY(), who);
      
      // If there's an opportunity for opponent to win, block their move
      } else if (check(board, opponent) && board.get((int) coordinates.getX(), (int) coordinates.getY()) == ' ') {
         board.set((int) coordinates.getX(), (int) coordinates.getY(), who);
      
      // Do a random move if there's no other special options
      } else {
         while (flag) {
            r1 = rand.nextInt(board.size());
            r2 = rand.nextInt(board.size());
            flag = board.get(r1, r2) != ' ';
         }
         board.set(r1, r2, who);
      }
   }
   
   /*
   This method counts how many positions of the board are taken by excluding any ' ' on the board.
   */
   private static int count(TTTBoard board) {
      int count = 0;
      
      // Counts the # of ' ' spaces in the TTT board
      for (int r = 0; r < board.size(); r++) {
         for (int c = 0; c < board.size(); c++) {
            if (board.get(r,c) != ' ') {
               count++;
            }
         }
      }
      
      return count;
   }
   
   /*
   This method checks the board to see different options for moves. Will work for checking if
   there's any winning moves, or blocking moves, or if there's no defensive or offensive moves. 
   */
   private static boolean check(TTTBoard board, char who) {
      // Counts how many moves have been made
      int count = 0;
      
      // Refers to checkRowAndColumn method to determine possible moves/blocks 
      if (checkRowAndColumn(board, who, count, "Row") || checkRowAndColumn(board, who, count, "Column")) {
         return true;
      }

      // Refers to checkDiagonals method to determine possible moves/blocks 
      if (checkDiagonals(board, who, count, "Top") || checkDiagonals(board, who, count, "Bottom")) {
         return true;
      }
      
      // If no special moves are found, return false
      return false;
   }
   
   /*
   This method helps condense repeated code for the check method. It takes the code from checking
   rows and columns for winning/blocking moves and makes one method out of it. 
   */    
   private static boolean checkRowAndColumn(TTTBoard board, char who, int count, String dtr) { 
      // Checks the rows to see if there's any winning moves
      for (int r = 0; r < board.size(); r++) {
         for (int c = 0; c < board.size(); c++) {
            // Checks rows if dtr.equals("Row")
            if (dtr.equals("Row")) {
               if (board.get(r,c) == who) {
                  count++;
               }
            // Checks columns if dtr.equals(*anything other than "Row"*)
            } else {
               if (board.get(c,r) == who) {
                  count++;
               }
            }        
         }
         if (count == board.size() - 1) {
            for (int c = 0; c < board.size(); c++) {
               // Checks rows
               if (dtr.equals("Row")) {
                  if (board.get(r,c) == ' ') {
                     coordinates.setLocation(r,c);
                  }
               // Checks columns
               } else {
                  if (board.get(c,r) == ' ') {
                     coordinates.setLocation(c,r);
                  }
               }                
            }
            return true;
         } else {
            count = 0;
         }
      }
      
      // If rows and columns have no potential solutions, then return false
      return false;
   }
   
   /*
   This method helps condense repeated code for the check method. It takes the code from checking
   diagonal both scanning from top and from bottom and shortens it to this method. 
   */ 
   private static boolean checkDiagonals(TTTBoard board, char who, int count, String dtr) {
      for (int d = 0; d < board.size(); d++) {
         // Checks the diagonal from top to bottom
         if (dtr.equals("Top")) {
            if (board.get(d,d) == who) {
               count++;
            }
         
         // Checks the diagonal from bottom to top
         } else {
            if (board.get((board.size() - 1) - d, d) == who) {
               count++;
            }
         }
      }
      
      if (count == board.size() - 1) {
         for (int d = 0; d < board.size(); d++) {
            if (dtr.equals("Top")) {
               if (board.get(d,d) == ' ') {
                  coordinates.setLocation(d,d);
               }
            } else {
               if (board.get((board.size() - 1) - d, d) == ' ') {
                  coordinates.setLocation((board.size() - 1) - d, d);
               }
            }
         }
         return true;
      } else {
         count = 0;
      }
      
      // If no other special move is found, then return false
      return false;
   }
}