package com.ahoy.domain.usecase.news

import com.ahoy.data.mapper.CloudErrorMapper
import com.ahoy.data.repository.AppRepoImp
import com.ahoy.data.repository.AppRepository
import com.ahoy.domain.mapper.NewsMapper
import com.ahoy.domain.utils.InstantExecutorExtension
import com.ahoy.domain.utils.MockJson
import com.ahoy.domain.utils.TestUtils
import com.ahoy.entities.base.ResponsePagingResultModel
import com.ahoy.entities.news.favorite.local.News
import com.ahoy.entities.news.favorite.query.NewsListQuery
import com.ahoy.entities.news.favorite.response.NewsListResponse
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
class NewsListseCaseTest {

    //system under test
    private lateinit var newsListUseCase: NewsListUsecase

    private lateinit var errorUtil: CloudErrorMapper
    private lateinit var appRepository: AppRepository
    private lateinit var mockJson: MockJson
    private lateinit var mapper: NewsMapper

    @BeforeEach
    fun initEach() {
        errorUtil = Mockito.mock(CloudErrorMapper::class.java)
        appRepository = Mockito.mock(AppRepoImp::class.java)
        mapper = NewsMapper()
        mockJson = MockJson()
        newsListUseCase = NewsListUsecase(
            errorUtil,
            appRepository,
            mapper
        )
    }


    @Test
    fun convertRemoteNewsDtoToNewsBo_returnNewsBo() = runBlocking {
        //Arrange
        val dto: NewsListResponse = mockJson.getNewsList()

        val newsListBo = ResponsePagingResultModel<News>(
            TestUtils.NEWS_LIST_BO,
            2,
            1
        )

        //Act
        val news = newsListUseCase.convert(dto)

        //here I choose data[1] because I created the second object in list as a real object not dummy one
        //Assert
        Assert.assertNotNull(news)
        Assertions.assertEquals(newsListBo.data[1].id, news.data[1].id)
        Assertions.assertEquals(newsListBo.data[1].title, news.data[1].title)
        Assertions.assertEquals(newsListBo.data[1].date, news.data[1].date)
        Assertions.assertEquals(newsListBo.data[1].imageUrl, news.data[1].imageUrl)
        Assertions.assertEquals(newsListBo.data[1].content, news.data[1].content)
        Assertions.assertEquals(newsListBo.data[1].shareUrl, news.data[1].shareUrl)
        Assertions.assertEquals(newsListBo.data[1].isFavourite, news.data[1].isFavourite)
    }

    @Test
    fun executeOnBackground_returnNewsListDto() = runBlocking {
        //Given
        val dto = mockJson.getNewsList()
        val parameters = NewsListQuery(1, 10)

        //when
        mockitoWhen(appRepository.getNewsList(parameters))
            .thenReturn(dto)

        val response = appRepository.getNewsList(parameters)

        //then
        Assert.assertEquals(response, newsListUseCase.executeOnBackground(parameters))
    }

}









