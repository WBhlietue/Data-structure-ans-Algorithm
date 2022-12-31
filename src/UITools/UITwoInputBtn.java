package UITools;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UITwoInputBtn extends UIItems {
    public JTextField input1;
    public JTextField input2;
    public JButton btn;

    public UITwoInputBtn(JPanel panel, int height, int width, int x, int y, String btnTxt, int padding) {
        input1 = new JTextField();
        input2 = new JTextField();
        btn = new JButton(btnTxt);
        input1.setFont(f);
        input2.setFont(f);
        btn.setFont(f);
        input1.setSize((width - padding) / 2, (height - padding) / 2);
        input2.setSize((width - padding) / 2, (height - padding) / 2);
        input1.setLocation(x, y);
        input2.setLocation(x + padding + (width - padding) / 2, y);
        btn.setSize(width, (height - padding) / 2);
        btn.setLocation(x, y + padding + (height - padding) / 2);
        panel.add(input1);
        panel.add(input2);
        panel.add(btn);
    }

    public String GetInput1Text() {
        String s = input1.getText();
        input1.setText("");
        return s;
    }

    public void SetInput1Text(String str) {
        input1.setText(str);
    }

    public String GetInput2Text() {
        String s = input2.getText();
        input2.setText("");
        return s;
    }

    public void SetInput2Text(String str) {
        input1.setText(str);
    }

    public String GetBtnText() {
        return btn.getText();
    }

    public void SetBtnText(String str) {
        btn.setText(str);
    }

    public void AddListener(IUIListener lis) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lis.OnClick();
            }
        });
    }
}
