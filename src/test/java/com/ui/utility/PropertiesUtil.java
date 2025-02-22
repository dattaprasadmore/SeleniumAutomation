package com.ui.utility;

import com.ui.constants.Env;
import com.ui.constants.FrameworkConstants;

import java.io.*;
import java.util.Properties;
import com.ui.constants.ConfigProperties;

public class PropertiesUtil {
    //Read Property file
    private PropertiesUtil(){}
    private static Properties prop = new Properties();
    private static InputStream ip;
    /*public static String readProperty(Env env, String propertyName) {
        File propertyFile = new File(System.getProperty("user.dir")+ "\\config\\"+ env+".properties");
        FileReader fileReader = null;
        Properties properties = new Properties();
        try {
            fileReader = new FileReader(propertyFile);
            properties.load(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(propertyName.toUpperCase());
    }*/
    public static Properties initProp(String envName) {
        prop = new Properties();
        //String envName = System.getProperty("env");

        System.out.println("Running with : " + envName + " Environment");

        try {
            if (envName == "null") {
                System.out.println("Environment is not given.");
                System.exit(1);
            } else {
                switch (envName.toLowerCase().trim()) {
                    case "qa":
                        ip = FrameworkConstants.QA_CONFIG_FILE_PATH;
                        break;
                    case "dev":
                        ip = FrameworkConstants.DEV_CONFIG_FILE_PATH;
                        break;
                    case "fit":
                        ip = FrameworkConstants.FIT_CONFIG_FILE_PATH;
                        break;
                    case "iat":
                        ip = FrameworkConstants.IAT_CONFIG_FILE_PATH;
                        break;
                    case "uat":
                        ip = FrameworkConstants.UAT_CONFIG_FILE_PATH;
                        break;
                    case "prod":
                        ip = FrameworkConstants.PROD_CONFIG_FILE_PATH;
                        break;
                    default:
                        System.out.println("No Env Found");
                        System.exit(0);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
            //Need logger
        }
        try {
            prop.load(ip);
        } catch (IOException e) {
            throw new RuntimeException(e);
            //Need Logger
        }
        return prop;
    }
    public static String get(ConfigProperties key) {
        return prop.getProperty(key.name().toUpperCase());
    }
}