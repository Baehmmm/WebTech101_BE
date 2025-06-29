package de.htwberlin.webtech101.service;

import de.htwberlin.webtech101.persistence.ArticleCategory;
import de.htwberlin.webtech101.persistence.ArticleEntity;
import de.htwberlin.webtech101.persistence.ArticleRepository;
import de.htwberlin.webtech101.web.api.Article;
import de.htwberlin.webtech101.web.api.ArticleManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> findAll() {
        return articleRepository.findAll().stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Article findById(Long id) {
        return articleRepository.findById(id)
                .map(this::transformEntity)
                .orElse(null);
    }

    public Article create(ArticleManipulationRequest request) {
        String articleNumber = generateArticleNumber(request.getCategory());

        var entity = new ArticleEntity(articleNumber, request.getName(), request.getCategory(), request.getPrice());
        entity = articleRepository.save(entity);
        return transformEntity(entity);
    }

    public Article update(Long id, ArticleManipulationRequest request) {
        var optional = articleRepository.findById(id);
        if (optional.isEmpty()) return null;

        var entity = optional.get();

        // Prüfen, ob sich die Kategorie ändert
        boolean categoryChanged = !entity.getCategory().equals(request.getCategory());

        entity.setName(request.getName());
        entity.setCategory(request.getCategory());
        entity.setPrice(request.getPrice());

        if (categoryChanged) {
            String newArticleNumber = generateArticleNumber(request.getCategory());
            entity.setArticleNumber(newArticleNumber);
        }

        entity = articleRepository.save(entity);
        return transformEntity(entity);
    }

    public boolean deleteById(Long id) {
        if (!articleRepository.existsById(id)) return false;
        articleRepository.deleteById(id);
        return true;
    }

    private String generateArticleNumber(ArticleCategory category) {
        String prefix = switch (category) {
            case PRODUCT -> "PROD";
            case SERVICE -> "SERV";
            case POSTAGE -> "POST";
        };

        // Alle existierenden Artikelnummern der Kategorie
        List<Integer> usedNumbers = articleRepository.findAll().stream()
                .filter(a -> a.getCategory() == category)
                .map(a -> a.getArticleNumber().substring(prefix.length() + 1)) // alles nach z. B. "PROD-"
                .filter(s -> s.matches("\\d+"))
                .map(Integer::parseInt)
                .sorted()
                .toList();

        // nächste freie Nummer suchen
        int nextNumber = 1;
        for (int used : usedNumbers) {
            if (used != nextNumber) break;
            nextNumber++;
        }

        return String.format("%s-%04d", prefix, nextNumber);
    }

    private Article transformEntity(ArticleEntity entity) {
        return new Article(
                entity.getId(),
                entity.getArticleNumber(),
                entity.getName(),
                entity.getCategory(),
                entity.getPrice()
        );
    }
}
