package UITools;

import java.awt.Font;

import javax.swing.*;

public class UIInputLabel extends UIItems {

    public JLabel label;
    public JTextField field;

    public String GetInputText() {
        String s = field.getText();
        field.setText("");
        return s;
    }

    public void SetInputText(String value) {
        field.setText(value);
    }

    public void SetText(String value) {
        label.setText(value);
    }

    public UIInputLabel(JPanel frame, int x, int y, int height, int width, String text) {
        field = new JTextField();
        field.setFont(f);
        field.setSize(width, height);
        field.setLocation(x, y);
        label = new JLabel(text);
        label.setFont(f);
        label.setSize(width, height);
        label.setLocation(x, y - height);
        frame.add(field);
        frame.add(label);
    }

    public UIInputLabel(JFrame frame, int x, int y, int height, int width, String text, int paddingX, int paddingY) {
        JTextField field = new JTextField();
        field.setFont(f);
        field.setSize(width, height);
        field.setLocation(x, y);
        JLabel label = new JLabel(text);
        label.setFont(f);
        label.setSize(width, height);
        label.setLocation(x + paddingX, y - height + paddingY);
        frame.add(field);
        frame.add(label);
    }

}
