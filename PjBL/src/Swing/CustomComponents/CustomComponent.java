package Swing.CustomComponents;

import java.awt.*;

public abstract class CustomComponent extends Component {
    public void setAnchor(int x, int y) {
        super.setBounds(x, y, getPreferredSize().width, getPreferredSize().height);
    }

    public void setAnchor(Component refComp, char align, int gap) {
        if (align == 'V') {
            setBounds(refComp.getX(), refComp.getY() + refComp.getHeight() + gap, getPreferredSize().width, getPreferredSize().height);
        }
        if (align == 'H') {
            super.setBounds(refComp.getX() + refComp.getWidth() + gap, refComp.getY(), getPreferredSize().width, getPreferredSize().height);
        }
    }
}
