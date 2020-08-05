import com.stirante.lolclient.ClientApi;
import com.stirante.lolclient.ClientConnectionListener;
import generated.LolLootPlayerLoot;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

public class InventoryManager {
    public static String readFile(String filein){
        try {
            //note current line is temporary for testing purposes
            BufferedReader file = new BufferedReader(new FileReader(filein));
            StringBuilder content = new StringBuilder();
            String temp;
            while ((temp = file.readLine()) != null) {
                content.append(temp);
            }
            file.close();
            return content.toString();

        } catch (Exception ignored) {

        }
        return null;
    }

    public static void main(String[] args) {
        ClientApi api = new ClientApi();
        ClientApi.setDisableEndpointWarnings(true);
        api.addClientConnectionListener(new ClientConnectionListener() {
            @Override
            public void onClientConnected() {
                RetrieveCDragon dragon = new RetrieveCDragon();
                GUIHandler handler = new GUIHandler(api, dragon);
                handler.setVisible(true);
            }

            @Override
            public void onClientDisconnected() {

            }
        });


    }

}

//        import com.stirante.lolclient.ClientWebSocket;
//
//        import java.io.BufferedReader;
//        import java.io.InputStreamReader;
//
//public class InventoryManager {
//
//    private static ClientWebSocket socket;
//
//    /**
//     * Simple example showing how to receive websocket events from client
//     */
//    public static void main(String[] args) throws Exception {
//        //Initialize API
//        ClientApi api = new ClientApi();
//        api.addClientConnectionListener(new ClientConnectionListener() {
//            @Override
//            public void onClientConnected() {
//                System.out.println("Client connected");
//                try {
//                    //open web socket
//                    socket = api.openWebSocket();
//                    //add event handler, which prints every received event
//                    socket.setSocketListener(new ClientWebSocket.SocketListener() {
//                        @Override
//                        public void onEvent(ClientWebSocket.Event event) {
//                            System.out.println(event);
//                        }
//
//                        @Override
//                        public void onClose(int code, String reason) {
//                            System.out.println("Socket closed, reason: " + reason);
//                        }
//                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onClientDisconnected() {
//                System.out.println("Client disconnected");
//                socket.close();
//            }
//        });
//        //close socket when user enters something into console
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        reader.readLine();
//        api.stop();
//        socket.close();
//    }
//
//}