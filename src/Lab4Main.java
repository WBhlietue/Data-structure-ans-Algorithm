import Lab4.MyHash;
import UITools.*;
import java.util.*;

public class Lab4Main extends UI {

    MyHash hash;
    UILabel label;

    @Override
    protected void MainUI() {
        UIInputLabel ilab = new UIInputLabel(panel, 100, 50, 30, 200, "new Hashtable Size");
        label = new UILabel(panel, 500, 500, 50, 100, "null", 0);
        UIButton btn = new UIButton(panel, 100, 30, 300, 50, "create");
        btn.AddListener(new IUIListener() {
            @Override
            public void OnClick() {
                int a = Integer.parseInt(ilab.GetInputText());
                hash = new MyHash(a);
                UpdateLabel();
            }
        });
        new UILabel(panel, 50, 50, 550, 10, "key");
        new UILabel(panel, 50, 50, 700, 10, "value");
        UITwoInputBtn put = new UITwoInputBtn(panel, 50, 300, 500, 50, "Put", 0);
        put.AddListener(new IUIListener() {
            @Override
            public void OnClick() {
                try {
                    hash.put(put.GetInput1Text(), put.GetInput2Text());
                } catch (Exception e) {
                }
                UpdateLabel();
            }
        });
        new UILabel(panel, 50, 50, 550, 110, "key");
        new UILabel(panel, 50, 50, 700, 110, "value");
        UITwoInputBtn updateValue = new UITwoInputBtn(panel, 50, 300, 500, 150, "Update Value", 0);
        updateValue.AddListener(new IUIListener() {
            @Override
            public void OnClick() {
                hash.UpdateElement(updateValue.GetInput1Text(), updateValue.GetInput2Text());
                UpdateLabel();

            }
        });
        new UILabel(panel, 100, 50, 530, 210, "old key");
        new UILabel(panel, 100, 50, 680, 210, "new key");
        UITwoInputBtn updateKey = new UITwoInputBtn(panel, 50, 300, 500, 250, "Update Value", 0);
        updateKey.AddListener(new IUIListener() {
            @Override
            public void OnClick() {
                hash.UpdateKey(updateKey.GetInput1Text(), updateKey.GetInput2Text());
                UpdateLabel();
            }
        });
        UIInputLabel delete = new UIInputLabel(panel, 500, 350, 30, 200, "key");
        UIButton del = new UIButton(panel, 100, 30, 700, 350, "delete");
        del.AddListener(new IUIListener() {
            @Override
            public void OnClick() {
                hash.Delete(delete.GetInputText());
                UpdateLabel();
            }
        });
    }

    void UpdateLabel() {
        label.SetText(hash.toString("<br>"));
    }

    public static void main(String[] args) {
        new Lab4Main().Show("Title");
    }
}
