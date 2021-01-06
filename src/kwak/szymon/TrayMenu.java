package kwak.szymon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        trayIcon = new TrayIcon(createIcon("/kwak/szymon/AppIcon.ico"));
        final SystemTray tray = SystemTray.getSystemTray();

        //Dodanie menu PPM do ikony w trayu
        trayIcon.setPopupMenu(createTrayPopUpMenu());

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

        return popupMenu;
    }

}
