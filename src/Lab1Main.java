import java.awt.*;
import java.awt.event.*;

import Lab1.MyArrayLinearList;

public class Lab1Main extends Frame {
    public static MyArrayLinearList arrayList;
    Label list;
    TextField field;
    Label output;

    public Lab1Main() {
        super("Lab1");
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
        list = new Label(arrayList.toString(), 1);
        field = new TextField("");
        list.setFont(f);
        list.setSize(1200, 100);
        list.setLocation(40, 50);
        field.setFont(f);
        field.setSize(200, 50);
        field.setLocation(200, 300);
        output = new Label("output");
        output.setSize(400, 50);
        output.setLocation(700, 300);
        output.setFont(f);

        Button sum = new Button("Sum");
        Button max = new Button("Max");
        Button min = new Button("Min");
        Button sort = new Button("Sort");
        Button removeOdd = new Button("RemOdd");
        Button add = new Button("Add");
        Button exit = new Button("Exit");
        Button avarage = new Button("Avarage");

        sum.setSize(200, 50);
        sum.setFont(f);
        sum.setLocation(200, 560);
        max.setSize(200, 50);
        max.setFont(f);
        max.setLocation(200, 620);
        min.setSize(200, 50);
        min.setFont(f);
        min.setLocation(200, 680);
        sort.setSize(200, 50);
        sort.setFont(f);
        sort.setLocation(200, 740);
        removeOdd.setSize(200, 50);
        removeOdd.setFont(f);
        removeOdd.setLocation(200, 800);
        add.setSize(200, 50);
        add.setFont(f);
        add.setLocation(200, 500);
        exit.setSize(200, 50);
        exit.setFont(f);
        exit.setLocation(500, 560);
        avarage.setSize(200, 50);
        avarage.setFont(f);
        avarage.setLocation(500, 500);

        sum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                output.setText("Sum = " + arrayList.Sum());
            }
        });
        max.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                output.setText("Max = " + arrayList.Max());
            }
        });
        min.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                output.setText("Min = " + arrayList.Min());
            }
        });
        sort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                arrayList = arrayList.Sort();
                list.setText(arrayList.toString());
            }
        });

        removeOdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                arrayList.RemoveOdd();
                list.setText(arrayList.toString());
            }
        });
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(field.getText());
                arrayList.Add(a);
                list.setText(arrayList.toString());
                field.setText("");
            }
        });
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                System.exit(0);
            }
        });
        avarage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                output.setText("Avarage = " + arrayList.Avarage());
            }
        });
        add(sum);
        add(max);
        add(min);
        add(sort);
        add(removeOdd);
        add(add);
        add(exit);
        add(avarage);
        add(list);
        add(field);
        add(output);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        arrayList = new MyArrayLinearList();
        arrayList.Add(12);
        arrayList.Add(14);
        arrayList.Add(64);
        arrayList.Add(34);
        arrayList.Add(56);
        arrayList.Add(23);
        arrayList.Add(142);
        arrayList.Add(141);
        arrayList.Add(6);
        arrayList.Add(345);
        arrayList.Add(563);
        arrayList.Add(231);
        new Lab1Main();
    }
}
