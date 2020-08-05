import com.stirante.lolclient.ClientApi;
import generated.LolLootPlayerLoot;
import generated.LolSummonerSummoner;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HeaderPanel extends JPanel {
    private ClientApi api;
    private LolLootPlayerLoot BE;
    private LolLootPlayerLoot OE;
    private LolLootPlayerLoot keys;
    private LolLootPlayerLoot gemstones;
    private LolSummonerSummoner summoner;
    private RetrieveCDragon dragon;

    public HeaderPanel(GUIHandler guiHandler, ClientApi api, RetrieveCDragon dragon) {
        this.api = api;
        this.dragon = dragon;
        getCurrencys();
        getTrayIcons();
        createHeaderPanel();
    }

    public void getTrayIcons(){
        try {
            dragon.returnImage("/fe/lol-loot/assets/tray_icons/currency_cosmetic.png");
            dragon.returnImage("/fe/lol-loot/assets/tray_icons/currency_champion.png");
            dragon.returnImage("/fe/lol-loot/assets/tray_icons/material_rare.png");
            dragon.returnImage("/fe/lol-loot/assets/tray_icons/material_key.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getCurrencys(){
        try {
            summoner = api.executeGet("/lol-summoner/v1/current-summoner", LolSummonerSummoner.class);
            LolLootPlayerLoot[] allPlayerLoot = api.executeGet("/lol-loot/v1/player-loot", LolLootPlayerLoot[].class);
            for(LolLootPlayerLoot loot: allPlayerLoot){
                if(loot.asset.equals("currency_cosmetic")){
                    OE = loot;
                }else if (loot.asset.equals("currency_champion")) {
                    BE = loot;
                }else if(loot.asset.equals("material_rare")){
                    gemstones = loot;
                }else if (loot.lootId.equals("MATERIAL_key")){
                    keys = loot;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createHeaderPanel(){
        setLayout(new BorderLayout());


        JLabel titleText = new JLabel(summoner.displayName);
        Font f = new Font(titleText.getFont().getName(),titleText.getFont().getStyle(), 25);
        titleText.setFont(f);
        titleText.setBorder(new EmptyBorder(20,35,20,10));
        add(titleText,BorderLayout.WEST);
        //setBackground(Color.blue);

        JPanel currencys = new JPanel(new GridBagLayout());
        //currencys.setBackground(Color.RED);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=4;
        c.gridy=0;
        c.gridheight=2;
        File oefile = new File("src/main/resources/"+ RetrieveCDragon.createCorrectImagePath("/fe/lol-loot/assets/tray_icons/currency_cosmetic.png"));
        BufferedImage oeimage = null;
        try {
            oeimage = ImageIO.read(oefile);
            //image = ImageResize.resize(image,26, 26);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel oeImg = new JLabel(new ImageIcon(oeimage));
        currencys.add(oeImg,c);

        c.gridx=4;
        c.gridy=2;
        c.gridheight=0;
        JLabel oe = new JLabel(String.valueOf(OE.count));
//        Font foe = new Font(oe.getFont().getName(),oe.getFont().getStyle(), 15);
//        oe.setFont(foe);
//        oe.setBounds(0,0,(int)oe.getPreferredSize().getWidth(),(int)oe.getPreferredSize().getHeight());
        currencys.add(oe,c);

        c.gridx=3;
        c.gridy=0;
        c.gridheight=4;
        currencys.add( Box.createHorizontalStrut( 50 ) , c );


        c.gridx=3;
        c.gridy=0;
        c.gridheight=2;
        File befile = new File("src/main/resources/"+ RetrieveCDragon.createCorrectImagePath("/fe/lol-loot/assets/tray_icons/currency_champion.png"));
        BufferedImage beimage = null;
        try {
            beimage = ImageIO.read(befile);
            //image = ImageResize.resize(image,26, 26);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel beImg = new JLabel(new ImageIcon(beimage));
        currencys.add(beImg,c);

        c.gridx=3;
        c.gridy=2;
        c.gridheight=0;
        JLabel be = new JLabel(String.valueOf(BE.count));
        currencys.add(be,c);

        c.gridx=5;
        c.gridy=0;
        c.gridheight=3;
        currencys.add( Box.createHorizontalStrut( 10 ) , c );


        c.gridx=6;
        c.gridy=0;
        c.gridheight=2;
        File gsfile = new File("src/main/resources/"+ RetrieveCDragon.createCorrectImagePath("/fe/lol-loot/assets/tray_icons/material_rare.png"));
        BufferedImage gsimage = null;
        try {
            gsimage = ImageIO.read(gsfile);
            //image = ImageResize.resize(image,26, 26);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel gsImg = new JLabel(new ImageIcon(gsimage));
        currencys.add(gsImg,c);
        c.gridx=6;
        c.gridy=2;
        c.gridheight=0;
        if(gemstones==null){
            JLabel gs = new JLabel("0");
            currencys.add(gs,c);
        }else{
            JLabel gs = new JLabel(String.valueOf(keys.count));
            currencys.add(gs,c);
        }

        c.gridx=7;
        c.gridy=0;
        c.gridheight=3;
        currencys.add( Box.createHorizontalStrut( 10 ) , c );


        c.gridx=8;
        c.gridy=0;
        c.gridheight=2;
        File kfile = new File("src/main/resources/"+ RetrieveCDragon.createCorrectImagePath("/fe/lol-loot/assets/tray_icons/material_key.png"));
        BufferedImage kimage = null;
        try {
            kimage = ImageIO.read(kfile);
            //image = ImageResize.resize(image,26, 26);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel kImg = new JLabel(new ImageIcon(kimage));
        currencys.add(kImg,c);
        c.gridx=8;
        c.gridy=2;
        c.gridheight=0;
        if(keys==null){
            JLabel key = new JLabel("0");
            currencys.add(key,c);
        }else{
            JLabel key = new JLabel(String.valueOf(keys.count));
            currencys.add(key,c);
        }
        currencys.setBorder(new EmptyBorder(0,0,0,30));
        add(currencys, BorderLayout.EAST);

    }

}
