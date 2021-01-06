package kwak.szymon;

import javax.swing.*;

public class Remainder extends JFrame {

    private JProgressBar progressBarTime;
    private JPanel RemainderPanel;
    private JButton btnPostpone;

    public Remainder(int width, int height) {
        add(RemainderPanel);
        setSize(width - 150, height - 150);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null); //Wyśrodkowanie form na środku ekranu
        setUndecorated(true); //Ukrywanie Title Bar'a

    }


}
