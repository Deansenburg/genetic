package historic;

import helper.ViewHelper;
import historic.view.IPresenter;
import historic.view.PresenterController;
import historic.view.StringPreview;
import javafx.scene.text.TextAlignment;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;

import javax.swing.JFrame;

public class HistoricView extends Component implements MouseWheelListener, MouseMotionListener, MouseListener, IPresenter{
	
	JFrame main;
	
	HistoricModel model;
	
	ViewHelper helper;

	Point lastPos;

	StringPreview preview;

	PresenterController presController;

	float space = 20;
	
	public HistoricView(HistoricModel m, StringPreview p)
	{
		model = m;
		preview = p;
		helper = new ViewHelper();
	}
	
	@Override
	public void paint(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		helper.setWindowSize(width, height);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		
		float x = 0, y = 0;
		
		g.setColor(Color.BLACK);
		Point start = helper.translate(x * space, y * space);
		for (Generation gen : model.getGenerations()) {
			x = 0;
			y = gen.generation;
			for (HistoricItem i : gen.getMembers()) {
				Point p = helper.translate(x * space, y * space);
				g.drawOval(p.x, p.y, helper.scale(5), helper.scale(5));
				helper.paintString(g, "G "+y+"-----", start.x, p.y+1, TextAlignment.RIGHT);
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
			//helper.multiplyZoom(2);
			helper.addYOffset(50);
			repaint();
		}
		else
		{
			//helper.multiplyZoom(0.5f);
			helper.addYOffset(-50);
			repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point cur = e.getLocationOnScreen();
		//int xDif = cur.x - lastPos.x;
		//int yDif = cur.y - lastPos.y;
		//helper.addXOffset(xDif);
		//helper.addYOffset(yDif);
		repaint();
		lastPos = cur;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		lastPos = e.getLocationOnScreen();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		int h = main.getHeight() - main.getContentPane().getHeight();

		Point p = e.getPoint();
		p.y -= h;

		int size = helper.scale(5) / 2;
		p.x-=size;

		p = helper.reverseTranslate(p.x, p.y);
		int gen = (int)Math.ceil(p.y / space);
		int pos = (int)Math.floor(p.x / space);

		Generation g = model.getGeneration(gen);
		if(g != null)
		{
			HistoricItem i = g.getMember(pos);
			if (i != null) {
				preview.setupPreview(i);
				presController.requestScreenChange(preview.getPresenterName());
			}
		}
	}

	@Override
	public void push(JFrame main, PresenterController con) {
		this.main = main;
		presController = con;
		main.add(this);
		main.addMouseListener(this);
		main.addMouseMotionListener(this);
		main.addMouseWheelListener(this);
		Dimension d = getSize();
		if (d.getHeight() == 0 || d.getWidth() == 0) {
			main.setSize(getPreferredSize());
		}
		else
		{
			main.setSize(main.getSize());
		}
	}

	@Override
	public void pop() {
		main.remove(this);
		main.removeMouseListener(this);
		main.removeMouseMotionListener(this);
		main.removeMouseWheelListener(this);
		main = null;
		presController = null;
	}

	@Override
	public String getPresenterName() {
		return "HistoricOverview";
	}

	//unused
	@Override
	public void mouseMoved(MouseEvent e) {

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
