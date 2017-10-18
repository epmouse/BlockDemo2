package org.cityu.cs.ian.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseMsgUtils {
    public static Map<String, String> getResponseMap(boolean b, String errDetails) {
        Map<String, String> backMap = new HashMap<>();
        backMap.put("status", b ? "ok" : "err");
        backMap.put("details", errDetails == null ? "" : errDetails);
        backMap.put("url", PropertiesUtil.readValue("config.properties", "currentServerUrl"));
        return backMap;
    }
}
