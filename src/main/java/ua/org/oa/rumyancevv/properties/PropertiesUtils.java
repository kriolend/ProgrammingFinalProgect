package ua.org.oa.rumyancevv.properties;

import ua.org.oa.rumyancevv.exceptions.PropertiesException;

import java.io.*;

public class PropertiesUtils {
    private static PropertiesLibrary properties = null;

    public static void readProperties(String propertiesFilePath) throws PropertiesException {
        if (properties == null) {
            PropertiesLibrary library = new PropertiesLibrary();
            try (InputStream in = new FileInputStream(new File(propertiesFilePath))) {
                library.load(in);
                properties = library;
            } catch (FileNotFoundException e) {
                throw new PropertiesException("Properties file not found");
            } catch (IOException e) {
                throw new PropertiesException("Error reading properties file");
            }
        }
    }

    public static PropertiesLibrary getProperties() throws PropertiesException {
        return properties;
    }
}
