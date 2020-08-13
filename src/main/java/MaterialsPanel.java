import com.stirante.lolclient.ClientApi;
import generated.LolLootPlayerLoot;
import generated.LolLootRedeemableStatus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MaterialsPanel extends JPanel {
    private GUIHandler handler;
    private ClientApi api;
    private RetrieveCDragon dragon;
    public MaterialsPanel(GUIHandler handler, ClientApi api, RetrieveCDragon dragon){
        this.handler = handler;
        this.api = api;
        this.dragon = dragon;
        createPanel();
        setLayout(null);
    }

    public void createPanel(){
        JButton disenchantAll = new JButton("Disenchant Everything");
        disenchantAll.setBounds(20,50,120,30);
        disenchantAll.setFocusable(false);
        disenchantAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    LolLootPlayerLoot[] allPlayerLoot = api.executeGet("/lol-loot/v1/player-loot", LolLootPlayerLoot[].class);
                    for(LolLootPlayerLoot loot: allPlayerLoot) {
                        if(loot.lootId.startsWith("CHEST_128")) {
                            for (int i = 0; i < loot.count; i++) {
                                api.executePost("/lol-loot/v1/recipes/CHEST_128_open/craft", new String[]{loot.lootId});
                            }
                        }else if (loot.lootId.startsWith("CHAMPION_SKIN_RENTAL_")) {
                            for (int i = 0; i < loot.count; i++) {
                                api.executePost("/lol-loot/v1/recipes/SKIN_RENTAL_disenchant/craft", new String[]{loot.lootId});
                            }
                        }else if (loot.lootId.startsWith("CHAMPION_RENTAL_")) {
                            for (int i = 0; i < loot.count; i++) {
                                api.executePost("/lol-loot/v1/recipes/CHAMPION_RENTAL_disenchant/craft", new String[]{loot.lootId});
                            }
                        }
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        add(disenchantAll);

        JButton disenchantChampShards = new JButton("Disenchant Champions");
        disenchantChampShards.setBounds(150,50,180,30);
        disenchantChampShards.setFocusable(false);
        disenchantChampShards.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    LolLootPlayerLoot[] allPlayerLoot = api.executeGet("/lol-loot/v1/player-loot", LolLootPlayerLoot[].class);
                    for(LolLootPlayerLoot loot: allPlayerLoot) {
                        if (loot.lootId.startsWith("CHAMPION_RENTAL_")) {
                            for (int i = 0; i < loot.count; i++) {
                                api.executePost("/lol-loot/v1/recipes/CHAMPION_RENTAL_disenchant/craft", new String[]{loot.lootId});
                            }
                        }
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        add(disenchantChampShards);

        JButton disenchantSkins = new JButton("Disenchant Skins");
        disenchantSkins.setBounds(340,50,150,30);
        disenchantSkins.setFocusable(false);
        disenchantSkins.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    LolLootPlayerLoot[] allPlayerLoot = api.executeGet("/lol-loot/v1/player-loot", LolLootPlayerLoot[].class);
                    for(LolLootPlayerLoot loot: allPlayerLoot) {
                        if (loot.lootId.startsWith("CHAMPION_SKIN_RENTAL_") && loot.redeemableStatus.equals(LolLootRedeemableStatus.ALREADY_OWNED)) {
                            for (int i = 0; i < loot.count; i++) {
                                api.executePost("/lol-loot/v1/recipes/SKIN_RENTAL_disenchant/craft", new String[]{loot.lootId});
                            }
                        }
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        add(disenchantSkins);

    }

    private void allOnClick(){

    }
}

