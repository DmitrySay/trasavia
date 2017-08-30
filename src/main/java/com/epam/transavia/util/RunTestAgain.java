package com.epam.transavia.util;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.apache.log4j.Logger;

public class RunTestAgain implements IRetryAnalyzer {
    private static final Logger LOG = Logger.getLogger(RunTestAgain.class);
    private int nowCount = 0;
    private int maxCount = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (nowCount < maxCount) {
            nowCount++;
            return true;
        }
        LOG.info("Test failed twice");
        nowCount = 0;
        return false;
    }

}
