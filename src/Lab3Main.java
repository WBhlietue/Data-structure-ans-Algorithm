import Lab3.MyStack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.tools.Tool;

public class Lab3Main extends JFrame{

    MyStack stack = new MyStack();
    public Lab3Main() {
        super("stack");
        Font f = new Font("Arial", Font.PLAIN, 20);
        Border bor = BorderFactory.createLineBorder(Color.black);
        setSize(1280, 720);
        
        Container cont = getContentPane();
        cont.setLayout(null);
        JLabel label = new JLabel(Tools.ArrayToString(stack.toArray()));
        label.setSize(1000, 100);
        label.setLocation(140, 100);
        label.setFont(f);
        label.setBorder(bor);
        cont.add(label);

        JTextField field = new JTextField("");
        // field.setBorder(bor);
        field.setSize(200, 50);
        field.setLocation(100, 300);
        field.setFont(f);
        cont.add(field);

        JButton btn = new JButton("Push");
        btn.setSize(200, 50);
        btn.setLocation(300, 300);
        btn.setBorder(bor);
        btn.setFont(f);
        cont.add(btn);
        btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(field.getText().equals("")){
                    return;
                }
                stack.push(field.getText());
                label.setText(Tools.ArrayToString(stack.toArray()));
                field.setText("");
            }
        });

        JTextField fieldRange = new JTextField("");
        // field.setBorder(bor);
        fieldRange.setSize(200, 50);
        fieldRange.setLocation(100, 500);
        fieldRange.setFont(f);
        cont.add(fieldRange);

        JButton btnRange = new JButton("AddRange");
        btnRange.setSize(200, 50);
        btnRange.setLocation(300, 500);
        btnRange.setBorder(bor);
        btnRange.setFont(f);
        btnRange.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fieldRange.getText().equals("")){
                    return;
                }
                String[] str = fieldRange.getText().trim().split("\\s+");
                Object[] obj = new Object[str.length];
                fieldRange.setText("");
                for (int i = 0; i < obj.length; i++) {
                    try {
                        obj[i] = (Object) (str[i]);
                    } catch (Exception ex) {
                        label.setText("Chain1 error");
                        // chain1 = backup;
                        return;
                    }
                }
                stack.AddRange(obj);
                label.setText(Tools.ArrayToString(stack.toArray()));
            }
        });
        cont.add(btnRange);

        JButton pop = new JButton("pop");
        pop.setSize(200, 50);
        pop.setLocation(700, 300);
        pop.setFont(f);
        pop.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                stack.pop();
                }catch(Exception eee) {
                    label.setText("stack is empty");
                    return;
                }
                label.setText(Tools.ArrayToString(stack.toArray()));
            }
        });
        cont.add(pop);

        JButton rand = new JButton("rand");
        rand.setSize(200, 50);
        rand.setLocation(700, 500);
        rand.setFont(f);
        rand.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                stack = stack.rand();
                label.setText(Tools.ArrayToString(stack.rand().toArray()));
            }
        });
        cont.add(rand);

        JButton uniq = new JButton("rand");
        uniq.setSize(200, 50);
        uniq.setLocation(700, 500);
        uniq.setFont(f);
        uniq.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MyStack s  = stack.rand();
                label.setText(Tools.ArrayToString(stack.rand().toArray()));
            }
        });
        cont.add(rand);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Lab3Main();
        MyStack stack = new MyStack();
        stack.push(0);
        stack.push(0);
        stack.push(0);
        stack.AddRange(new Object[] { 1, 2, 3, 7, 4, 5, 6, 7, 7, 7 });

        System.out.println(Tools.ArrayToString(stack.toArray()));

        System.out.println(Tools.ArrayToString(stack.unique().toArray()));

        System.out.println(Tools.ArrayToString(stack.rand().toArray()));
    }

}
