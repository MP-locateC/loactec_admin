package com.example.admin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OnHttpConnection {

  String EXCEPTION_ERROR = "Error";

  public String GetFunction(String mUrl) {
    try {

      URL url = new URL(mUrl);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setConnectTimeout(15000);
      conn.setReadTimeout(10000);
      conn.setDoInput(true);
      conn.setDoOutput(true);

      InputStream is = conn.getInputStream();
      StringBuilder sb = new StringBuilder();
      BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
      String result;
      while ((result = br.readLine()) != null) {
        sb.append(result + '\n');
      }


      return "result";
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }
}
