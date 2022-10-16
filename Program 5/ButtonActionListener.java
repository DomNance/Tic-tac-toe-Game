import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * The function of this class is to create the ActionListener for the TTTGUI program. This program
 * has a constructor which has two parameters: a double array of JButtons and a JLabel. The
 * constructor initializes the ButtonActionListener with a TTTBoard, the user's character, the
 * computer's character, the double array of buttons, the label (which also sets the text to
 * "Your turn"), a count that keeps track of how many spaces are filled, and a boolean called
 * pass that helps determine if the board is full. The only method that this program has is the
 * ActionPerformed method which performs the functions of the TTTBoard and TTTAI on a GUI board.
 *
 * @author Dominic Nance (3405)
 * @version 9 May 2019
 */
public class ButtonActionListener implements ActionListener {
   // Private members
   private TTTBoard board;
   private char user;
   private char computer;
   private JButton[][] buttons;
   private JLabel message;
   private int count;
   private boolean pass;
   
   /**
    * This constructor creates the ButtonActionListener and initializes all of the private members
    * in the class. It takes two parameters that connect to the TTTGUI, does all the data
    * manipulation in here, then displays it on the TTTGUI.
    *
    * @param btns - a 2d array of buttons that holds all of the buttons from the TTTGUI
    * @param lbl - a label that's connected to the TTTGUI
    */
   public ButtonActionListener(JButton[][] btns, JLabel lbl) {
      board = new TTTBoard(4);
      user = 'X';
      computer = 'O';
      buttons = btns;
      message = lbl;
      message.setText("Your turn");
      count = 0;
      pass = true;
   }
   
   /**
    * This method causes a button on the TTTGUI to show an X or O mark depending on whether it's
    * the computer's turn (O) or the user's turn (X). If there's a draw, the label says that 
    * there's a draw, if X wins, then the label says that the user wins, and if O wins, then it
    * says that the computer wins. After the winner is determined or if the board is full, then 
    * the program prevents the user from pressing any more buttons.
    *
    * @param event - an ActionEvent which is inputed from the GUI, helps determine pressed buttons
    */
   public void actionPerformed(ActionEvent event) {
      // Holds the button that was pressed
      JButton pressed = (JButton) event.getSource();     
      
      // If statement that prevents the user from pressing buttons after win
      if (board.winner() != 'X' && board.winner() != 'O' && pass) {
         // Puts X in button that's been clicked on  
         for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
               if (pressed == buttons[r][c]) {
                  pressed.setText("X");
                  board.set(r, c, 'X');
               }
            }
         }
                 
         // Checks for winner for user 
         if (board.winner() == 'X') { 
            message.setText("You won! End the game");           
         } else {
            if (board.size() != 0) {
               TTTAI.move(board, 'O');
               for (int r = 0; r < 4; r++) {
                  for (int c = 0; c < 4; c++) {
                     if (board.get(r,c) == 'O') {
                        buttons[r][c].setText("O");                     
                     }
                  }
               }
            }                       
         }
         
         // Checks winner for computer        
         if (board.winner() == 'O') {
            message.setText("Sorry, computer won! End the game");
         } 
          
         // Checks if there's a draw
         for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
               if (board.get(r,c) != ' ') {
                  count++;
               }
            }
         }
         if (count == 16) {
            message.setText("It's a draw!");
            pass = false;
         } else {
            pass = true;
            count = 0;
         }          
      }                    
   }
}