package com.example.rynnarriola.newsapp.data.local

import com.example.rynnarriola.newsapp.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

class NewsDataBaseService constructor(private val newsDataBase: NewsDataBase) : DatabaseService {

    override fun getArticles(): Flow<List<Article>> {
        return newsDataBase.articleDao().getAll()
    }

    override fun deleteAllAndInsertAll(articles: List<Article>) {
        newsDataBase.articleDao().deleteAllAndInsertAll(articles)
    }

}