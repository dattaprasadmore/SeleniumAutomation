package com.ui.listeners;

import com.ui.constants.Env;
import com.ui.utility.JSONUtility;
import com.ui.utility.PropertiesUtil;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import static com.ui.constants.ConfigProperties.*;

public class MyRetryAnalyzer implements IRetryAnalyzer {
    //private static final int MAXIMUM_NUMBER_OF_ATTEMPTS = Integer.parseInt(PropertiesUtil.readProperty(Env.DEV, "MAXIMUM_NUMBER_OF_ATTEMPTS"));
    //private static final int maximumAtempt = JSONUtility.readJSON(Env.QA).getMAXIMUM_NUMBER_OF_ATTEMPTS();
    private static final int maximumAtempt = Integer.parseInt(PropertiesUtil.get(MAXIMUM_NUMBER_OF_ATTEMPTS));
    private static int currentAttempt = 1;
    @Override
    public boolean retry(ITestResult result) {
        if(currentAttempt<=maximumAtempt){
            currentAttempt++;
            return true;
        }
            return false;
    }
}