package com.ahoy.domain.usecase.events

import com.ahoy.data.mapper.CloudErrorMapper
import com.ahoy.data.repository.AppRepoImp
import com.ahoy.data.repository.AppRepository
import com.ahoy.domain.mapper.EventsMapper
import com.ahoy.domain.utils.InstantExecutorExtension
import com.ahoy.domain.utils.MockJson
import com.ahoy.domain.utils.TestUtils
import com.ahoy.entities.base.ResponsePagingResultModel
import com.ahoy.entities.events.query.EventsListQuery
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.`when` as mockitoWhen


@ExtendWith(InstantExecutorExtension::class)
class EventsListCaseTest {

    //system under test
    private lateinit var eventsListUseCase: EventsListUseCase

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
        eventsListUseCase = EventsListUseCase(
            errorUtil,
            appRepository,
            mapper
        )
    }


    @Test
    fun convertRemoteEventsDtoToEventBo_returnEventBo() = runBlocking {
        //Arrange
        val dto = mockJson.getEventsList()

        val eventsListBo = ResponsePagingResultModel(
            TestUtils.EVENTS_LIST_BO,
            2,
            1
        )

        //Act
        val events = eventsListUseCase.convert(dto)

        //Assert
        Assert.assertNotNull(events)
        Assertions.assertNotEquals(events.data.size, 0)

        Assertions.assertEquals(eventsListBo.data[0], events.data[0])
    }

    @Test
    fun executeOnBackground_returnEventsListDto() = runBlocking {
        //Given
        val dto = mockJson.getEventsList()
        val parameters = EventsListQuery(1, 10, "2020-11-25'T'10:10:10", "2020-11-265'T'11:11:11")

        //when
        mockitoWhen(appRepository.getEventsList(parameters))
            .thenReturn(dto)

        val response = appRepository.getEventsList(parameters)


        //then
        Assert.assertEquals(response, eventsListUseCase.executeOnBackground(parameters))
    }

}









