public class Camera
{
	private static final int height = 1024;
	private static final int width = 768;
	
	private int x;
	private int y;
	
	private double latittude;
	private double longitude;
	
	public Camera(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public int getArea()
	{
		return this.getX()*this.getY();
	}
	
	public double[] getGPS()
	{
		return null;
	}
	
	private double getGPS(String direction)
	{
		return null;
	}
	
	
	
}