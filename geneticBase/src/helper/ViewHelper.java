package helper;

import java.awt.Point;

public class ViewHelper {

	private float mZoom = 1f;

	private int mXOffset = 0;

	private int mYOffset = 0;

	private int mWindowOffsetX = 0;

	private int mWindowOffsetY = 0;

	private int mWindowWidth = 0;

	private int mWindowHeight = 0;

	float minX=Float.MAX_VALUE, maxX=0;

	public int scale(float x)
	{
		return (int) (x * mZoom);
	}

	public void addXOffset(int i)
	{
		mXOffset += (int) (i/mZoom);
	}

	public void addYOffset(int i)
	{
		mYOffset += (int) (i/mZoom);
	}

	public void multiplyZoom(float factor)
	{
		mZoom *= factor;
	}

	public void setWindowSize(int width, int height)
	{
		mWindowOffsetX = width/2;
		mWindowOffsetY = height/2;
		mWindowWidth = width;
		mWindowHeight = height;
		mXOffset = -(int) ((minX+maxX)/2);
	}

	public int adjustX(float point) {
		point += mXOffset;
		point *= mZoom;
		point += mWindowOffsetX;
		return (int) point;
	}
	public int adjustY(float point) {
		point += mYOffset;
		point *= mZoom;
		point += mWindowOffsetY;
		return (int) point;
	}
	private void updateCenter(float x)
	{
		if (x < minX)
		{
			minX = x;
		}
		if (x > maxX)
		{
			maxX = x;
		}
	}
	public Point translate(float x, float y)
	{
		updateCenter(x);
		Point p = new Point(adjustX(x), adjustY(y));
		return p;
	}
}
