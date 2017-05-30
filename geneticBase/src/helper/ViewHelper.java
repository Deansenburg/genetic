package helper;

import java.awt.Point;

public class ViewHelper {

	float xOffset = 0, yOffset = 0, scale = 1;
	
	public void addXOffset(float x)
	{
		xOffset += x; 
	}
	public void addYOffset(float y)
	{
		yOffset += y;
	}
	public void addScale(float s)
	{
		scale *= s;
	}
	
	public Point translate(float x, float y)
	{
		Point p = new Point();
		p.x = (int)((x + xOffset) * scale);
		p.y = (int) ((y + yOffset) * scale);
		return p;
	}

	public int scale(float x)
	{
		return (int) (x * scale);
	}

}
