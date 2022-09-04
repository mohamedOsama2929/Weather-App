package com.ahoy.domain.usecase.internal_news

import com.ahoy.data.mapper.CloudErrorMapper
import com.ahoy.data.repository.AppRepoImp
import com.ahoy.data.repository.AppRepository
import com.ahoy.domain.mapper.InternalNewsMapper
import com.ahoy.domain.utils.InstantExecutorExtension
import com.ahoy.domain.utils.MockJson
import com.ahoy.domain.utils.TestUtils
import com.ahoy.entities.internal_news.favorite.response.InternalNewsDetailsResponse
import com.ahoy.entities.news.favorite.query.NewsDetailsQuery
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.`when` as mockitoWhen

/**
 * Created by Shehab Elsarky.
 */
@ExtendWith(InstantExecutorExtension::class)
class InternalNewsDetailsUseCaseTest {

    //system under test
    private lateinit var newsDetailsUsecase: InternalNewsDetailsUsecase

    private lateinit var errorUtil: CloudErrorMapper
    private lateinit var appRepository: AppRepository
    private lateinit var mockJson: MockJson
    private lateinit var mapper: InternalNewsMapper

    @BeforeEach
    fun initEach() {
        errorUtil = Mockito.mock(CloudErrorMapper::class.java)
        appRepository = Mockito.mock(AppRepoImp::class.java)
        mapper = InternalNewsMapper()
        mockJson = MockJson()
        newsDetailsUsecase = InternalNewsDetailsUsecase(
            errorUtil,
            appRepository,
            mapper
        )
    }


    @Test
    fun convertRemoteNewsDtoToNewsBo_returnNewsBo() = runBlocking {

        //Arrange
        val dto: InternalNewsDetailsResponse = mockJson.getInternalNewsDetails()
        val newsBO = TestUtils.INTERNAL_NEWS_DETAILS_BO

        //Act
        val news = newsDetailsUsecase.convert(dto)

        //Assert
        Assert.assertNotNull(dto)
        Assert.assertNotNull(news)
        Assertions.assertEquals(newsBO.id, news.id)
        Assertions.assertEquals(newsBO.title, news.title)
        Assertions.assertEquals(newsBO.date, news.date)
        Assertions.assertEquals(newsBO.imageUrl, news.imageUrl)
        Assertions.assertEquals(newsBO.content, news.content)
        Assertions.assertEquals(newsBO.shareUrl, news.shareUrl)
        Assertions.assertEquals(newsBO.isFavourite, news.isFavourite)
    }

    @Test
    fun executeOnBackground_returnNewsDetailsDto() = runBlocking {
        //Given
        val dto = mockJson.getInternalNewsDetails()
        val parameters = NewsDetailsQuery("")

        //when
        mockitoWhen(appRepository.getInternalNewsDetails(parameters))
            .thenReturn(dto)

        val response = appRepository.getInternalNewsDetails(parameters)

        //then
        Assert.assertEquals(response, newsDetailsUsecase.executeOnBackground(parameters))
    }

}









