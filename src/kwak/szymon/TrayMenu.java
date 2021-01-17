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

        //Dodanie menu PPM do ikony w trayu
        trayIcon.setPopupMenu(createTrayPopUpMenu());
        trayIcon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                //TODO teoretycznie działa, ale trzeba klikać na ikonkę
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
                //TODO dlaczego tutaj nie działa?
                trayIcon.setToolTip("test");

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
                Main.setActualMin(0);
                Main.hideRemainder();
            }
        });

        return popupMenu;
    }


}
