import javax.swing.*;

import BiyDaalt.Lessen;
import BiyDaalt.Major;
import BiyDaalt.Student;
import BiyDaalt.Subject;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import Lab1.MyArrayLinearListT;
import Lab2.MyChain;
import utilities.ChangeArrayLength;

public class Registration {
    public static MyArrayLinearListT<Student> student;
    public static MyArrayLinearListT<Subject> subject;
    public static MyArrayLinearListT<Major> major;

    static MyChain<Lessen> less;

    static JFrame mainFrame;
    static JFrame majorReg;
    static JFrame studentReg;
    static JFrame subjectReg;
    static JFrame lessonReg;
    static JFrame list;

    public static void main(String[] args) {

        Load();

        MainRegister();
        MajorRegister();
        SubjectRegister();
        StudentRegister();
    }

    public static void Save() {
        try {
            File file = new File("Student.txt");
            FileOutputStream output = new FileOutputStream(file);
            PrintWriter writer = new PrintWriter(output);
            for (int i = 0; i < student.size(); i++) {
                writer.println(student.Get(i).ConvertString());
            }
            writer.close();

            file = new File("Subject.txt");
            output = new FileOutputStream(file);
            writer = new PrintWriter(output);
            for (int i = 0; i < subject.size(); i++) {
                writer.println(subject.Get(i).ConvertString());
            }
            writer.close();

            file = new File("Major.txt");
            output = new FileOutputStream(file);
            writer = new PrintWriter(output);
            for (int i = 0; i < major.size(); i++) {
                writer.println(major.Get(i).ConvertString());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Error 1");
        }
    }

    public static void Load() {
        student = new MyArrayLinearListT<>();
        subject = new MyArrayLinearListT<>();
        major = new MyArrayLinearListT<>();
        try {
            File file = new File("Student.txt");
            FileReader reader = new FileReader(file);
            BufferedReader read = new BufferedReader(reader);
            try {
                while (true) {
                    student.Add(Student.FromString(read.readLine()));
                }
            } catch (Exception e) {
                System.out.println("Error 2");
            }
            read.close();

            file = new File("Subject.txt");
            reader = new FileReader(file);
            read = new BufferedReader(reader);
            try {
                while (true) {
                    subject.Add(Subject.FromString(read.readLine()));
                }
            } catch (Exception e) {
                System.out.println("Error 2.1");
            }
            read.close();

            file = new File("Major.txt");
            reader = new FileReader(file);
            read = new BufferedReader(reader);
            try {
                while (true) {
                    major.Add(Major.FromString(read.readLine()));
                }
            } catch (Exception e) {
                System.out.println("Error 2.2");
            }
            read.close();
        } catch (Exception w) {
            System.out.println("Error 3");
        }
    }

    static void MainRegister() {
        Font f = new Font("Arial", Font.PLAIN, 30);
        mainFrame = new JFrame("Biy Daalt");
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Save();
                System.out.println("!23");
                System.exit(0);
            }
        });
        mainFrame.setSize(900, 720);
        mainFrame.setLayout(null);

        JButton may = new JButton("Add Major");
        may.setSize(200, 50);
        may.setLocation(25, 50);
        may.setFont(f);
        may.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowMajorRegister(mainFrame.getLocation());
            }
        });
        mainFrame.add(may);
        JButton sub = new JButton("Add Subject");
        sub.setSize(200, 50);
        sub.setLocation(25, 125);
        sub.setFont(f);
        sub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowSubjectRegister(mainFrame.getLocation());
            }
        });
        mainFrame.add(sub);

        JButton stu = new JButton("Add Student");
        stu.setSize(200, 50);
        stu.setLocation(25, 200);
        stu.setFont(f);
        stu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowStudentRegister(mainFrame.getLocation());
            }
        });
        mainFrame.add(stu);

        JButton less = new JButton("Show All Subject");
        less.setSize(400, 50);
        less.setLocation(400, 50);
        less.setFont(f);
        less.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] str = new String[subject.size()];
                for (int i = 0; i < str.length; i++) {
                    str[i] = subject.Get(i).toString();
                }
                System.out.println(str.length);
                ShowList(str, mainFrame.getLocation());
            }
        });
        mainFrame.add(less);
        JButton maj = new JButton("Show All Major");
        maj.setSize(400, 50);
        maj.setLocation(400, 125);
        maj.setFont(f);
        maj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] str = new String[major.size()];
                for (int i = 0; i < str.length; i++) {
                    str[i] = major.Get(i).toString();
                }
                ShowList(str, mainFrame.getLocation());
            }
        });
        mainFrame.add(maj);
        mainFrame.add(less);
        JButton showGPA = new JButton("Show Avarage GPA");
        showGPA.setSize(400, 50);
        showGPA.setLocation(400, 200);
        showGPA.setFont(f);
        showGPA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float avarage = 0;
                for (int i = 0; i < student.size(); i++) {
                    avarage += student.Get(i).CalculateGpa();
                }
                avarage /= student.size();
                JOptionPane.showMessageDialog(null, avarage);
            }
        });
        mainFrame.add(showGPA);
        JButton showF = new JButton("Show 3F");
        showF.setSize(400, 50);
        showF.setLocation(400, 275);
        showF.setFont(f);
        showF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] str = new String[0];
                for (int i = 0; i < student.size(); i++) {
                    if (student.Get(i).CheckIsF()) {
                        str = (String[]) ChangeArrayLength.changeLength1D(str, str.length + 1);
                        str[str.length - 1] = student.Get(i).toString();
                    }
                }
                ShowList(str, mainFrame.getLocation());
            }
        });
        mainFrame.add(showF);
        JButton showStudent = new JButton("Show All Students");
        showStudent.setSize(400, 50);
        showStudent.setLocation(400, 350);
        showStudent.setFont(f);
        showStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] str = new String[student.size()];
                for (int i = 0; i < student.size(); i++) {
                    str[i] = student.Get(i).GetAllStatus();
                }

                ShowList(str, mainFrame.getLocation());
            }
        });
        mainFrame.add(showStudent);
        JButton majFilter = new JButton("Show Student with Major");
        majFilter.setSize(400, 50);
        majFilter.setLocation(400, 425);
        majFilter.setFont(f);
        majFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowMajorFilter(mainFrame.getLocation());
            }
        });
        mainFrame.add(majFilter);
        JButton subFilter = new JButton("Show Student with Subject");
        subFilter.setSize(400, 50);
        subFilter.setLocation(400, 500);
        subFilter.setFont(f);
        subFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowSubjectFilter(mainFrame.getLocation());
            }
        });
        mainFrame.add(subFilter);

        mainFrame.setVisible(true);
    }

    static void MajorRegister() {
        Font f = new Font("Arial", Font.PLAIN, 30);
        majorReg = new JFrame("Major");
        majorReg.setSize(500, 450);
        majorReg.setLayout(null);
        JLabel label = new JLabel("New Major");
        label.setSize(400, 100);
        label.setLocation(175, 0);
        label.setFont(f);
        majorReg.add(label);

        JLabel name = new JLabel("Name");
        name.setSize(400, 100);
        name.setLocation(25, 75);
        name.setFont(f);
        majorReg.add(name);
        JTextField fName = new JTextField();
        fName.setSize(300, 50);
        fName.setLocation(150, 100);
        fName.setFont(f);
        majorReg.add(fName);

        JLabel code = new JLabel("Code");
        code.setSize(400, 100);
        code.setLocation(25, 175);
        code.setFont(f);
        majorReg.add(code);
        JTextField fCode = new JTextField();
        fCode.setSize(300, 50);
        fCode.setLocation(150, 200);
        fCode.setFont(f);
        majorReg.add(fCode);

        JButton cancel = new JButton("Cancel");
        cancel.setSize(200, 50);
        cancel.setLocation(25, 300);
        cancel.setFont(f);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fCode.setText("");
                fName.setText("");
                majorReg.setVisible(false);
            }
        });
        majorReg.add(cancel);
        JButton add = new JButton("Add");
        add.setSize(200, 50);
        add.setLocation(250, 300);
        add.setFont(f);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                major.Add(new Major(fCode.getText(), fName.getText()));
                fCode.setText("");
                fName.setText("");
                majorReg.setVisible(false);
            }
        });
        majorReg.add(add);
    }

    public static void ShowMajorRegister(Point p) {
        majorReg.setLocation(p);
        majorReg.setVisible(true);
    }

    static void SubjectRegister() {
        Font f = new Font("Arial", Font.PLAIN, 30);
        subjectReg = new JFrame("Subject");
        subjectReg.setSize(500, 550);
        subjectReg.setLayout(null);
        JLabel label = new JLabel("New Subject");
        label.setSize(400, 100);
        label.setLocation(175, 0);
        label.setFont(f);
        subjectReg.add(label);

        JLabel name = new JLabel("Name");
        name.setSize(400, 100);
        name.setLocation(25, 75);
        name.setFont(f);
        subjectReg.add(name);
        JTextField fName = new JTextField();
        fName.setSize(300, 50);
        fName.setLocation(150, 100);
        fName.setFont(f);
        subjectReg.add(fName);

        JLabel code = new JLabel("Code");
        code.setSize(400, 100);
        code.setLocation(25, 175);
        code.setFont(f);
        subjectReg.add(code);
        JTextField fCode = new JTextField();
        fCode.setSize(300, 50);
        fCode.setLocation(150, 200);
        fCode.setFont(f);
        subjectReg.add(fCode);

        JLabel credit = new JLabel("Credits");
        credit.setSize(400, 100);
        credit.setLocation(25, 275);
        credit.setFont(f);
        subjectReg.add(credit);
        JComboBox<Integer> fCredit = new JComboBox<Integer>(new Integer[] { 1, 2, 3 });
        fCredit.setSize(300, 50);
        fCredit.setLocation(150, 300);
        fCredit.setFont(f);
        subjectReg.add(fCredit);

        JButton cancel = new JButton("Cancel");
        cancel.setSize(200, 50);
        cancel.setLocation(25, 400);
        cancel.setFont(f);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fCode.setText("");
                fName.setText("");
                subjectReg.setVisible(false);
            }
        });
        subjectReg.add(cancel);
        JButton add = new JButton("Add");
        add.setSize(200, 50);
        add.setLocation(250, 400);
        add.setFont(f);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subject.Add(new Subject(fCode.getText(), fName.getText(), (int) fCredit.getSelectedItem()));
                fCode.setText("");
                fName.setText("");
                subjectReg.setVisible(false);
            }
        });
        subjectReg.add(add);
    }

    public static void ShowSubjectRegister(Point p) {
        subjectReg.setLocation(p);
        subjectReg.setVisible(true);
    }

    static JTextArea pane = new JTextArea();

    static void StudentRegister() {

        less = new MyChain<Lessen>();
        pane.setEditable(false);
        Font f = new Font("Arial", Font.PLAIN, 30);
        studentReg = new JFrame("Student");
        studentReg.setSize(700, 800);
        studentReg.setLayout(null);
        JLabel label = new JLabel("New Student");
        label.setSize(400, 100);
        label.setLocation(275, 0);
        label.setFont(f);
        studentReg.add(label);

        JLabel name = new JLabel("Code");
        name.setSize(400, 100);
        name.setLocation(125, 75);
        name.setFont(f);
        studentReg.add(name);
        JTextField fName = new JTextField();
        fName.setSize(300, 50);
        fName.setLocation(250, 100);
        fName.setFont(f);
        studentReg.add(fName);

        JLabel maj = new JLabel("Major");
        maj.setSize(400, 100);
        maj.setLocation(125, 175);
        maj.setFont(f);
        studentReg.add(maj);
        JComboBox<Major> fmaj = new JComboBox<>(major.toArray());
        fmaj.setSize(400, 50);
        fmaj.setLocation(250, 200);
        fmaj.setFont(f);
        studentReg.add(fmaj);

        JScrollPane lessons = new JScrollPane();

        pane.setSize(450, 225);
        pane.setLocation(100, 0);
        pane.setFont(f);
        lessons.setViewportView(pane);

        lessons.setSize(450, 225);
        lessons.setLocation(125, 300);
        studentReg.add(lessons);

        JButton addL = new JButton("Add lesson");
        addL.setSize(200, 50);
        addL.setLocation(250, 550);
        addL.setFont(f);
        studentReg.add(addL);
        addL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowLessonRegister(studentReg.getLocation());
            }
        });

        JButton cancel = new JButton("Cancel");
        cancel.setSize(200, 50);
        cancel.setLocation(125, 650);
        cancel.setFont(f);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fName.setText("");
                pane.setText("");
                studentReg.setVisible(false);
            }
        });
        studentReg.add(cancel);
        JButton add = new JButton("Add");
        add.setSize(200, 50);
        add.setLocation(350, 650);
        add.setFont(f);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                student.Add(new Student(fName.getText(), less, (Major) fmaj.getSelectedItem()));
                fName.setText("");
                pane.setText("");
                studentReg.setVisible(false);
            }
        });
        studentReg.add(add);
    }

    public static void ShowStudentRegister(Point p) {
        StudentRegister();
        studentReg.setLocation(p);
        studentReg.setVisible(true);
        LessonRegister();
    }

    static void LessonRegister() {
        Font f = new Font("Arial", Font.PLAIN, 30);
        lessonReg = new JFrame("Lesson");
        lessonReg.setSize(700, 400);
        lessonReg.setLayout(null);
        JLabel label = new JLabel("New Lesson");
        label.setSize(400, 100);
        label.setLocation(275, 0);
        label.setFont(f);
        lessonReg.add(label);

        JLabel sub = new JLabel("Subject");
        sub.setSize(400, 100);
        sub.setLocation(125, 75);
        sub.setFont(f);
        lessonReg.add(sub);

        JComboBox<Subject> fsub = new JComboBox<Subject>(subject.toArray());
        fsub.setSize(400, 50);
        fsub.setLocation(250, 100);
        fsub.setFont(f);
        lessonReg.add(fsub);

        JLabel name = new JLabel("Score");
        name.setSize(400, 100);
        name.setLocation(125, 175);
        name.setFont(f);
        lessonReg.add(name);
        JTextField fName = new JTextField();
        fName.setSize(300, 50);
        fName.setLocation(250, 200);
        fName.setFont(f);
        lessonReg.add(fName);

        JButton cancel = new JButton("Cancel");
        cancel.setSize(200, 50);
        cancel.setLocation(125, 275);
        cancel.setFont(f);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fName.setText("");

                lessonReg.setVisible(false);
            }
        });
        lessonReg.add(cancel);
        JButton add = new JButton("Add");
        add.setSize(200, 50);
        add.setLocation(350, 275);
        add.setFont(f);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    less.Add(new Lessen((Subject) (fsub.getSelectedItem()), Integer.parseInt(fName.getText())));
                    pane.append(less.Get(less.size() - 1).getLearned().getSubjectName() + "\n");
                    fName.setText("");
                } catch (Exception w) {
                    System.out.println("error 1");
                }
                name.setText("");
                lessonReg.setVisible(false);
            }
        });
        lessonReg.add(add);
    }

    public static void ShowLessonRegister(Point p) {
        lessonReg.setLocation(p);
        lessonReg.setVisible(true);
    }

    static void ShowList(String[] strs, Point p) {
        Font f = new Font("Arial", Font.PLAIN, 30);
        list = new JFrame("List");
        list.setLocation(p);
        list.setSize(500, 600);
        list.setLayout(new BoxLayout(list.getContentPane(), BoxLayout.PAGE_AXIS));
        JTextArea label = new JTextArea();
        label.setEditable(false);
        label.setFont(f);
        list.add(label);
        for (int i = 0; i < strs.length; i++) {
            label.append(strs[i] + "\n");
        }

        list.setVisible(true);
    }

    static void ShowMajorFilter(Point p) {
        Font f = new Font("Arial", Font.PLAIN, 30);
        list = new JFrame("List");
        list.setLocation(p);
        list.setSize(500, 600);
        list.setLayout(new BoxLayout(list.getContentPane(), BoxLayout.PAGE_AXIS));
        JTextArea label = new JTextArea();
        JComboBox<Major> select = new JComboBox<>(major.toArray());
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("");
                for (int i = 0; i < student.size(); i++) {
                    if (student.Get(i).getMajor().getMajorName()
                            .equals(((Major) (select.getSelectedItem())).getMajorName())) {
                        label.append(student.Get(i).toString() + "\n");
                    }
                }
            }
        });
        select.setFont(f);
        list.add(select);
        label.setEditable(false);
        label.setFont(f);
        list.add(label);

        for (int i = 0; i < student.size(); i++) {
            if (student.Get(i).getMajor().getMajorName().equals(((Major) (select.getSelectedItem())).getMajorName())) {
                label.append(student.Get(i).toString() + "\n");
            }
        }

        list.setVisible(true);
    }

    static void ShowSubjectFilter(Point p) {
        Font f = new Font("Arial", Font.PLAIN, 30);
        list = new JFrame("List");
        list.setLocation(p);
        list.setSize(500, 600);
        list.setLayout(new BoxLayout(list.getContentPane(), BoxLayout.PAGE_AXIS));
        JTextArea label = new JTextArea();
        JComboBox<Subject> select = new JComboBox<>(subject.toArray());
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("");
                for (int i = 0; i < student.size(); i++) {
                    MyChain<Lessen> chain = student.Get(i).getLesson();
                    for (int j = 0; j < chain.size(); j++) {
                        if (chain.Get(j).getLearned().getSubjectName()
                                .equals(((Subject) select.getSelectedItem()).getSubjectName())) {
                            label.append(student.Get(i).SubjectData((Subject) select.getSelectedItem()) + "\n");
                            break;
                        }
                    }
                }
            }
        });
        select.setFont(f);
        list.add(select);
        label.setEditable(false);
        label.setFont(f);
        list.add(label);

        for (int i = 0; i < student.size(); i++) {
            MyChain<Lessen> chain = student.Get(i).getLesson();
            for (int j = 0; j < chain.size(); j++) {
                System.out.println("213");
                if (chain.Get(j).getLearned().getSubjectName()
                        .equals(((Subject) select.getSelectedItem()).getSubjectName())) {
                    label.append(student.Get(i).SubjectData((Subject) select.getSelectedItem()) + "\n");
                    break;
                }
            }
            System.out.println("!23");
        }

        list.setVisible(true);
    }

}
