package de.htwberlin.webtech101.web.api;

import de.htwberlin.webtech101.persistence.ArticleCategory;

public class ArticleManipulationRequest {

    private String name;
    private ArticleCategory category;
    private double price;

    public ArticleManipulationRequest() {}

    public ArticleManipulationRequest(String name, ArticleCategory category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(ArticleCategory category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
