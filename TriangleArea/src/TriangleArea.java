import java.util.Scanner;
public class TriangleArea
{
    public static void main(String[] args)
    {
    	
      // Declaring all VARS used in this class.
      double x1, x2, x3, y1, y2, y3, d1, d2, d3, s, a;
      
      // Asking for user input, and scanning them into variables.
      System.out.println("Please enter coordinates in this form: x1 y1 x2 y2 x3 y3");
      Scanner keyboard = new Scanner(System.in);
      x1 = keyboard.nextDouble();
      y1 = keyboard.nextDouble();
      x2 = keyboard.nextDouble();
      y2 = keyboard.nextDouble();
      x3 = keyboard.nextDouble();
      y3 = keyboard.nextDouble();
      
      // Doing all the calculations.
      d1 = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
      d2 = Math.sqrt((x2-x3)*(x2-x3)+(y2-y3)*(y2-y3)); 
      d3 = Math.sqrt((x1-x3)*(x1-x3)+(y1-y3)*(y1-y3));
      
      s = (d1+d2+d3)/2;
      a = Math.sqrt(s*(s-d1)*(s-d2)*(s-d3));
                
      // Showing the area of the triangle.
      System.out.println("The area of the triangle is:");
      System.out.println(a);
     }
 }