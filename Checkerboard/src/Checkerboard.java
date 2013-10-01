// Daphne Lin


import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;

   public class Checkerboard extends Applet 
   {
           public void paint(Graphics canvas)
             {
                  int row;  
                  int column;   
                  int x;
                  int y;   

                  for (row = 0;  row < 8;  row++) 
                  {

                         for (column=0;  column<8;  column++) 
                         {
                                x = column * 40;
                                y = row * 40;
                                if ((row % 2) == (column % 2))
                                   canvas.setColor(new Color(255*(int) Math.random(),255*(int) Math.random(),255*(int) Math.random()));
                                else
                                   canvas.setColor(Color.);
                                   canvas.fillRect(x, y, 40, 40);
                         }
                  }
           }
   }