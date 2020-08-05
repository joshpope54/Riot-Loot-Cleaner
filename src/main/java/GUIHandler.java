import com.stirante.lolclient.ClientApi;

import javax.imageio.ImageIO;
import javax.imageio.ImageTranscoder;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GUIHandler extends JFrame {
    public GUIHandler(ClientApi api, RetrieveCDragon dragon){
        setUndecorated(true);
        setSize(510,240);
        setBackground(new Color(255,255,255));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        UpperBar topBar = new UpperBar(this);
        add(topBar, BorderLayout.PAGE_START);
        HeaderPanel header = new HeaderPanel(this, api, dragon);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(header, BorderLayout.NORTH);
        panel.setBackground(Color.RED);
        MaterialsPanel materialsPanel = new MaterialsPanel(this, api, dragon);

        panel.add(materialsPanel);
        add(panel);

    }
}
