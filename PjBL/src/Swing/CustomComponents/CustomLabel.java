package Swing.CustomComponents;

import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel implements ComponentInterface{
    public CustomLabel(String text) {
        super(text);

    }


    public CustomLabel(String text, int height) {
        super(text);
        setPreferredSize(new Dimension(getPreferredSize().width, height));
    }

    //Overload eg
    // @Override
    public void setAnchor(int x, int y) {
        super.setBounds(x, y, getPreferredSize().width, getPreferredSize().height);
    }
    @Override
    public void setAnchor(Component refComp, char align, int gap) {
        if (align == 'V') {
            super.setBounds(refComp.getX(), refComp.getY() + refComp.getHeight() + gap, getPreferredSize().width, getPreferredSize().height);
        }
        if (align == 'H') {
            super.setBounds(refComp.getX() + refComp.getWidth() + gap, refComp.getY(), getPreferredSize().width, getPreferredSize().height);
        }
    }
}
