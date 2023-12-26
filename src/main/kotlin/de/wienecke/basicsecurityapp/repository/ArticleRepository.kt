package de.wienecke.basicsecurityapp.repository

import de.wienecke.basicsecurityapp.model.Article
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ArticleRepository {
    private  val articles = mutableListOf<Article>(
        Article(
            id = UUID.randomUUID(),
            title = "First Article",
            content = "This is the first article"
        ),
        Article(
            id = UUID.randomUUID(),
            title = "Second Article",
            content = "This is the second article"
        ),
        Article(
            id = UUID.randomUUID(),
            title = "Third Article",
            content = "This is the third article"
        ),
    )

    fun findAll(): List<Article> {
        return articles
    }
}