package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utility {
    public static String fileName = "./Config/config.properties";

    public static Object fetchPropertyByValue(String key) throws IOException {
        FileInputStream file = new FileInputStream(fileName);
        Properties property = new Properties();
        property.load(file);
        return property.get(key);
    }
}
