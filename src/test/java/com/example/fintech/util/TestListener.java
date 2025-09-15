package com.example.fintech.util;

import io.qameta.allure.Allure;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.example.fintech.ui.BaseUiTest;

public class TestListener implements ITestListener {

    @Override public void onTestFailure(ITestResult result) {
        Object instance = result.getInstance();
        if (instance instanceof BaseUiTest) {
            BaseUiTest ui = (BaseUiTest) instance;
            byte[] shot = ui.takeScreenshot();
            Allure.addAttachment("Failure Screenshot", "image/png", new java.io.ByteArrayInputStream(shot), ".png");
        }
    }

    @Override public void onStart(ITestContext context) { }
    @Override public void onFinish(ITestContext context) { }
    @Override public void onTestStart(ITestResult result) { }
    @Override public void onTestSuccess(ITestResult result) { }
    @Override public void onTestSkipped(ITestResult result) { }
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }
    @Override public void onTestFailedWithTimeout(ITestResult result) { }
}
