package historic.view;

import javax.swing.*;

/**
 * Created by Dean on 01/06/17.
 * presenters will know about the controller and use this to push to screen
 * popping will remove itself from the frame
 * names should be unique as will be on a first find basis
 */
public interface IPresenter {
    void push(JFrame main, PresenterController con);
    void pop();
    String getPresenterName();
}
