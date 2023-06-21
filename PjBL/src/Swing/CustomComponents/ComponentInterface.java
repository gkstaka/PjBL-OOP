package Swing.CustomComponents;

import java.awt.*;

public interface ComponentInterface {
    void setAnchor(int x, int y);
    void setAnchor(Component refComp, char align, int gap);
}
