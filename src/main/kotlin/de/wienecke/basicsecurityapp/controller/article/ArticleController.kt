package de.wienecke.basicsecurityapp.controller.article

import de.wienecke.basicsecurityapp.service.ArticleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/articles")
class ArticleController(
    private val articleService: ArticleService
) {

    @GetMapping("/")
    fun listAllArticles(): List<ArticleResponse> {
        return articleService.findAll()

    }

}