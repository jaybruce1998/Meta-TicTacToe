import javax.swing.*;
import java.awt.event.*;
public class metaTicTacToe
{
   static int[][] board=new int[9][9];
   static int[][] won=new int[3][3];
   static String typed, name1, name2, message;
   static int option, bRow, bCol, row, col;
   static boolean valid, meta;
   static JFrame frame = new JFrame();
   private static mouseListener ml=new mouseListener();
   static MetaPanel mp=new MetaPanel(board);
   public metaTicTacToe()
   {
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.addMouseListener(ml);
      frame.add(mp);
      frame.setSize(705, 745);
      frame.setFocusable(true);
   }
   public static void fillBoard()
   {
      for(int r=0; r<board.length; r++)
         for(int c=0; c<board[0].length; c++)
         {
            board[r][c]=2;
            won[r/3][c/3]=2;     
         }
   }
   public static void showBoard()
   {
      if(meta)
      {
         System.out.println("   012 012 012");
         System.out.println("  -------------");
         for(int r=0; r<board.length; r++)
         {
            System.out.print(r%3+" |");
            for(int c=0; c<board[0].length; c++)
            {
               if(won[r/3][c/3]==2)
                  System.out.print(board[r][c]);
               else
                  System.out.print(won[r/3][c/3]);
               if(c%3==2)
                  System.out.print("|");
            }
            if(r%3==1)
               System.out.print(" "+r/3);
            System.out.println();
            if(r%3==2)
               System.out.println("  -------------");
         }
         System.out.println("    0   1   2");
      }
      else
      {
         System.out.println("    0   1   2");
         System.out.println("  -------------");
         for(int r=0; r<3; r++)
         {
            System.out.print(r+" | ");
            for(int c=0; c<3; c++)
               System.out.print(board[r][c]+" | ");
            System.out.println();
            System.out.println("  -------------");
         }
      }
   }
   public static boolean playerWon(int player)
   {
      if(meta)
      {
         for(int r=0; r<won.length; r++)
            for(int c=0; c<won[0].length; c++)
               if(boardWon(player, r*3, c*3))
                  won[r][c]=player;
         if(won[0][0]==player)
         {
            if(won[0][1]==player&&won[0][2]==player)
               return true;
            if(won[1][0]==player&&won[2][0]==player)
               return true;
            if(won[1][1]==player&&won[2][2]==player)
               return true;
         }
         if(won[1][1]==player)
         {
            if(won[0][1]==player&&won[2][1]==player)
               return true;
            if(won[0][1]==player&&won[2][1]==player)
               return true;
            if(won[0][2]==player&&won[2][0]==player)
               return true;
         }
         if(won[2][2]==player)
         {
            if(won[2][0]==player&&won[2][1]==player)
               return true;
            if(won[0][2]==player&&won[1][2]==player)
               return true;
         }
         return false;
      }
      else
         return boardWon(player, 0, 0);
   }
   public static boolean boardWon(int player, int firstR, int firstC)
   {
      if(board[firstR][firstC]==player)
      {
         if(board[firstR][firstC+1]==player&&board[firstR][firstC+2]==player)
            return true;
         if(board[firstR+1][firstC]==player&&board[firstR+2][firstC]==player)
            return true;
         if(board[firstR+1][firstC+1]==player&&board[firstR+2][firstC+2]==player)
            return true;
      }
      if(board[firstR+1][firstC+1]==player)
      {
         if(board[firstR+1][firstC]==player&&board[firstR+1][firstC+2]==player)
            return true;
         if(board[firstR][firstC+1]==player&&board[firstR+2][firstC+1]==player)
            return true;
         if(board[firstR][firstC+2]==player&&board[firstR+2][firstC]==player)
            return true;
      }
      if(board[firstR+2][firstC+2]==player)
      {
         if(board[firstR+2][firstC]==player&&board[firstR+2][firstC+1]==player)
            return true;
         if(board[firstR][firstC+2]==player&&board[firstR+1][firstC+2]==player)
            return true;
      }
      return false;
   }
   public static boolean boardIsFull(int startR, int startC)
   {
      for(int r=startR; r<startR+3; r++)
         for(int c=startC; c<startC+3; c++)
            if(board[r][c]==2)
               return false;
      return true;
   }
   public static boolean boardIsFull()
   {
      if(meta)
         return boardsAreFull();
      return boardIsFull(0, 0);
   }
   public static boolean boardsAreFull()
   {
      for(int startR=0; startR<board.length; startR+=3)
         for(int startC=0; startC<board[0].length; startC+=3)
            if(!(boardIsFull(startR, startC)||won[startR/3][startC/3]!=2))
               return false;
      return true;
   }
   public static int couldWin(int player, int startR, int startC)
   {
      for(int r=startR; r<startR+3; r++)
      {
         if(board[r][startC]==player&&board[r][startC+1]==player&&board[r][startC+2]==2)
            return r*10+startC+2;
         if(board[r][startC]==player&&board[r][startC+1]==2&&board[r][startC+2]==player)
            return r*10+startC+1;
         if(board[r][startC]==2&&board[r][startC+1]==player&&board[r][startC+2]==player)
            return r*10+startC;
      }
      for(int c=startC; c<startC+3; c++)
      {
         if(board[startR][c]==player&&board[startR+1][c]==player&&board[startR+2][c]==2)
            return (startR+2)*10+c;
         if(board[startR][c]==player&&board[startR+1][c]==2&&board[startR+2][c]==player)
            return (startR+1)*10+c;
         if(board[startR][c]==2&&board[startR+1][c]==player&&board[startR+2][c]==player)
            return startR*10+c;
      }
      if(board[startR][startC]==player&&board[startR+1][startC+1]==player&&board[startR+2][startC+2]==2)
         return (startR+2)*10+startC+2;
      if(board[startR][startC]==player&&board[startR+1][startC+1]==2&&board[startR+2][startC+2]==player)
         return (startR+1)*10+startC+1;
      if(board[startR][startC]==2&&board[startR+1][startC+1]==player&&board[startR+2][startC+2]==player)
         return startR*10+startC;
      if(board[startR][startC+2]==player&&board[startR+1][startC+1]==player&&board[startR+2][startC]==2)
         return (startR+2)*10+startC;
      if(board[startR][startC+2]==player&&board[startR+1][startC+1]==2&&board[startR+2][startC]==player)
         return (startR+1)*10+startC+1;
      if(board[startR][startC+2]==2&&board[startR+1][startC+1]==player&&board[startR+2][startC]==player)
         return startR*10+startC+2;
      return -1;
   }
   public static void doAITurn()
   {
      if(meta)
         if(bRow>2||won[bRow][bCol]!=2)
         {
            for(int startR=0; bRow>2&&startR<board.length; startR+=3)
               for(int startC=0; bRow>2&&startC<board.length; startC+=3)
                  if(couldWin(1, startR, startC)>=0)
                  {
                     bRow=startR/3;
                     bCol=startC/3;
                  }
            for(int startR=0; bRow>2&&startR<board.length; startR+=3)
               for(int startC=0; bRow>2&&startC<board.length; startC+=3)
                  if(couldWin(0, startR, startC)>=0)
                  {
                     bRow=startR/3;
                     bCol=startC/3;
                  }
            if(bRow>2||won[bRow][bCol]!=2)
            {
               bRow=(int)(Math.random()*3);
               bCol=(int)(Math.random()*3);
               while(boardIsFull(bRow*3, bCol*3)||won[bRow][bCol]!=2)
                  if(bRow<2)
                     bRow++;
                  else
                  {
                     bRow=0;
                     if(bCol<2)
                        bCol++;
                     else
                        bCol=0;
                  }
            }
         }
      if(couldWin(1, bRow*3, bCol*3)>=0)
      {
         row=couldWin(1, bRow*3, bCol*3)/10;
         col=couldWin(1, bRow*3, bCol*3)%10;
      }
      else
      {
         if(couldWin(0, bRow*3, bCol*3)>=0)
         {
            row=couldWin(0, bRow*3, bCol*3)/10;
            col=couldWin(0, bRow*3, bCol*3)%10;
         }
         else
         {
            row=(int)(Math.random()*3)+bRow*3;
            col=(int)(Math.random()*3)+bCol*3;
         }
         while(board[row][col]!=2)
         {
            if(row<bRow*3+2)
               row++;
            else
            {
               row=bRow*3;
               if(col<bCol*3+2)
                  col++;
               else
                  col=bCol*3;
            }
         }
      }
      board[row][col]=1;
      if(meta)
      {
         bRow=row%3;
         bCol=col%3;
      }
   }
   public static void doTurn(int player)
   {
      row=9;
      col=9;
      valid=false;
      if(player<1)
         message="It is "+name1+"'s turn.";
      else
         message="It is "+name2+"'s turn.";
      if(bRow>2)
         message+=" You can play wherever you want!";
      if(bRow<3&&won[bRow][bCol]<2)
         bRow=3;
      frame.repaint();
      while(!valid)
      {
         while(row<0||row>8||col<0||col>8||board[row][col]!=2)
            mp.repaint();
         if(bRow<3&&(row/3!=bRow||col/3!=bCol))
            row=9;
         else
            valid=true;
      }
      board[row][col]=player;
      if(meta)
      {
         bRow=row%3;
         bCol=col%3;
         if(boardIsFull(bRow, bCol))
            bRow=3;
      }
   }
   public static boolean meta()
   {
      return meta;
   }
   public static String message()
   {
      return message;
   }
   public static void setRow(int r)
   {
      row=r;
   }
   public static void setCol(int c)
   {
      col=c;
   }
   public static void playGame(boolean twoPeople)
   {
      bRow=0;
      bCol=0;
      fillBoard();
      if(meta)
      {
         bRow=3;
         bCol=3;
      }
      name1 = JOptionPane.showInputDialog("What is player one's name?");
      if(twoPeople)
         name2 = JOptionPane.showInputDialog("What is player two's name?");
      else
         name2="The AI";
      while(!(playerWon(0)||playerWon(1)||boardIsFull()))
      {
         doTurn(0);
         if(!(playerWon(0)||boardIsFull()))
            if(twoPeople)
               doTurn(1);  
            else
               doAITurn();
      }
      mp.repaint();
      frame.repaint();
      if(playerWon(0))
         message=name1+" won!";
      else
         if(playerWon(1))
            message=name2+" won!";
         else
            message="It's a tie!";
   }
   public static void main(String[] args)
   {
      new metaTicTacToe();
      while(option!=5)
      {
         option=0;
         meta=true;
         typed=null;
         typed = JOptionPane.showInputDialog("What would you like to do?\n1) Play Meta TicTacToe with a friend.\n2) Play Meta TicTacToe with the AI.\n3) Play TicTacToe with a friend.\n4) Play TicTacToe with the AI.\n5) Exit.");
         if(typed==null||typed.equals(""))  
            System.exit(0);
         fillBoard();
         if(typed.contains("1")||typed.contains("2")||typed.contains("3")||typed.contains("4")||typed.contains("5"))
            option=Integer.parseInt(typed);
         message="";
         frame.setVisible(true);
         if(option==1)
            playGame(true);
         if(option==2)
            playGame(false);
         if(option==3)
         {
            meta=false;
            playGame(true);
         }
         if(option==4)
         {
            meta=false;
            playGame(false);
         }
      }
      System.exit(0);
   }
}