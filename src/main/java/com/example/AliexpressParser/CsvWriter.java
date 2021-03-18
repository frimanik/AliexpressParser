package com.example.AliexpressParser;

import java.io.*;
import java.util.ArrayList;

public class CsvWriter {

    public static void write() throws IOException, InterruptedException {
        ArrayList<ProductEntity>entities = new ArrayList<>();

        String url = "https://gpsfront.aliexpress.com/getRecommendingResults.do?widget_id=5547572&limit=50";
        String postback ="";

        int offset=0;
        JToken token;

        HttpConnection content = new HttpConnection();

        while (entities.size()<100) {
            token = new JToken();
            token.write(content.jsonDownload(url+"&offset="+offset+"&postback="+postback), 0);
            Object results = token.getField("results");
            EntityFiller.fill(entities, token);
            postback = token.getField("postback").toString();
            offset+=50;
        }
            CsvWriter.writeToCSV(entities);
    }

    private static void writeToCSV(ArrayList<ProductEntity>entities) {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("products.csv"), "UTF-8"));

            bw.write(ProductEntity.headerRow());

            for (ProductEntity entity : entities)
            {
                bw.newLine();
                bw.write(entity.toCsvRow());
            }

            bw.flush();
            bw.close();
        } catch (IOException ignored) {}
    }
}
