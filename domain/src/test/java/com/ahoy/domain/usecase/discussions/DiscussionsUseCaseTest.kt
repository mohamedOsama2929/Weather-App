package com.ahoy.domain.usecase.discussions

import com.ahoy.data.mapper.CloudErrorMapper
import com.ahoy.data.repository.AppRepoImp
import com.ahoy.data.repository.AppRepository
import com.ahoy.domain.mapper.DiscussionMapper
import com.ahoy.domain.utils.InstantExecutorExtension
import com.ahoy.domain.utils.MockJson
import com.ahoy.entities.discussions.response.DiscussionsResponse
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
class DiscussionsUseCaseTest {

    //system under test
    private lateinit var discussionsUseCase: DiscussionsUseCase

    private lateinit var errorUtil: CloudErrorMapper
    private lateinit var appRepository: AppRepository
    private lateinit var mockJson: MockJson
    private lateinit var discussionsMapper: DiscussionMapper

    @BeforeEach
    fun initEach() {
        errorUtil = Mockito.mock(CloudErrorMapper::class.java)
        appRepository = Mockito.mock(AppRepoImp::class.java)
        discussionsMapper = Mockito.mock(DiscussionMapper::class.java)
        mockJson = MockJson()
        discussionsUseCase = DiscussionsUseCase(
            errorUtil,
            appRepository,
            discussionsMapper
        )
    }


    @Test
    fun convertDiscussionsDtoToBo_returnDiscussions() = runBlocking {
        //Arrange
        val query = ""
        val dto: DiscussionsResponse = mockJson.getDiscussions(query)

        //Act
        val bo = discussionsUseCase.convert(dto)

        //Assert
        Assert.assertNotNull(dto)
        Assert.assertNotNull(dto.result)
        Assert.assertEquals(dto.result!!.data, bo)
    }

    @Test
    fun executeOnBackground_returnDiscussionsDto() = runBlocking {
        //Given
        val parameters = ""
        val dto: DiscussionsResponse = mockJson.getDiscussions(parameters)
        //when
        mockitoWhen(appRepository.getDiscussions(parameters))
            .thenReturn(dto)

        val response = appRepository.getDiscussions(parameters)
        //then
        Assert.assertEquals(response, discussionsUseCase.executeOnBackground(parameters))
    }

}









