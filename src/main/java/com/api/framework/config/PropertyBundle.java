package com.api.framework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyBundle {

    private static final Logger logger = LoggerFactory.getLogger(PropertyBundle.class);
    private final Properties properties;

    public PropertyBundle(String propertiesFilePath) {
        properties = new Properties();
        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(propertiesFilePath)) {
            if (resourceAsStream != null) {
                properties.load(resourceAsStream);
            } else {
                logger.error("Properties file not found: {}", propertiesFilePath);
            }
        } catch (IOException e) {
            logger.error("Unable to load properties file: {}", propertiesFilePath, e);
        }
    }

    public String getProperty(String propertyKey) {
        if (propertyKey != null && !propertyKey.isEmpty()) {
            return properties.getProperty(propertyKey);
        }
        return null;
    }

}
