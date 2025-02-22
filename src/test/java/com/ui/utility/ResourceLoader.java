package com.ui.utility;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;

public class ResourceLoader {
    private static InputStream stream;
    public static InputStream getResources(String fileName) {
        Properties prop = new Properties();
        InputStream stream = null;
        try{
            stream  = ResourceLoader.class.getClassLoader().getResourceAsStream(fileName);
            //stream  = ResourceLoader.class.getResourceAsStream(fileName);
            if(Objects.nonNull(stream)){
                return stream;
            }
            return Files.newInputStream(Path.of(fileName));
        }catch (Exception e){
            e.printStackTrace();
        }
        return stream;
    }
}