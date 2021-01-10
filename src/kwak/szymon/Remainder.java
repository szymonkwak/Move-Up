package kwak.szymon;

import javax.swing.*;

public class Remainder extends JFrame {

    JProgressBar progressBarTime;
    private JPanel RemainderPanel;

    public Remainder(int width, int height) {
        add(RemainderPanel);
        setSize(width, height);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null); //Wyśrodkowanie form na środku ekranu
        setUndecorated(true); //Ukrywanie Title Bar'a

    }

    void showRemainder() {
    }

    void hideRemainder() {
    }

    void postPoneRemainder() {
    }


}
