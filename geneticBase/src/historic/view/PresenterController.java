package historic.view;

import com.sun.security.jgss.InquireSecContextPermission;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dean on 01/06/17.
 */
public class PresenterController {
    JFrame main;

    List<IPresenter> pageStack;
    List<IPresenter> pages;
    IPresenter defaultPage;

    public PresenterController(List<IPresenter> p, IPresenter defaultPage)
    {
        pages = p;
        pageStack = new ArrayList<>();
        this.defaultPage = defaultPage;

        main = new JFrame();
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);

        requestScreenChange(defaultPage.getPresenterName());
    }

    private IPresenter find(String id)
    {
        for (IPresenter p:pages)
        {
            if (p.getPresenterName().equals(id))
            {
                return p;
            }
        }
        throw null;
    }

    private void addPresenter(IPresenter p)
    {
        pageStack.add(p);
        p.push(main, this);
    }
    private boolean removeLastPresenterFromScreen()
    {
        if (pageStack.size() > 0)
        {
            IPresenter last = pageStack.get(pageStack.size()-1);
            last.pop();
            return true;
        }
        return false;
    }
    private boolean removeLastPresenter()
    {
        if (removeLastPresenterFromScreen()) {
            pageStack.remove(pageStack.size() - 1);
            return true;
        }
        return false;
    }

    public void requestScreenChange(String pageId)
    {
        IPresenter page = find(pageId);
        if (page != null)
        {
            removeLastPresenterFromScreen();
            addPresenter(page);
            main.validate();
            main.repaint();
        }
    }

    public void requestBackPage()
    {
        if (removeLastPresenter()) {
            addPresenter(pageStack.get(pageStack.size() - 1));
            main.repaint();
        }
    }

    public void render()
    {
        main.repaint();
    }
}
