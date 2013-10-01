/*
 * Remember, these lines just give us access to all of the
 * graphics functions, so we can take advantage of the
 * classes that have been defined by the good folks at Sun.
 */
import java.awt.*;
import java.applet.*;
import java.awt.event.*;

/*
 * This is the class that defines the Sketching applet
 */
public class Sketching extends Applet implements MouseListener {
	/*
	 * When the user release the mouse, we'll make changes to these and then
	 * invoke repaint to install them
	 * 
	 * This is a technique known as "double-buffering". It will make this
	 * assignment easier to implement than drawign directly onto the active
	 * Image. And, it is also better, because the active Image will change,
	 * without the redraw being visible.
	 */
	private Graphics nextGraphics;
	private Image nextImage;

	/*
	 * Dimensions of the applet, itself These come from the HTML page's APPLET
	 * tag
	 */
	Dimension appletDim;

	/*
	 * Start and finish control points for graphics objects, such as line, oval,
	 * rectangle, etc.
	 */
	private Point start, finish;

	/*
	 * The GUI elements in order to implement user interfaces
	 */
	private CheckboxGroup shapes, colors;
	private Checkbox line, oval, rect, roundRect;
	private Checkbox black, blue, green, red, yellow;

	/*
	 * This method is called when the applet is initialized
	 */
	public void init() {
		appletDim = getSize(); // size of the applet, itself
		setLayout(null); // set applet layout

		/*
		 * Set up the layout of the applet for the graphical user interface.
		 */
		shapes = new CheckboxGroup();
		colors = new CheckboxGroup();

		line = new Checkbox("Line", shapes, true);
		oval = new Checkbox("Oval", shapes, false);
		rect = new Checkbox("Rectangle", shapes, false);
		roundRect = new Checkbox("Round Rectangle", shapes, false);

		black = new Checkbox("Black", colors, true);
		blue = new Checkbox("Blue", colors, false);
		green = new Checkbox("Green", colors, false);
		red = new Checkbox("Red", colors, false);
		yellow = new Checkbox("Yellow", colors, false);

		line.setBounds(0, 10, 150, 30);
		oval.setBounds(0, 40, 150, 30);
		rect.setBounds(0, 70, 150, 30);
		roundRect.setBounds(0, 100, 150, 30);

		black.setBounds(480, 10, 50, 30);
		blue.setBounds(480, 40, 50, 30);
		green.setBounds(480, 70, 50, 30);
		red.setBounds(480, 100, 50, 30);
		yellow.setBounds(480, 130, 50, 30);

		add(line);
		add(oval);
		add(rect);
		add(roundRect);

		add(black);
		add(blue);
		add(green);
		add(red);
		add(yellow);

		/*
		 * Create the buffer that we'll use to store out changes to the screen,
		 * before we actually paint them onto the screen.
		 * 
		 * This involves creating a new Image, and then extracting the Graphics
		 * object, the actual screen-map, from it.
		 */
		nextImage = createImage(appletDim.width, appletDim.height);
		nextGraphics = nextImage.getGraphics();

		/*
		 * Add code here to set the foreground color to white, and then to draw
		 * a box the size of the applet. Remember, we want to draw to
		 * "nextGraphics". paint() is automatically called after init(), so we
		 * don't need to call repaint()
		 */
		nextGraphics.setColor(Color.white);
		nextGraphics.fillRect(0, 0, appletDim.width, appletDim.height);

		/*
		 * Then, change the color back to something different, maybe black, so
		 * that what is drawn on the screen will be visible.
		 */
		nextGraphics.setColor(Color.black);

		/*
		 * Instruct the applet to watch for mouse events
		 */
		addMouseListener(this);
	}

	/*
	 * This is used, sometimes automatically, and sometimes explicitly by the
	 * program, to redraw the screen In this case, we prepare "nextImage" in
	 * advance. When we want to redraw the screen, for example after a mouse
	 * event that adds an object, we adjust nextImage, and then call repaint(),
	 * which in turn calls this method.
	 */
	public void paint(Graphics g) {
		g.drawImage(nextImage, 0, 0, this);
	}

	/*
	 * If the mouse is pressed, it is a starting point. So, we want to mark it
	 * with a dot and store it for later, so we can draw the object
	 */
	public void mousePressed(MouseEvent me) {
		// Save the current position of the mouse into
		start = new Point(me.getX(), me.getY());

		/*
		 * You might want to draw a dot so the user can see the original point
		 * while moving the mouse to the second point
		 * 
		 * If you want to do this, you can do it by drawing a line 1 pixel long.
		 * You want to draw onto "nextGraphics" and then call repaint() to
		 * install "nextgraphics" on the screen.
		 * 
		 * Otherwise, you can just save the point and draw the object when the
		 * user releases the mouse at the second point.
		 */
	}

	/*
	 * When the mouse is released it is time to draw the object. So, we find the
	 * point, set the color, and draw the right shape
	 */
	public void mouseReleased(MouseEvent me) {
		// Save the end point
		finish = new Point(me.getX(), me.getY());

		// Check Color
		if (black.getState())
			nextGraphics.setColor(Color.black);
		else if (blue.getState())
			nextGraphics.setColor(Color.blue);
		else if (green.getState())
			nextGraphics.setColor(Color.green);
		else if (red.getState())
			nextGraphics.setColor(Color.red);
		else
			nextGraphics.setColor(Color.yellow);

		// Draw the shape or line onto "nextGraphics"
		if (line.getState())
			nextGraphics.drawLine((int) start.getX(), (int) start.getY(),
					(int) finish.getX(), (int) finish.getY());
		// else if(oval.getState()) nextGraphics.

		// Call repaint, so the change can be seen.
		repaint();
	}

	/*
	 * We didn't need these actions. But, we do need to define them, or we
	 * haven't complied with the MouseAction interface.
	 */

	// This is received, if the user momentarily presses the mouse button
	public void mouseClicked(MouseEvent me) {
	}

	// This is received, if the mouse enters the applet area from outside
	public void mouseEntered(MouseEvent me) {
	}

	// This is received, if the mouse exits the applet area from outside
	public void mouseExited(MouseEvent me) {
	}

}
