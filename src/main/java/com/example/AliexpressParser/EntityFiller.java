package com.example.AliexpressParser;

import java.util.ArrayList;

public class EntityFiller {

    public static void fill(ArrayList<ProductEntity>entities, JToken token) {
        ArrayList<JToken> innerResult = (ArrayList<JToken>)token.getField("results");

        for(JToken el: innerResult) {
            ProductEntity entity = new ProductEntity();
            entity.loadFromJson(el);
            entities.add(entity);
        }
    }
}

