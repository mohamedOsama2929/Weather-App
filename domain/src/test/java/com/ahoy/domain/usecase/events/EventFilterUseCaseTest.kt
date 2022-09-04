package com.ahoy.domain.usecase.events

import com.ahoy.data.mapper.CloudErrorMapper
import com.ahoy.domain.utils.InstantExecutorExtension
import com.ahoy.entities.events.local.EventFilter
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import java.util.*
import java.util.Calendar.*


@ExtendWith(InstantExecutorExtension::class)
class EventFilterUseCaseTest {

    //system under test
    private lateinit var eventFilterUseCase: EventFilterUseCase

    private lateinit var errorUtil: CloudErrorMapper


    @BeforeEach
    fun initEach() {
        errorUtil = Mockito.mock(CloudErrorMapper::class.java)

        eventFilterUseCase = EventFilterUseCase(errorUtil)
    }


    @Test
    fun `executeOnBackground to update fromDate filter then return the eventFilter updated`() =
        runBlocking {

            //Arrange
            val calendarToSet = Calendar.getInstance()
            calendarToSet.set(2020, 10, 25)

            val params = Triple(EventFilter(), FilterType.UpdateFromFilter, calendarToSet)

            //Act
            val eventFilter = eventFilterUseCase.executeOnBackground(params)

            //Assert
            assertThat(eventFilter.toFilter, nullValue())
            assertThat(eventFilter.fromFilter, notNullValue())

            assertThat(eventFilter.fromFilter?.day, `is`(25))
            assertThat(eventFilter.fromFilter?.month, `is`(10))
            assertThat(eventFilter.fromFilter?.year, `is`(2020))
            assertThat(eventFilter.fromFilter?.filterText, `is`("25 Nov 2020"))
        }

    @Test
    fun `executeOnBackground to update toDate filter then return the eventFilter updated`() =
        runBlocking {

            //Arrange
            val calendarToSet = Calendar.getInstance()
            calendarToSet.set(2020, 11, 2)

            val params = Triple(EventFilter(), FilterType.UpdateToFilter, calendarToSet)

            //Act
            val eventFilter = eventFilterUseCase.executeOnBackground(params)

            //Assert
            assertThat(eventFilter.fromFilter, nullValue())
            assertThat(eventFilter.toFilter, notNullValue())

            assertThat(eventFilter.toFilter?.day, `is`(2))
            assertThat(eventFilter.toFilter?.month, `is`(11))
            assertThat(eventFilter.toFilter?.year, `is`(2020))
            assertThat(eventFilter.toFilter?.filterText, `is`("02 Dec 2020"))
        }


    @Test
    fun `executeOnBackground to apply default filter then return the eventFilter updated`() =
        runBlocking {

            //Arrange
            val defaultFromCalendar = Calendar.getInstance().apply { add(DAY_OF_MONTH, -7) }
            val defaultToCalendar = Calendar.getInstance().apply { add(DAY_OF_MONTH, 21) }

            val params = Triple(EventFilter(), FilterType.SetDefaultFilter, Calendar.getInstance())

            //Act
            val eventFilter = eventFilterUseCase.executeOnBackground(params)

            //Assert
            assertThat(eventFilter.fromFilter, notNullValue())
            assertThat(eventFilter.toFilter, notNullValue())
            assert(eventFilter.isDefaultFilter)

            assertThat(eventFilter.fromFilter?.day, `is`(defaultFromCalendar.get(DAY_OF_MONTH)))
            assertThat(eventFilter.fromFilter?.month, `is`(defaultFromCalendar.get(MONTH)))
            assertThat(eventFilter.fromFilter?.year, `is`(defaultFromCalendar.get(YEAR)))

            assertThat(eventFilter.toFilter?.day, `is`(defaultToCalendar.get(DAY_OF_MONTH)))
            assertThat(eventFilter.toFilter?.month, `is`(defaultToCalendar.get(MONTH)))
            assertThat(eventFilter.toFilter?.year, `is`(defaultToCalendar.get(YEAR)))
        }

}









