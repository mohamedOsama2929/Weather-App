package com.ahoy.domain.usecase.events

import com.ahoy.data.mapper.CloudErrorMapper
import com.ahoy.data.repository.AppRepoImp
import com.ahoy.data.repository.AppRepository
import com.ahoy.domain.mapper.EventsMapper
import com.ahoy.domain.utils.InstantExecutorExtension
import com.ahoy.domain.utils.MockJson
import com.ahoy.domain.utils.TestUtils
import com.ahoy.entities.events.query.EventDetailsQuery
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.`when` as mockitoWhen


@ExtendWith(InstantExecutorExtension::class)
class EventDetailsUseCaseTest {

    //system under test
    private lateinit var eventDetailsUseCase: EventDetailsUseCase

    private lateinit var errorUtil: CloudErrorMapper
    private lateinit var appRepository: AppRepository
    private lateinit var mockJson: MockJson
    private lateinit var mapper: EventsMapper

    @BeforeEach
    fun initEach() {
        errorUtil = Mockito.mock(CloudErrorMapper::class.java)
        appRepository = Mockito.mock(AppRepoImp::class.java)
        mapper = EventsMapper()
        mockJson = MockJson()

        eventDetailsUseCase = EventDetailsUseCase(
            errorUtil,
            appRepository,
            mapper
        )
    }


    @Test
    fun convertRemoteEventsDtoToEventBo_returnEventBo() = runBlocking {

        //Arrange
        val dto = mockJson.getEventDetails()
        val eventDetailsBo = TestUtils.EVENT_DETAILS_BO

        //Act
        val event = eventDetailsUseCase.convert(dto)

        //Assert
        Assert.assertNotNull(dto)
        Assert.assertNotNull(event)
        Assert.assertEquals(eventDetailsBo, event)
    }

    @Test
    fun executeOnBackground_returnAnnouncementDetailsDto() = runBlocking {
        //Given
        val dto = mockJson.getEventDetails()
        val parameters = EventDetailsQuery("")

        //when
        mockitoWhen(appRepository.getEventDetails(parameters))
            .thenReturn(dto)

        val response = appRepository.getEventDetails(parameters)

        //then
        Assert.assertEquals(response, eventDetailsUseCase.executeOnBackground(parameters))
    }

}









