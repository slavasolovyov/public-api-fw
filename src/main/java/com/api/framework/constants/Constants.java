package com.api.framework.constants;

import com.api.framework.config.PropertyBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

    private static final Logger logger = LoggerFactory.getLogger(Constants.class);
    private static final PropertyBundle propertyBundle = new PropertyBundle("application.properties");
    public static final String BASE_URL = getBaseUrl();

    private static String getBaseUrl() {
        String baseUrl = propertyBundle.getProperty("base.url");
        if (baseUrl == null || baseUrl.isEmpty()) {
            String errorMsg = "base.url property is not set or is empty. Please check application.properties file.";
            logger.error(errorMsg);
            throw new IllegalStateException(errorMsg);
        }
        return baseUrl;
    }
}
