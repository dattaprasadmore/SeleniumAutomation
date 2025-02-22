package com.ui.constants;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.ui.utility.PropertiesUtil;
import com.ui.utility.ResourceLoader;

public class FrameworkConstants {
    private FrameworkConstants() {}

    //private static final String RESOURCESPATH = System.getProperty("user.dir")+"/src/test/resources";
    public static final InputStream DEV_CONFIG_FILE_PATH = ResourceLoader.getResources("config/DEV.properties");
    public static final InputStream QA_CONFIG_FILE_PATH = ResourceLoader.getResources("config/QA.properties");
    public static final InputStream FIT_CONFIG_FILE_PATH = ResourceLoader.getResources("config/FIT.properties");
    public static final InputStream IAT_CONFIG_FILE_PATH = ResourceLoader.getResources("config/iat.properties");
    public static final InputStream UAT_CONFIG_FILE_PATH = ResourceLoader.getResources("config/uat.properties");
    public static final InputStream PROD_CONFIG_FILE_PATH = ResourceLoader.getResources("config/prod.properties");
    private static final String EXTENT_REPORT_FILE_PATH = System.getProperty("user.dir") + "\\ExtentReports\\";
    private static String extentReportFilePath = "";

    public static String getExtentReportFilePath(){
        if(extentReportFilePath.isEmpty()){
            extentReportFilePath =createReportPath();
        }
        return extentReportFilePath;
    }

    public static String createReportPath(){
        if(PropertiesUtil.get(ConfigProperties.OVERRIDEREPORTS).equalsIgnoreCase("no")){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_mm_ss");
            LocalDateTime localDateTime = LocalDateTime.now();
            String formattedTime = dateTimeFormatter.format(localDateTime);
            return EXTENT_REPORT_FILE_PATH + formattedTime + "_Report" +".html";
        }else{
            return EXTENT_REPORT_FILE_PATH + "index.html";
        }
    }
}