package kwak.szymon;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        //Pobierz wygląd okienek z systemu
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

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Remainder remainder = new Remainder(800,500);
                remainder.setVisible(true);
            }
        });
    }
}
