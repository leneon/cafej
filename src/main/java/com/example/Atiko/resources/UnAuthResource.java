package com.example.Atiko.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Atiko.dtos.ArticleDto;
import com.example.Atiko.dtos.CategorieDto;
import com.example.Atiko.dtos.CommentaireDto;
import com.example.Atiko.dtos.NewsletterDto;
import com.example.Atiko.dtos.StructureDto;
import com.example.Atiko.services.ArticleService;
import com.example.Atiko.services.CategorieService;
import com.example.Atiko.services.CommentaireService;
import com.example.Atiko.services.NewsletterService;
import com.example.Atiko.services.StructureService;

@RequestMapping("/unauth")
@RestController
public class UnAuthResource {
    
    @Autowired
    private StructureService structureService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategorieService categorieService;
    @Autowired
    private NewsletterService newsletterService;
    @Autowired
    private CommentaireService commentaireService;

    @GetMapping("/structure")
    public ResponseEntity<StructureDto> getFirstStructure() {
        List<StructureDto> structures = structureService.getAllStructures();
        if (structures.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content si aucune structure n'existe
        }
        return ResponseEntity.ok(structures.get(0));  // 200 OK avec la première structure
    } 

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleDto>> getAllArticlesWithComments() {
        List<ArticleDto> aricles = articleService.getAllArticlesWithCommentCount();
        if (aricles.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content si aucune structure n'existe
        }
        return ResponseEntity.ok(aricles);  // 200 OK avec la première structure
    }
    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticleDto> getAarticle(@PathVariable Long id) {
        ArticleDto aricle = articleService.getArticleById(id);
        if (aricle==null) {
            return ResponseEntity.noContent().build();  // 204 No Content si aucune structure n'existe
        }
        return ResponseEntity.ok(aricle);  // 200 OK avec la première structure
    }
    @PutMapping("/like/{id}")
    public ResponseEntity<ArticleDto> like(@PathVariable Long id) {
       return ResponseEntity.ok(articleService.like(id));
    }
    @PutMapping("/dislike/{id}")
    public ResponseEntity<ArticleDto> dislike(@PathVariable Long id) {
       return ResponseEntity.ok(articleService.like(id));
    }
    @GetMapping("/categories")
    public ResponseEntity<List<CategorieDto>> getAllCategories() {
        List<CategorieDto> cats = categorieService.getAllCategories();
        if (cats.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content si aucune structure n'existe
        }
        return ResponseEntity.ok(cats);  // 200 OK avec la première structure
    }

    @PostMapping("/newsletter")
    public ResponseEntity<NewsletterDto> newsletter(@RequestBody NewsletterDto dto) {
        return ResponseEntity.ok(newsletterService.createNewsletter(dto));  // 200 OK avec la première structure
    }

    @PostMapping("/commentaire")
    public ResponseEntity<CommentaireDto> postComment(@RequestBody CommentaireDto dto) {
        CommentaireDto comment = commentaireService.saveCommentaire(dto);
        if (comment==null) {
            return ResponseEntity.noContent().build();  // 204 No Content si aucune structure n'existe
        }
        return ResponseEntity.ok(comment);  // 200 OK avec la première structure
    }

  
}
