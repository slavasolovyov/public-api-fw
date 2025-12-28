package com.api.framework.base;

import com.api.framework.enums.Roles;
import com.api.framework.utils.TokenStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;


public abstract class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeSuite
    public void beforeSuite() {
        logger.debug("Storing SUPERVISOR user token");
        TokenStorage.setToken(Roles.SUPERVISOR, "supervisor");
        logger.debug("Storing ADMIN user token");
        TokenStorage.setToken(Roles.ADMIN, "admin");
    }
}

