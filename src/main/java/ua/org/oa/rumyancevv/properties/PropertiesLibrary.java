package ua.org.oa.rumyancevv.properties;

import ua.org.oa.rumyancevv.exceptions.PropertiesException;

import java.util.Properties;

public class PropertiesLibrary extends Properties {

    public String getLibraryProperty(String key) throws PropertiesException{
        String result = super.getProperty(key);
        if (result == null){
            throw new PropertiesException("Propertie  \"" + key + "\"  has null value");
        }
        return result;
    }
}
