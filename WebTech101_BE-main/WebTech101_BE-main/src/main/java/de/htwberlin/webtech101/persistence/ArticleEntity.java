package de.htwberlin.webtech101.persistence;

import jakarta.persistence.*;

@Entity(name = "articles")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "article_number", nullable = false, unique = true)
    private String articleNumber;

    @Column(name = "article_name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private ArticleCategory category;

    @Column(name = "price", nullable = false)
    private double price;

    protected ArticleEntity() {}

    public ArticleEntity(String articleNumber, String name, ArticleCategory category, double price) {
        this.articleNumber = articleNumber;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    // Getter & Setter
    public long getId() {
        return id;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArticleCategory getCategory() {
        return category;
    }

    public void setCategory(ArticleCategory category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
