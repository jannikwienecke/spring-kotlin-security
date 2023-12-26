package de.wienecke.basicsecurityapp.service

import de.wienecke.basicsecurityapp.model.toResponse
import de.wienecke.basicsecurityapp.repository.ArticleRepository
import org.springframework.stereotype.Service

@Service
class ArticleService(
    private val articleRepository: ArticleRepository
) {
    fun findAll() = articleRepository.findAll().map {
        it.toResponse()
    }


}