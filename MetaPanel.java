import javax.swing.*;
import java.awt.*;
public class MetaPanel extends JPanel
{
   ImageIcon[] icons;
   String location;
   int[][] board;
   public MetaPanel(int[][] b)
   {
      super();
      location=System.getProperty("user.dir")+"/";
      board=b;
      icons=new ImageIcon[3];
      for(int i=0; i<icons.length; i++)
         icons[i] = new ImageIcon(location+i+".png");
   }
   public void paintComponent(Graphics g)
   {
      int rowP, colP;
      rowP=2;
      colP=3;
      if(!metaTicTacToe.meta())
         for(int r=0; r<3; r++)
            for(int c=0; c<3; c++)
               g.drawImage(icons[board[c][r]].getImage(), r*225, c*225, 225, 225, null);
      else
         for(int r=0; r<9; r++)
         {
            for(int c=0; c<9; c++)
            {
               if(c%3==0)
                  colP+=5;
               g.drawImage(icons[board[c][r]].getImage(), r*75+rowP, c*75+colP, 75, 75, null);
            }
            colP=3;  
            if(r%3==2)
               rowP+=5;
         }
      g.drawString(metaTicTacToe.message(), 3, 705);
   }
}