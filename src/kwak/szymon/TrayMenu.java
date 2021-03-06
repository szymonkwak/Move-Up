package kwak.szymon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class TrayMenu {

    static TrayIcon trayIcon;

    public TrayMenu() {
        show();
    }

    public static void show() {
        if (!SystemTray.isSupported()) {
            System.exit(0);
        }
        trayIcon = new TrayIcon(createIcon("/kwak/szymon/AppIcon.png"));
        final SystemTray tray = SystemTray.getSystemTray();
        trayIcon.setImageAutoSize(true);

        //Dodanie menu PPM do ikony w trayu
        trayIcon.setPopupMenu(createTrayPopUpMenu());
        trayIcon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                trayIcon.setToolTip("Do kolejnej przerwy " + (Main.REMAINDER_INTERVAL_MIN - Main.getActualMin()) + " min");
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                // Nie jest wspierane
                //https://docs.oracle.com/javase/7/docs/api/java/awt/TrayIcon.html#addMouseListener%28java.awt.event.MouseListener%29
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
            }
        });

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private static Image createIcon(String path) {
        URL imageURL = TrayMenu.class.getResource(path);
        return (new ImageIcon(imageURL, "icon")).getImage();
    }

    private static PopupMenu createTrayPopUpMenu() {
        PopupMenu popupMenu = new PopupMenu();

        MenuItem exit = new MenuItem("Zamknij");
        MenuItem postpone = new MenuItem("Zresetuj przerwy");

        popupMenu.add(postpone);
        popupMenu.addSeparator();
        popupMenu.add(exit);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        postpone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.hideRemainder();
                Main.timerProgressBar.stop();
                Main.timerOneHour.stop();
                Main.setActualMin(0);
                Main.startOneHourTimer();
            }
        });

        return popupMenu;
    }


}
