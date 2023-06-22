package Swing.CustomComponents;

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel implements ComponentInterface {

    public CustomPanel() {
        super();
    }

    @Override
    public void setAnchor(int x, int y) {
        super.setBounds(x, y,  getSize().width,  getSize().height);
    }

    @Override
    public void setAnchor(Component refComp, char align, int gap) {
        if (align == 'V') {
            super.setBounds(refComp.getX(), refComp.getY() + refComp.getHeight() + gap,  getSize().width,  getSize().height);
        }
        if (align == 'H') {
            super.setBounds(refComp.getX() + refComp.getWidth() + gap, refComp.getY(),  getSize().width,  getSize().height);
        }
    }
}
