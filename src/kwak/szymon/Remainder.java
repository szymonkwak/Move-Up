package kwak.szymon;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Remainder extends JFrame {

    JProgressBar progressBarTime;
    private JPanel RemainderPanel;
    private JLabel LabelMoment;

    public Remainder(int width, int height) {
        add(RemainderPanel);
        setSize(width, height);
        //setAlwaysOnTop(true);
        setLocationRelativeTo(null); //Wyśrodkowanie form na środku ekranu
        setUndecorated(true); //Ukrywanie Title Bar'a

        LabelMoment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Main.setActualMin(55);

            }
        });
    }

}
