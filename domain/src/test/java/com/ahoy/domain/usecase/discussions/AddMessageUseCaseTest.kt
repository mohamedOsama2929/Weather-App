package com.ahoy.domain.usecase.discussions

import com.ahoy.data.mapper.CloudErrorMapper
import com.ahoy.data.repository.AppRepoImp
import com.ahoy.data.repository.AppRepository
import com.ahoy.domain.utils.InstantExecutorExtension
import com.ahoy.domain.utils.MockJson
import com.ahoy.entities.discussions.query.AddMessageQuery
import com.ahoy.entities.discussions.response.AddMessageResponse
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
class AddMessageUseCaseTest {

    //system under test
    private lateinit var addMessageUseCase: AddMessageUseCase

    private lateinit var errorUtil: CloudErrorMapper
    private lateinit var appRepository: AppRepository
    private lateinit var mockJson: MockJson

    @BeforeEach
    fun initEach() {
        errorUtil = Mockito.mock(CloudErrorMapper::class.java)
        appRepository = Mockito.mock(AppRepoImp::class.java)
        mockJson = MockJson()
        addMessageUseCase = AddMessageUseCase(
            errorUtil,
            appRepository
        )
    }


    @Test
    fun convertAddMessageDtoToBo_returnBoolean() = runBlocking {
        //Arrange
        val addMessageQuery = AddMessageQuery()
        val dto: AddMessageResponse = mockJson.addMessage(addMessageQuery)

        //Act
        val bo = addMessageUseCase.convert(dto)

        //Assert
        Assert.assertNotNull(dto)
        Assert.assertNotNull(dto.result)
        Assert.assertEquals(dto.result!!.data, bo)
    }

    @Test
    fun executeOnBackground_returnAddMessageDto() = runBlocking {
        //Given
        val parameters = AddMessageQuery()
        val dto: AddMessageResponse = mockJson.addMessage(parameters)
        //when
        mockitoWhen(appRepository.addMessage(parameters))
            .thenReturn(dto)

        val response = appRepository.addMessage(parameters)
        //then
        Assert.assertEquals(response, addMessageUseCase.executeOnBackground(parameters))
    }

}









