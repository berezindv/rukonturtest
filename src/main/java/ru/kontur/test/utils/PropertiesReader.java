package ru.kontur.test.utils;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;

/**
 * Class for working with config files (*.properties files)
 * as the PropertiesReader objects.
 */
public class PropertiesReader {

    private static PropertiesReader propertiesReader = null;
    private static HashMap<String, String> propertiesMap = new HashMap<>();
    private HashSet<String> readRecords = new HashSet<>();


    private PropertiesReader() {}

    /**
     * Create a new PropertiesReader instance or return an existing one
     * @return PropertiesReader object
     */
    public static PropertiesReader getInstance() {
        if (propertiesReader == null) {
            propertiesReader = new PropertiesReader();
        }

        return propertiesReader;
    }

    /**
     * Get property value
     * @param aKey  property key as String
     * @return property value as String
     */
    public String getProperty(@Nonnull String aKey) {
        return propertiesMap.get(aKey);
    }

    /**
     * Read properties from file <aPathname>
     * @param aPathname path to the file
     * @return true if read is successful
     */
    public boolean readFromFile(@Nonnull String aPathname) {
        if (readRecords.contains(aPathname)) {
            return true;
        }

        try {
            File file = new File(aPathname);
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            try {
                properties.load(fileInput);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                fileInput.close();
            }

            Enumeration enuKeys = properties.keys();

            while (enuKeys.hasMoreElements()) {
                String key = (String) enuKeys.nextElement();
                String value = properties.getProperty(key);
                propertiesMap.put(key,value);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        readRecords.add(aPathname);
        return true;
    }
}
