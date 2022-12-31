package UITools;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class UI {
    protected JFrame frame;
    protected JPanel panel;

    public void Show(String title) {
        frame = new JFrame(title);
        // frame.setLayout(null);
        frame.setTitle(title);
        panel = new JPanel();
        panel.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.add(panel);
        MainUI();
        frame.setVisible(true);
        Start();
    }

    protected void MainUI() {

    }

    protected void Start() {

    }

    public void ChangeSize(int width, int height) {
        frame.setSize(width, height);
    }

    public void ChangePosition(int x, int y) {
        frame.setLocation(x, y);
    }

}
