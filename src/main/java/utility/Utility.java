package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Properties;
public class Utility {

    public static String path = System.getProperty("user.dir");
    public static Properties getProperties(){
        Properties prop = new Properties();
        try {
            InputStream inputStream = new FileInputStream(path + File.separator +"config.properties");
            prop.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }
    public static String decode(String key){
        byte[] decodeBytes = Base64.getDecoder().decode(key);
        return new String(decodeBytes);
    }

    public static void main(String[] args) {
//    //     we proceed in decoding
//        String toEncode = "savcKMWcbFXKst8Ysv6H";
//        String encoded = Base64.getEncoder().encodeToString(toEncode.getBytes());
//        System.out.println(encoded);
//        System.out.println(decode(encoded));

        String sep = File.separator;
        String path = System.getProperty("user.dir");
        System.out.println(path);
    }
}




/*******************************************************************************************/
                                       //first version
/****************************************************************************************/
//public class Utility {
//    public static Properties getProperties(){// THIS
//        Properties prop = new Properties();
//       String filePropertiesPath= "C:\\Users\\Malika Refsi\\IdeaProjects\\AutomationFrameWork\\config.properties";
//        try {
//            InputStream inputStream = new FileInputStream(filePropertiesPath);
//            prop.load(inputStream);//load the content of the file in the object prop
//            inputStream.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return prop;
//    }
//    public static String decode(String key){
//        byte[] decodeBytes = Base64.getDecoder().decode(key);
//        return new String(decodeBytes);
//    }
//
//    public static void main(String[] args) { //at this level we should encode each of the credentials we have before
////        we proceed in decoding
//        String toEncode = "savcKMWcbFXKst8Ysv6H";
//        String encoded = Base64.getEncoder().encodeToString(toEncode.getBytes());
//        System.out.println(encoded);
//
//        System.out.println(decode(encoded));
//    }
//
//}
