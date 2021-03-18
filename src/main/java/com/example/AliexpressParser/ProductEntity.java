package com.example.AliexpressParser;

import java.util.StringJoiner;

public class ProductEntity {

    long productId;
    long sellerId;
    long promotionId;
    long startTime;
    long endTime;
    long itemEvalTotalNum;
    long gmtCreate;

    String oriMinPrice;
    String oriMaxPrice;
    String productTitle;
    String minPrice;
    String maxPrice;
    String discount;
    String totalStock;
    String stock;
    String orders;
    String productImage;
    String productDetailUrl;
    String totalTranpro3;
    String productPositiveRate;
    String productAverageStar;

    boolean soldout;

    public void loadFromJson(JToken token){
        productId = token.getFieldAsLong("productId");
        sellerId = token.getFieldAsLong("sellerId");
        promotionId = token.getFieldAsLong("promotionId");
        startTime = token.getFieldAsLong("startTime");
        endTime = token.getFieldAsLong("endTime");
        itemEvalTotalNum = token.getFieldAsLong("itemEvalTotalNum");
        gmtCreate = token.getFieldAsLong("gmtCreate");

        oriMinPrice = token.getFieldAsString("oriMinPrice");
        oriMaxPrice = token.getFieldAsString("oriMaxPrice");
        productTitle = token.getFieldAsString("productTitle");
        minPrice = token.getFieldAsString("minPrice");
        maxPrice = token.getFieldAsString("maxPrice");
        discount = token.getFieldAsString("discount");
        totalStock = token.getFieldAsString("totalStock");
        stock = token.getFieldAsString("stock");
        orders = token.getFieldAsString("orders");
        productImage = token.getFieldAsString("productImage");
        productDetailUrl = token.getFieldAsString("productDetailUrl");
        totalTranpro3 = token.getFieldAsString("totalTranpro3");
        productPositiveRate = token.getFieldAsString("productPositiveRate");
        productAverageStar = token.getFieldAsString("productAverageStar");

        soldout = token.getFieldAsBool("soldout");
    }

    public static String headerRow(){
        return "ProductId, SellerId, PromotionId, StartTime, EndTime, ItemEvalTotalNum, GmtCreate, OriMinPrice, OriMaxPrice," +
                "ProductTitle, MinPrice, MaxPrice, Discount, TotalStock, Stock, Orders, ProductImage, ProductDetailUrl," +
                "TotalTranpro3, ProductPositiveRate, ProductAverageStar, SoldOut";
    }

    public String toCsvRow() {
        StringJoiner joiner = new StringJoiner(",       ");
        joiner.add(String.valueOf(productId));
        joiner.add(String.valueOf(sellerId));
        joiner.add(String.valueOf(promotionId));
        joiner.add(String.valueOf(startTime));
        joiner.add(String.valueOf(endTime));
        joiner.add(String.valueOf(itemEvalTotalNum));
        joiner.add(String.valueOf(gmtCreate));

        joiner.add(oriMinPrice);
        joiner.add(oriMaxPrice);
        joiner.add(productTitle);
        joiner.add(minPrice);
        joiner.add(maxPrice);
        joiner.add(discount);
        joiner.add(totalStock);
        joiner.add(stock);
        joiner.add(orders);
        joiner.add(productImage);
        joiner.add(productDetailUrl);
        joiner.add(totalTranpro3);
        joiner.add(productPositiveRate);
        joiner.add(productAverageStar);

        joiner.add(String.valueOf(soldout));

        return joiner.toString();
    }
}

