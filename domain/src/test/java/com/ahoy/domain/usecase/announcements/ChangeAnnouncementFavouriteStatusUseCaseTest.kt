package com.ahoy.domain.usecase.announcements

import com.ahoy.data.mapper.CloudErrorMapper
import com.ahoy.data.repository.AppRepoImp
import com.ahoy.data.repository.AppRepository
import com.ahoy.domain.utils.InstantExecutorExtension
import com.ahoy.domain.utils.MockJson
import com.ahoy.entities.announcements.query.AnnouncementDetailsQuery
import com.ahoy.entities.announcements.response.ChangeAnnouncementFavouriteStatusResponse
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
class ChangeAnnouncementFavouriteStatusUseCaseTest {

    //system under test
    private lateinit var changeAnnouncementListUsecase: ChangeAnnouncementFavouriteStatusUsecase

    private lateinit var errorUtil: CloudErrorMapper
    private lateinit var appRepository: AppRepository
    private lateinit var mockJson: MockJson

    @BeforeEach
    fun initEach() {
        errorUtil = Mockito.mock(CloudErrorMapper::class.java)
        appRepository = Mockito.mock(AppRepoImp::class.java)
        mockJson = MockJson()
        changeAnnouncementListUsecase = ChangeAnnouncementFavouriteStatusUsecase(
            errorUtil,
            appRepository
        )
    }


    @Test
    fun convertChangeFavoriteAnnouncementDtoToBooleanBo_returnBoolean() = runBlocking {
        //Arrange
        val dto: ChangeAnnouncementFavouriteStatusResponse =
            mockJson.changeFavouriteAnnouncementStatus()

        //Act
        val bo = changeAnnouncementListUsecase.convert(dto)

        //Assert
        Assert.assertNotNull(dto)
        Assert.assertNotNull(dto.result)
        Assert.assertEquals(dto.result!!.data, bo)
    }

    @Test
    fun executeOnBackground_returnChangeFavoriteAnnouncementDto() = runBlocking {
        //Given
        val dto: ChangeAnnouncementFavouriteStatusResponse =
            mockJson.changeFavouriteAnnouncementStatus()
        val parameters = AnnouncementDetailsQuery("")

        //when
        mockitoWhen(appRepository.changeAnnouncementFavouriteStatus(parameters))
            .thenReturn(dto)

        val response = appRepository.changeAnnouncementFavouriteStatus(parameters)

        //then
        Assert.assertEquals(response, changeAnnouncementListUsecase.executeOnBackground(parameters))
    }

}









