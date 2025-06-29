package de.htwberlin.webtech101.web;

import de.htwberlin.webtech101.service.ArticleService;
import de.htwberlin.webtech101.web.api.Article;
import de.htwberlin.webtech101.web.api.ArticleManipulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ArticleRestController {

    private final ArticleService articleService;

    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/api/v1/articles")
    public ResponseEntity<List<Article>> fetchArticles() {
        return ResponseEntity.ok(articleService.findAll());
    }

    @GetMapping("/api/v1/articles/{id}")
    public ResponseEntity<Article> fetchArticleById(@PathVariable Long id) {
        var article = articleService.findById(id);
        return article != null ? ResponseEntity.ok(article) : ResponseEntity.notFound().build();
    }

    @PostMapping("/api/v1/articles")
    public ResponseEntity<Void> createArticle(@RequestBody ArticleManipulationRequest request) throws URISyntaxException {
        var article = articleService.create(request);
        return ResponseEntity.created(new URI("/api/v1/articles/" + article.getId())).build();
    }

    @PutMapping("/api/v1/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody ArticleManipulationRequest request) {
        var article = articleService.update(id, request);
        return article != null ? ResponseEntity.ok(article) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/v1/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        boolean successful = articleService.deleteById(id);
        return successful ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
