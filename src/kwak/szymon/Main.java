package kwak.szymon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    static int actualMilisec;
    static Remainder remainder1;
    static TrayMenu trayMenu;
    static Timer timer1, timer2;
    static ActionListener listener1, listener2;

    static final int REMAINDER_INTERVAL = 10_000; //120 bo 1 Godzina = 3600_000 / 30_000
    static final int TIMER_DELAY = 1_000; //30_000
    static final int BREAK_DURATION_SEC = 60;

    public static void main(String[] args) {

        //        getSystemLookAndFeel();
        trayMenu = new TrayMenu();
        remainder1 = new Remainder(getScreenWidth() - 150, getScreenHeight() - 150);


        //remainder1.progressBarTime.setValue(2000);
        //showRemainder(remainder1);

        startOneHourTimer();
    }

    //Pobierz wygląd okienek z systemu
    static void getSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    //Ustal rozdzielczość ekranu
    static int getScreenHeight() {
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }

    static int getScreenWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }

    static void showRemainder(Remainder remainder) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                startProgressBar(remainder.progressBarTime);
                remainder.setVisible(true);
            }
        });
    }

    static void hideRemainder(Remainder remainder) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                remainder.setVisible(false);
            }
        });
    }

    //Startuje Timer i po godzinie wyświetla przypomnienie o przerwie
    static void startOneHourTimer() {
        listener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                actualMilisec = actualMilisec + TIMER_DELAY;
                if (actualMilisec >= REMAINDER_INTERVAL) {
                   showRemainder(remainder1);
                }
            }
        };
        timer1 = new Timer(TIMER_DELAY, listener1);
        timer1.start();
    }

    static void startProgressBar(JProgressBar progressBar) {
        progressBar.setValue(BREAK_DURATION_SEC);
        listener2 = new ActionListener() {
            int counter = BREAK_DURATION_SEC;

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                counter--;
                progressBar.setValue(counter);
                if (counter < 1) {
                    hideRemainder(remainder1);
                    actualMilisec = 0;
                    startOneHourTimer();
                }
            }
        };
        timer2 = new Timer(1000,listener2);
        timer2.start();
    }

}
