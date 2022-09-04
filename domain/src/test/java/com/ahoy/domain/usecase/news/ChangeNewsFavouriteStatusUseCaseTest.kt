package com.ahoy.domain.usecase.news

import com.ahoy.data.mapper.CloudErrorMapper
import com.ahoy.data.repository.AppRepoImp
import com.ahoy.data.repository.AppRepository
import com.ahoy.domain.utils.InstantExecutorExtension
import com.ahoy.domain.utils.MockJson
import com.ahoy.entities.news.favorite.query.NewsDetailsQuery
import com.ahoy.entities.news.favorite.response.ChangeNewsFavouriteStatusResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.`when` as mockitoWhen

/**
 * Created by Shehab Elsarky.
 */
@ExtendWith(InstantExecutorExtension::class)
class ChangeNewsFavouriteStatusUseCaseTest {

    //system under test
    private lateinit var changeNewsFavouriteListUsecase: ChangeNewsFavouriteStatusUsecase

    private lateinit var errorUtil: CloudErrorMapper
    private lateinit var appRepository: AppRepository
    private lateinit var mockJson: MockJson

    @BeforeEach
    fun initEach() {
        errorUtil = Mockito.mock(CloudErrorMapper::class.java)
        appRepository = Mockito.mock(AppRepoImp::class.java)
        mockJson = MockJson()
        changeNewsFavouriteListUsecase = ChangeNewsFavouriteStatusUsecase(
            errorUtil,
            appRepository
        )
    }


    @Test
    fun convertChangeFavoriteNewsDtoToNewsBo_returnNewsBo() = runBlocking {
        //Arrange
        val dto: ChangeNewsFavouriteStatusResponse = mockJson.changeFavouriteNewsStatus()

        //Act
        val bo = changeNewsFavouriteListUsecase.convert(dto)

        //Assert
        Assert.assertNotNull(dto)
        Assert.assertNotNull(dto.result)
        Assert.assertEquals(dto.result!!.data, bo)
    }

    @Test
    fun executeOnBackground_returnChangeFavoriteNewsDto() = runBlocking {
        //Given
        val dto: ChangeNewsFavouriteStatusResponse = mockJson.changeFavouriteNewsStatus()
        val parameters = NewsDetailsQuery("")

        //when
        mockitoWhen(appRepository.changeNewsFavouriteStatus(parameters))
            .thenReturn(dto)

        val response = appRepository.changeNewsFavouriteStatus(parameters)
        //then
        Assert.assertEquals(
            response,
            changeNewsFavouriteListUsecase.executeOnBackground(parameters)
        )
    }

}









