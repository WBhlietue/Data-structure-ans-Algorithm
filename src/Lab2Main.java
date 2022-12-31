import java.awt.*;
import java.awt.event.*;

import Lab2.*;

public class Lab2Main extends Frame {
    public static MyChain chain1;
    public static MyChain chain2;
    Label list1;
    Label list2;
    Label union;
    Label inter;
    TextField field1;
    TextField field2;
    Label output;

    public Lab2Main() {
        super("Lab2");
        this.setSize(1280, 720);
        setLayout(null);
        setSize(1280, 900);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                setVisible(false);
                System.exit(0);
            }
        });
        Font f = new Font("Arial", Font.PLAIN, 30);
        list1 = new Label("chain1 = " + chain1.toString(), 1);
        list2 = new Label("chain2 = " + chain2.toString(), 1);
        field1 = new TextField("");
        field2 = new TextField("");
        list1.setFont(f);
        list1.setSize(1200, 100);
        list1.setLocation(40, 50);
        list2.setFont(f);
        list2.setSize(1200, 100);
        list2.setLocation(40, 300);
        union = new Label("union", 1);
        inter = new Label("intersection", 1);

        union.setFont(f);
        union.setSize(1200, 100);
        union.setLocation(40, 600);

        inter.setFont(f);
        inter.setSize(1200, 100);
        inter.setLocation(40, 700);

        field1.setFont(f);
        field1.setSize(600, 50);
        field1.setLocation(200, 200);
        field2.setFont(f);
        field2.setSize(600, 50);
        field2.setLocation(200, 450);
        output = new Label("output");
        output.setSize(400, 50);
        output.setLocation(700, 300);
        output.setFont(f);

        Button addRange1 = new Button("AddRange");
        Button addRange2 = new Button("AddRange");

        addRange1.setSize(200, 50);
        addRange1.setFont(f);
        addRange1.setLocation(1000, 200);
        addRange2.setSize(200, 50);
        addRange2.setFont(f);
        addRange2.setLocation(1000, 450);

        union.setText("Union = " + chain1.Union(chain2).ToArray());
        inter.setText("Intersection = " + chain1.Intersection(chain2).ToArray());

        addRange1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(field1.getText().equals("")){
                    return;
                }
                String[] str = field1.getText().trim().split("\\s+");
                Object[] obj = new Object[str.length];
                field1.setText("");
                MyChain backup = new MyChain(chain1);
                for (int i = 0; i < obj.length; i++) {
                    try {
                        obj[i] = (Object) (str[i]);
                    } catch (Exception ex) {
                        list1.setText("Chain1 error");
                        chain1 = backup;
                        return;
                    }
                }

                chain1.AddRange(obj);
                list1.setText("Chain1 = " + chain1.toString());
                union.setText("Union = " + chain1.Union(chain2));
                inter.setText("Intersection = " + chain1.Intersection(chain2));
            }
        });
        addRange2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(field2.getText().equals("")){
                    return;
                }
                String[] str = field2.getText().trim().split("\\s+");
                Object[] obj = new Object[str.length];
                field2.setText("");
                MyChain backup = new MyChain(chain2);
                for (int i = 0; i < obj.length; i++) {
                    try {
                        obj[i] = (Object) (str[i]);
                    } catch (Exception ex) {
                        list1.setText("Chain2 error");
                        chain2 = backup;
                        return;
                    }
                }
                chain2.AddRange(obj);
                list2.setText("Chain2 = " + chain2.toString());
                union.setText("Union = " + chain1.Union(chain2));
                inter.setText("Intersection = " + chain1.Intersection(chain2));
            }
        });

        add(addRange1);
        add(addRange2);
        add(list1);
        add(list2);
        add(field1);
        add(field2);
        add(output);
        add(union);
        add(inter);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        chain1 = new MyChain();
        chain2 = new MyChain();
        new Lab2Main();
    }
}
