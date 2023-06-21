package Swing.CustomComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CustomButton extends JButton implements ComponentInterface {

    public CustomButton(String text) {
        super(text);
        setSize(getPreferredSize());
    }

    public CustomButton(String text, int width, int height) {
        super(text);
        setSize(new Dimension(width, height));
    }

    //Overload eg
    public void setAnchor(int x, int y) {
        super.setBounds(x, y, getPreferredSize().width, getPreferredSize().height);
    }

    public void setAnchor(Component refComp, char align, int gap) {
        if (align == 'V') {
            super.setBounds(refComp.getX(), refComp.getY() + refComp.getHeight() + gap, getPreferredSize().width, getPreferredSize().height);
        }
        if (align == 'H') {
            super.setBounds(refComp.getX() + refComp.getWidth() + gap, refComp.getY(), getPreferredSize().width, getPreferredSize().height);
        }
    }


}
