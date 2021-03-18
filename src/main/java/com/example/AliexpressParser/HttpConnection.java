package com.example.AliexpressParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class HttpConnection {

    public String jsonDownload(String adress) throws IOException, InterruptedException {

        URL url = new URL(adress);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        Scanner scanner = new Scanner(connection.getInputStream());
        scanner.useDelimiter("\\Z");
        String content=scanner.next();
        return content;

    }
}