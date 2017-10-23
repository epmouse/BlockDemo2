package org.cityu.cs.ian.util;

import java.io.File;

public class MyPathUtils {

    private static String getRootPath(){
     return System.getProperty("tansungWeb.root");
    }
    public static String getBlockHome(){
        String s = getRootPath() + "/blockHome";
        File file =new File(s);
        if(!file.exists()){
            file.mkdir();
        }
        return s;
    }
}
