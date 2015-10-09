package com.apppartner.androidprogrammertest.httputils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MOHAMED on 07-Oct-15.
 */
public class RequestPackage {

    private String Uri;
    private String method = "GET";
    private Map<String, String> params = new HashMap<>();

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setUri(String uri) {
        Uri = uri;
    }

    public String getUri() {
        return Uri;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public void setParams(String key, String value) {
        params.put(key, value);
    }

    public String getEncodedParams(){
        StringBuilder stringBuilder = new StringBuilder();
        String value = null;

        for (String key : params.keySet()) {
            try {
                value = URLEncoder.encode(params.get(key), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append(key + "=" + value);
        }
        return stringBuilder.toString();
    }



}
