import java.io.*;
import java.net.URL;

public class RetrieveCDragon {
    private static String globalPath = "/global/default/";
    private String webAddress = "https://raw.communitydragon.org/latest/plugins/";
    private static String fe = "rcp-fe-";
    private static String be = "rcp-be-";

    public RetrieveCDragon(){
    }

    public static String createCorrectImagePath(String lcuURl){
        String savePath = "";
        if(lcuURl.startsWith("/fe/")){
            String folderLocation = lcuURl.substring(4,12);
            String finalDestination =  lcuURl.substring(13);
            savePath = fe + folderLocation + globalPath + finalDestination;
        }else{
            String plugin = lcuURl.substring(1,lcuURl.indexOf("/",1));
            String finalDestination = lcuURl.substring(22);
            savePath = plugin + globalPath + finalDestination;

        }

        savePath = savePath.toLowerCase();
        return savePath;
    }

    public static String createCorrectFilePath(String CDragonURL){
        String savePath = "";
        if(CDragonURL.startsWith("/fe/")){
            String plugin = CDragonURL.substring(4);
            String path = fe + plugin;
            savePath = path;
        }else{
            String plugin = CDragonURL.substring(1);
            String path = be + plugin;
            savePath = path;
        }

        savePath = savePath.toLowerCase();
        return savePath;
    }

    public String returnFile(String CDragonURL) throws IOException {

        if(CDragonURL.startsWith("/fe/")){
            String plugin = CDragonURL.substring(4);
            String path = webAddress + fe + plugin;
            String savePath = fe + plugin;
            savePath = savePath.toLowerCase();
            createFile(savePath, path);
            return savePath;
        }else{
            String plugin = CDragonURL.substring(1);
            String path = webAddress + be + plugin;
            String savePath = be + plugin;
            savePath = savePath.toLowerCase();
            createFile(savePath, path);
            return savePath;
        }

        //convert CDragonURL to readable url for CDragon
    }

    public String returnImage(String lcuURL) throws IOException{
        String path = "";
        String savePath = "";
        if(lcuURL.startsWith("/fe/")){
            String folderLocation = lcuURL.substring(4,12);
            String finalDestination =  lcuURL.substring(13);
            path = webAddress + fe + folderLocation + globalPath + finalDestination;
            savePath = fe + folderLocation + globalPath + finalDestination;
        }else{
            String plugin = lcuURL.substring(1,lcuURL.indexOf("/",1));
            String finalDestination = lcuURL.substring(22);
            path = webAddress + be + plugin + globalPath + finalDestination;
            path = path.toLowerCase();
            savePath = plugin + globalPath + finalDestination;

        }

        savePath = savePath.toLowerCase();
        createFile(savePath, path);
        return savePath;
    }


    private void createFile(String data, String urlType) throws IOException {
        System.setProperty("http.agent", "Chrome");
        if(data.contains("/")){
            //check if dir exists
            File dir = new File("src/main/resources/"+data.substring(0,data.lastIndexOf("/")));
            if(!dir.exists()){
                boolean success = dir.mkdirs();
            }
            //check if file exists
            File file = new File("src/main/resources/"+data);
            if(!file.exists()){
                System.out.println("Downloaded File");
                URL url = new URL(urlType);
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream("src/main/resources/"+data);
                byte[] b = new byte[2048];
                int length;
                while ((length = is.read(b)) != -1) {
                    os.write(b, 0, length);
                }
                is.close();
                os.close();
            }

        }

    }
}
