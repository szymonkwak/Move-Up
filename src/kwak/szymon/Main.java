package kwak.szymon;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private static int actualMilisec;
    static Remainder remainder1;
    static TrayMenu trayMenu;
    static Timer timerOneHour, timerProgressBar;
    static ActionListener listener1, listener2;

    static final int REMAINDER_INTERVAL_MIN = 60; //60 bo 1 Godzina = 3600_000 / 60_000
    static final int TIMER_DELAY = 60_000; //60_000 bo aktualizuję czas co 1min
    static final int BREAK_DURATION_SEC = 60;

    public static void main(String[] args) {

        setLookAndFeelFlatFight();
        trayMenu = new TrayMenu();
        remainder1 = new Remainder(getScreenWidth() - 150, getScreenHeight() - 150);
        timerProgressBar = new Timer(0,null); //Potrzebuję zainicjalizować oba timery, żeby nie dostać nullPointerException jeżeli zatrzymam je zanim by wystartowały

        startOneHourTimer();
    }

    //Pobierz wygląd okienek z systemu
    static void setLookAndFeelFlatFight() {
//  https://www.formdev.com/flatlaf/
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
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

    public static void setActualMin(int actualMin) {
        Main.actualMilisec = actualMin * 60_000;
    }

    public static int getActualMin() {
        return actualMilisec / 60_000;
    }

    static void showRemainder() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                startProgressBar(remainder1.progressBarTime);
                remainder1.setVisible(true);
            }
        });
    }

    static void hideRemainder() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                remainder1.setVisible(false);
            }
        });
    }

    //Startuje Timer i po godzinie wyświetla przypomnienie o przerwie
    static void startOneHourTimer() {
        listener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                actualMilisec = actualMilisec + TIMER_DELAY;
                // System.out.println("oneHourTimer " + getActualMin());
                if (actualMilisec >= (REMAINDER_INTERVAL_MIN * 60_000)) {
                    remainder1.downloadAndSetImage();
                    showRemainder();
                }
            }
        };
        timerOneHour = new Timer(TIMER_DELAY,listener1);
        timerOneHour.start();
    }

    static void startProgressBar(JProgressBar progressBar) {
        progressBar.setValue(BREAK_DURATION_SEC * 40);
        listener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                progressBar.setValue(progressBar.getValue() - 1);
                // System.out.println("progressBar " + progressBar.getValue());
                if (progressBar.getValue() < 1) {
                    hideRemainder();
                    timerProgressBar.stop();
                    timerOneHour.stop();
                    setActualMin(0);
                    startOneHourTimer();
                }

            }
        };
        timerProgressBar = new Timer(25,listener2);
        timerProgressBar.start();
    }


}
