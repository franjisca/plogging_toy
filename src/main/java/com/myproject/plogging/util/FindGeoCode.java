package com.myproject.plogging.util;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class FindGeoCode {
    private final static String MAP_URL = "http://dapi.kakao.com/v2/local/search/address.json?query=";
     @Value("${api-key.kakao}")
    private String key;

    public Map<String, String> getGeoData(String address) {

        try{

            String encodedAddr = URLEncoder.encode(address, "UTF-8");
            URL findUrl = new URL(MAP_URL+encodedAddr);

            HttpURLConnection conn = (HttpURLConnection) findUrl.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "KakaoAK " + key);
            conn.setRequestProperty("content-type", "application/json");
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setDefaultUseCaches(false);

            Charset charset = Charset.forName("UTF-8");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));

            String readLine;
            StringBuffer response = new StringBuffer();

            while ((readLine = br.readLine()) != null) {
                response.append(readLine);
            }

            return getXyCode(response);


        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

    private Map<String, String> getXyCode(StringBuffer jsonData) throws ParseException {

        Map<String, String> xyMap = new HashMap<>();

        JSONObject obj = (JSONObject) new JSONParser().parse(String.valueOf(jsonData));

        JSONArray xyData = (JSONArray) obj.get("documents");

        JSONObject jsonObject1 =(JSONObject) xyData.get(0);

        xyMap.put("x", (String) jsonObject1.get("x"));
        xyMap.put("y", (String) jsonObject1.get("y"));

        return xyMap;
    }

}
