package kwak.szymon;

import javax.swing.*;
import java.awt.*;

public class Main {

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

    //Pokaż remainder
    static void showRemainder(Remainder remainder) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                remainder.setVisible(true);
                //Wyzeruj i wystartuj progress bar
            }
        });
    }

    static void hideRemainder(Remainder remainder){
        remainder.setVisible(false);
    }

    public static void main(String[] args) {

        TrayMenu trayMenu = new TrayMenu();

        getSystemLookAndFeel();
        Remainder remainder = new Remainder(getScreenWidth(), getScreenHeight());
        showRemainder(remainder);
        hideRemainder(remainder);


    }
}
