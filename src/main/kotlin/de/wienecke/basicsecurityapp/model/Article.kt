package de.wienecke.basicsecurityapp.model

import de.wienecke.basicsecurityapp.controller.article.ArticleResponse
import java.util.*

data class Article(
    val id: UUID,
    val title: String,
    val content: String,
)

fun Article.toResponse() = ArticleResponse(
    id = id.toString(),
    title = title,
    content = content
)
