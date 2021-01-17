package kwak.szymon;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Remainder extends JFrame {

    JProgressBar progressBarTime;
    private JPanel RemainderPanel;
    private JLabel LabelMoment;
    private JLabel LabelImage;
    private ImageIcon imageIcon;

    public Remainder(int width, int height) {
        downloadAndSetImage();
        add(RemainderPanel);
        setSize(width, height);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null); //Wyśrodkowanie form na środku ekranu
        setUndecorated(true); //Ukrywanie Title Bar'a



        LabelMoment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Main.setActualMin(55);
                Main.hideRemainder();
            }
        });
    }

    public void downloadAndSetImage() {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://api.giphy.com/v1/gifs/random?api_key=mv85dnLAViSQ0RIMSIeBPW5AloBdgyv7&limit=1&rating=pg");
                    InputStreamReader reader = new InputStreamReader(url.openStream());
                    JsonObject jsonObject = new JsonParser().parse(reader).getAsJsonObject();
                    jsonObject = jsonObject.getAsJsonObject("data");
                    String giphString = jsonObject.get("fixed_height_downsampled_url").getAsString();
                    System.out.println(giphString);
                    giphString = giphString.replaceAll("media[0-9]","i");
                    URL giphUrl = new URL(giphString);
                    System.out.println(giphUrl.toString());

                    imageIcon = new ImageIcon(giphUrl);
                    LabelImage.setIcon(imageIcon);
                } catch (IOException e) {
                    e.printStackTrace();
                    imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("AppIcon.png")).getImage().getScaledInstance(-1, 400, Image.SCALE_SMOOTH));
                    LabelImage.setIcon(imageIcon);
                }
            }
        });


    }

}
