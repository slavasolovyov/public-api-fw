package com.api.framework.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(TestListeners.class);

    @Override
    public void onFinish(ITestContext context) {
        logger.info("=== TEST FINISHED ===: {}", context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("=== TEST STARTED ===: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("=== TEST SUCCESS ===: {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("=== TEST FAILED===: {}", result.getName());
    }
}
