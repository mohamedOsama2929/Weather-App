package com.ahoy.domain.mapper

import com.ahoy.domain.utils.TestUtils
import org.junit.Assert
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


/**
 * Created by Shehab elsarky.
 */

class NewsMapperTest {
    //system under test
    private lateinit var newsMapper: NewsMapper

    @BeforeEach
    fun init() {
        newsMapper = NewsMapper()
    }

    @Test
    fun remoteNewsObjectToNewsObject_returnNews() {
        //Arrange
        val remoteNewsDto = TestUtils.REMOTE_NEWS_DTO

        val newsBO = TestUtils.NEWS_BO

        //Act
        val news = newsMapper.convert(remoteNewsDto)

        //Assert
        Assert.assertNotNull(news)
        assertEquals(newsBO.id, news.id)
        assertEquals(newsBO.title, news.title)
        assertEquals(newsBO.date, news.date)
        assertEquals(newsBO.imageUrl, news.imageUrl)
        assertEquals(newsBO.content, news.content)
        assertEquals(newsBO.shareUrl, news.shareUrl)
        assertEquals(newsBO.isFavourite, news.isFavourite)
    }
}