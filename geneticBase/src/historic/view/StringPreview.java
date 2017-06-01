package historic.view;

import helper.StringToBinary;
import helper.ViewHelper;
import historic.HistoricItem;
import historic.IPreview;
import javafx.scene.text.TextAlignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

/**
 * Created by Dean on 01/06/17.
 */
public class StringPreview extends Component implements IPreview, IPresenter, MouseListener {
    JFrame main;
    PresenterController con;
    String gene;

    StringToBinary sTB = new StringToBinary();
    ViewHelper helper = new ViewHelper();

    @Override
    public void setupPreview(HistoricItem item)
    {
        gene = item.getGene().String();
    }

    @Override
    public void push(JFrame main, PresenterController con) {
        this.main = main;
        this.con = con;
        main.add(this);
        main.addMouseListener(this);
    }

    @Override
    public void pop() {
        main.remove(this);
        main.removeMouseListener(this);
        main = null;
        con = null;
    }

    @Override
    public void paint(Graphics g) {
        int posx = getWidth()/2, posy = getHeight()/2;

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        helper.paintString(g, gene, posx, posy, TextAlignment.CENTER);
        helper.paintString(g, sTB.getString(gene), posx, posy+getFontMetrics(getFont()).getHeight(), TextAlignment.CENTER);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }

    @Override
    public String getPresenterName() {
        return "StringPreview";
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        con.requestBackPage();
    }

    //unused
    @Override
    public void mousePressed(MouseEvent e) {

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
