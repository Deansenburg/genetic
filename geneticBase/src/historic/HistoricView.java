package historic;

import helper.ViewHelper;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;

public class HistoricView extends Component implements MouseWheelListener, MouseMotionListener, MouseListener{
	
	JFrame main;
	
	HistoricModel model;
	
	ViewHelper helper;

	Point lastPos;
	
	public HistoricView(HistoricModel m)
	{
		model = m;
		helper = new ViewHelper();
		helper.addScale(1);
	}
	
	public void createGui()
	{
		main = new JFrame();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.add(this);
		main.addMouseWheelListener(this);
		main.addMouseMotionListener(this);
		main.addMouseListener(this);
		main.setVisible(true);
		main.pack();
		main.repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		
		float x = 0, y = 0;
		
		g.setColor(Color.BLACK);

		for (Generation gen : model.getGenerations()) {
			x = 0;
			y = gen.generation;
			for (HistoricItem i : gen.generationMembers) {
				Point p = helper.translate(x * 10, y * 10);
				g.drawOval(p.x, p.y, helper.scale(5), helper.scale(5));
				x += 1;
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(100, 100);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int rot = e.getWheelRotation();
		if (rot < 0){
			helper.addScale(2);
			repaint();
		}
		else
		{
			helper.addScale(0.5f);
			repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point cur = e.getLocationOnScreen();
		int xDif = cur.x - lastPos.x;
		int yDif = cur.y - lastPos.y;
		helper.addXOffset(xDif);
		helper.addYOffset(yDif);
		repaint();
		lastPos = cur;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		lastPos = e.getLocationOnScreen();
	}

	//unused
	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
