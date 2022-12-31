import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Lab3.MyStack;

public class Test extends JFrame {
    public Test() {
        super("test");
        Font f = new Font("Arial", Font.ITALIC, 40);
        setSize(1280, 720);
        setLayout(null);
        JLabel label = new JLabel("asdasdasd");
        label.setSize(1000, 50);
        label.setLocation(500, 100);
        label.setFont(f);
        add(label);

        JTextField field = new JTextField();
        field.setSize(300, 50);
        field.setLocation(500, 300);
        field.setFont(f);
        add(field);

        JButton btn = new JButton("btn");
        btn.setSize(200, 50);
        btn.setLocation(500, 500);
        btn.setFont(f);
        add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = field.getText();
                field.setText("");
                label.setText(str);
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        Integer[] a = { 10, 30, 20, 10, 30, 40 };

        for (int i = 1; i < 6; i++)

            for (int j = i + 1; j < 6; j++)

                if (a[i] == a[j]) {

                    a[j] = 0;

                }
        System.out.println(Tools.ArrayToString(a));
    }
}
