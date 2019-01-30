   import java.awt.event.*;
   import java.awt.*;
   import javax.swing.*;
   public class mouseListener extends MouseAdapter
   {
      public mouseListener()
      {
      
      }
      public void mousePressed(MouseEvent e)
      {
         int div;
         div=225;
         if(metaTicTacToe.meta())
            div=75;
         metaTicTacToe.setRow((e.getY()-20)/div);
         metaTicTacToe.setCol(e.getX()/div);
      }
   }