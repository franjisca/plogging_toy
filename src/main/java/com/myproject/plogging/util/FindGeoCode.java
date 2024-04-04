package com.myproject.plogging.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

@Component
@RequiredArgsConstructor
public class FindGeoCode {

    private final static String MAP_URL = "http://dapi.kakao.com/v2/local/search/address.json?query=";

    @Value("${api-key.kakao}")
    private String key;

    public Float[] getGeoData(String address) {

        System.out.println("key: " + key);


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

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            // 가져온 값 출력해주기
            System.out.println(response.toString());

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

}
