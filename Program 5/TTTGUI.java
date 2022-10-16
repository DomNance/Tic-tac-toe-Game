import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * The function of this class is to create a GUI version of Tic-Tac-Toe given the TTTBoard class, 
 * and the TTTAI class. This GUI uses a Border Layout to connect both aspects of the GUI, the 
 * buttons and the label. Whenever one of the buttons are clicked, that button's text is given an
 * X mark, then immediately after, a O mark is placed on a random button. 
 *
 * The label is constantly saying that it's your turn. If you win, the label will say that you won,
 * if you lose, then the label will say that you lost, and if there's no winner and all spots on 
 * the board are filled, then the label says that it's a draw, and the GUI will prevent you from 
 * pressing any buttons until you exit the program. 
 *
 * @author Dominic Nance (3405)
 * @version 9 May 2019
 */
public class TTTGUI {
   public static void main(String[] args) {
      // Creates new frame
      JFrame frame = new JFrame();
      
      // Makes sure tab stops running when document is closed
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // Sets size
      frame.setSize(new Dimension(400, 400));
      
      // Sets title of window
      frame.setTitle("Tic Tac Toe GUI");
      
      // Sets the layout of the window
      frame.setLayout(new BorderLayout());
      
      // Creates an array to store the buttons
      JButton buttons[][] = new JButton[4][4];
      
      // Create the label
      JLabel label = new JLabel("");
      
      // Creates ActionListener object
      ActionListener action = new ButtonActionListener(buttons, label);            
      
      // Creates the rows and columns of buttons for the program
      JPanel gameBoard = new JPanel(new GridLayout(4, 4));
      for (int r = 0; r < 4; r++) {
         for (int c = 0; c < 4; c++) {
            buttons[r][c] = new JButton(" ");
            gameBoard.add(buttons[r][c]);
            buttons[r][c].addActionListener(action);
         }         
      } 
      
      // Adds all the buttons to the frame in the correct position
      frame.add(gameBoard, BorderLayout.CENTER);
      
      // Creates the label at the bottom of the window
      JPanel turn = new JPanel(new FlowLayout());      
      turn.add(label);
      frame.add(turn, BorderLayout.SOUTH);
      
      // Makes all of this visible
      frame.setVisible(true);            
   }
}