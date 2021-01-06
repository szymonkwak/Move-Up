package kwak.szymon;

import javax.swing.*;

public class Remainder extends JFrame
{
    private JProgressBar progressBarTime;
    private JButton button1;
    private JButton button2;
    private JPanel RemainderPanel;

    public Remainder(int sizeX, int sizeY) {
        add(RemainderPanel);
        setSize(sizeX,sizeY);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null); //Wyśrodkowanie form na środku ekranu
        setUndecorated(true); //Ukrywanie Title Bar'a

    }
}
