package Swing.CustomComponents;

import java.awt.*;

public class CustomGridBagConstraints extends GridBagConstraints {

    public CustomGridBagConstraints() {
    }

    public void cell(int x, int y) {
        this.gridx = x;
        this.gridy = y;
    }
}
