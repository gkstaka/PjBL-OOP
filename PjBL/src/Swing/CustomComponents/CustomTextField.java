package Swing.CustomComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomTextField extends JFormattedTextField {
    //private final String LIGHT_GREY = "#bababa";
    private String defaultText;
    private boolean displayDefaultText;

    public CustomTextField(String defaultText) {
        super(defaultText);
        setForeground(Color.GRAY);
        this.defaultText = defaultText;
        displayDefaultText = true;
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (displayDefaultText) {
                    setText("");
                    setForeground(Color.BLACK);
                    displayDefaultText = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("focus lost");
                if (getText().isEmpty() || getText().isBlank()) {

                    setText(defaultText);
                    setForeground(Color.GRAY);
                    displayDefaultText = true;
                }
            }
        });
    }

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
