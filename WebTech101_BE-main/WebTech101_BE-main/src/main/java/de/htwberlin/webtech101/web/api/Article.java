package de.htwberlin.webtech101.web.api;

import de.htwberlin.webtech101.persistence.ArticleCategory;

public class Article {

    private long id;
    private String articleNumber;
    private String name;
    private ArticleCategory category;
    private double price;

    public Article(long id, String articleNumber, String name, ArticleCategory category, double price) {
        this.id = id;
        this.articleNumber = articleNumber;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public String getName() {
        return name;
    }

    public ArticleCategory getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }
}
