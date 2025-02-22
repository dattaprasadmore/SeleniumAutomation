package com.ui.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {
    private LoggerUtility(){

    }

    public static Logger getLogger(Class<?> claszz){
        Logger logger = null;
        if(logger==null){
            logger = LogManager.getLogger(claszz);
        }
        return logger;
    }

}
