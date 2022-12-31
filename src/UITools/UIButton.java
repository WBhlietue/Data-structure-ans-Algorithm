package UITools;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.*;

public class UIButton extends UIItems {
    public JButton button;

    public UIButton(JPanel panel, int width, int height, int x, int y, String txt) {
        button = new JButton(txt);
        button.setFont(f);
        button.setSize(width, height);
        button.setLocation(x, y);
        panel.add(button);
    }

    public void SetText(String str) {
        button.setText(str);
    }

    public void AddListener(IUIListener list){
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                list.OnClick();
            }
        });
    }
}
