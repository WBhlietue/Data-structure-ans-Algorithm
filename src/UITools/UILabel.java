package UITools;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class UILabel extends UIItems {
    public JLabel label;

    public UILabel(JPanel panel, int width, int height, int x, int y, String txt) {
        label = new JLabel("<html>" + txt + "</html>", JLabel.CENTER);
        label.setFont(f);
        label.setSize(width, height);
        label.setLocation(x, y);
        panel.add(label);
    }
    public UILabel(JPanel panel, int width, int height, int x, int y, String txt, int a) {
        label = new JLabel("<html>" + txt + "</html>");
        label.setFont(f);
        label.setSize(width, height);
        label.setLocation(x, y);
        panel.add(label);
    }
    public void SetTop(){
        label.setVerticalAlignment(JLabel.TOP);
    }
    public void SetText(String str){
        label.setText("<html>" + str + "</html>");
    }
}
