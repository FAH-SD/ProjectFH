package com.tku.goldas.projectfh;

/**
 * Created by Goldas on 2015/6/11.
 */
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class icebox2_jsonparser {
    static JSONObject jObj = null;
    static String json = "";
    // constructor
    public icebox2_jsonparser() {}

    public JSONObject getJSONFromUrl(String temp_url) {
        HttpURLConnection conn = null;
        // Making HTTP request
        try {

            URL url =new URL(temp_url);
            conn = (HttpURLConnection)url.openConnection();
            //===============================

            //conn.setReadTimeout(10000);
            //conn.setConnectTimeout(15000);
            //===============================
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            reader.close();
            json = sb.toString();
        }catch (Exception e) {
            System.out.println("getJSONFromUrl Exception Error :"+e);
        }finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "getJSONFromUrl Error parsing data " + e.toString());
        }
        return jObj;
    }

    public JSONObject makeHttpRequest(String temp_url, String method, String urlParameters){
        HttpURLConnection conn = null;
        try{

            URL url =new URL(temp_url);
            conn = (HttpURLConnection)url.openConnection();


            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            conn.setRequestMethod(method);

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            conn.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));

            conn.setRequestProperty("Content-Language", "zh-tw");

            conn.setUseCaches (false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (conn.getOutputStream ());
            wr.writeBytes (urlParameters);
            wr.flush ();
            wr.close ();

            //Get Response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            StringBuffer sb = new StringBuffer();

            while((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            reader.close();
            json = sb.toString();
        }catch (Exception e) {
            System.out.println("makeHttpRequest Error:"+e.toString());
        }finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "getJSONFromUrl Error parsing data " + e.toString());
        }
        // return JSON String
        return jObj;
    }
}