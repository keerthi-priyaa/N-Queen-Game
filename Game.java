package javaapplication10;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JOptionPane;
/**
 *
 * @author Keerthi
 */
public class Game extends JFrame{
    
    private JPanel buttonsPane;
    private JPanel topPane;
    Scanner obj = new Scanner(System.in);
    int N = obj.nextInt();
    int rf;
    int cf;
    JButton[][] sqs = new JButton[N][N];
    int[][] chessBoard=new int[N][N];
    
    public void solveGame(){
      
    JFrame f = new JFrame("Game");
    f.setSize(600,600);
    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    buttonsPane = new JPanel(){
        public Dimension getPrefferedSize(){
            return new Dimension(500,500);
        }
    };
        
    JButton newGame = new JButton("New Game");
    JButton exit = new JButton("Exit");
    JButton sol = new JButton("Solution");
    newGame.setSize(100,200);
    exit.setSize(100,200);
    buttonsPane.setLayout(new GridLayout(N,N));
    buttonsPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    ButtonHandler buttonHandler = new ButtonHandler();
    Solution solution = new Solution();
    newGame.addActionListener(e->{this.setEnabled(false);solveGame();});
    exit.addActionListener(e->{f.dispose();});
    sol.addActionListener(solution);
     for(int i=0;i<N;i++)
    {
        for(int j=0;j<N;j++)
        {
            sqs[i][j] =new JButton();
            chessBoard[i][j] = 0;
                    buttonsPane.add(sqs[i][j]);
                    sqs[i][j].addActionListener(buttonHandler);
        }
    }
     
     topPane = new JPanel();
     topPane.setLayout(new GridBagLayout());
     GridBagConstraints gbc = new GridBagConstraints();
     gbc.gridy =2;
     gbc.ipadx = 3;
     gbc.ipady = 8;
     gbc.insets = new Insets(5,10,5,10);
     topPane.add(newGame,gbc);
     topPane.add(exit,gbc);
     topPane.add(sol,gbc);
     topPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
     f.add(topPane,BorderLayout.NORTH);
     f.getContentPane().add(buttonsPane,BorderLayout.CENTER);
     f.setResizable(true);
     f.setLocationRelativeTo(null);
     f.setVisible(true);
    }//end of method solveGame

    class ButtonHandler implements ActionListener{
        int v=0;
        int row;
        int col;
    
    public void actionPerformed(ActionEvent e){
         JButton b=(JButton)e.getSource();   
         if(v!=N){
            Rectangle r=b.getBounds();
            Point p=b.getLocation();
             row=p.y/r.height;
             col=p.x/r.width;
               if(v==0){
                   rf=row;
                   cf=col;   
               }
             for(int i=0;i<N;i++){
                    for(int j=0;j<N;j++){
                    if(row==i){
                         sqs[row][j].setBackground(Color.RED);
                          }  
                    if(col==j){
                         sqs[i][col].setBackground(Color.RED);
                          }
                    if(row-col==i-j){
                        sqs[i][j].setBackground(Color.red);
                    }
                    if(row+col==i+j){
                        sqs[i][j].setBackground(Color.red);
                    }
                    
                    b.setBackground(Color.green);
                    b.setOpaque(true);
                }
                }
        v++;
        }
         Color queenbc = Color.green;
         Color nqueen = Color.red;
         int k = 0;
         boolean flag ;
         for(int i=0;i<N; i++){
             for(int j=0;j<N;j++){
              Color bc =  sqs[i][j].getBackground();
              if(bc==queenbc){
                  k++;
              if(k==N){
                      System.out.print("\nQueen at position ("+row+","+col+")");
                      JOptionPane.showMessageDialog(null,"Congratulations! you won");
                         }
                     }
                 }
           }
     
        System.out.print("\nQueen"+k+" at position ("+row+","+col+")");
   
      }
       }//end of ButtonHandler class
    
         class Solution implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                hasSolution(rf,cf);
                     }
            }//end of class Solution
         
 boolean hasSolution(int ctr, int colQueen){
 
        if(colQueen == N){
            boolean flag = true;
            displayBoard();
            return true;
        }
        int i,j;
        for(i=ctr; i<N; i++){
            if(isValidPlace(i,colQueen)){
                chessBoard[i][colQueen] = 1;
                if(hasSolution(0,colQueen+1))
                    return true;
                chessBoard[i][colQueen] = 0;
            } 
        }
       return false;
    }//end of method hasSolution
  void displayBoard(){
        int i, j;
        System.out.print("\n");
        System.out.print("\n\033[34mHere is the Solution for "+N+" \033[34mQueens\n\n");
        for(i=0; i<N; i++){
            for(j=0; j<N; j++){
                if(chessBoard[i][j] == 1)
                    System.out.print(" Q ");
                else
                    System.out.print(" * ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\033[32;1;2mTry Again :)\n");
    }//end of method displayBoard
     boolean isValidPlace(int row, int col){
        int i,j;
         //Checking horizontally
        for(i=col; i>=0; i--){
            if(chessBoard[row][i] == 1)
                return false;
        }
        //checking left diagonal
        for(i=row, j=col; i>=0 && j>=0; i--,j--){
            if(chessBoard[i][j] == 1)
                return false;
        } 
        //checking right diagonal
        for(i=row, j=col; i<N && j>=0; i++,j--){
            if(chessBoard[i][j] == 1)
                return false;
        } 
        return true;
    }//end of method isValidPlace
     
    public static void main(String[] args) {
    int i;
        for(i=0;i<50;i++)
            System.out.print("\033[32;1;2m**.");
        System.out.print("\n\t\t\t\t\t\t\033[34mWeLcOmE To N qUeEn gAmE\n");
         for(i=0;i<50;i++)
            System.out.print("\033[32;1;2m**.");
         System.out.print("\n\n");
         System.out.print("\033[32;1;2mCoNsTrAiNt :) No Two Queens Share same\033[31;1m ROW, COLOUMN, DIAGONAL \033[32;1;2m:)\n");
          System.out.print("\033[34mBegin Your Game!!!\n");
         System.out.print("\033[31;1mPlease Enter Valid Number of Queens - 4 or more :)\n");
        Game g = new Game();
        g.solveGame();
    }
}//end of Game class
    
  