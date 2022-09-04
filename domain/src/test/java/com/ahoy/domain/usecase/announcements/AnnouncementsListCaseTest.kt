package com.ahoy.domain.usecase.announcements

import com.ahoy.data.mapper.CloudErrorMapper
import com.ahoy.data.repository.AppRepoImp
import com.ahoy.data.repository.AppRepository
import com.ahoy.domain.mapper.AnnouncementMapper
import com.ahoy.domain.mapper.AttachmentMapper
import com.ahoy.domain.utils.InstantExecutorExtension
import com.ahoy.domain.utils.MockJson
import com.ahoy.domain.utils.TestUtils
import com.ahoy.entities.announcements.local.Announcement
import com.ahoy.entities.announcements.query.AnnouncementsListQuery
import com.ahoy.entities.announcements.response.AnnouncementsListResponse
import com.ahoy.entities.base.ResponsePagingResultModel
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
class AnnouncementsListCaseTest {

    //system under test
    private lateinit var announcementsListUseCase: AnnouncementsListUsecase

    private lateinit var errorUtil: CloudErrorMapper
    private lateinit var appRepository: AppRepository
    private lateinit var mockJson: MockJson
    private lateinit var mapper: AnnouncementMapper
    private lateinit var attachmentMapper: AttachmentMapper

    @BeforeEach
    fun initEach() {
        errorUtil = Mockito.mock(CloudErrorMapper::class.java)
        appRepository = Mockito.mock(AppRepoImp::class.java)
        attachmentMapper = AttachmentMapper()
        mapper = AnnouncementMapper(attachmentMapper)
        mockJson = MockJson()
        announcementsListUseCase = AnnouncementsListUsecase(
            errorUtil,
            appRepository,
            mapper
        )
    }


    @Test
    fun convertRemoteAnnouncementDtoToAnnouncementBo_returnAnnouncementBo() = runBlocking {
        //Arrange
        val dto: AnnouncementsListResponse = mockJson.getAnnouncementList()

        val announcementsListBo = ResponsePagingResultModel<Announcement>(
            TestUtils.ANNOUNCEMENTS_LIST_BO,
            2,
            1
        )

        //Act
        val announcement = announcementsListUseCase.convert(dto)

        //here I choose data[1] because I created the second object in list as a real object not dummy one
        //Assert
        Assert.assertNotNull(announcement)
        Assertions.assertEquals(announcementsListBo.data[1].id, announcement.data[1].id)
        Assertions.assertEquals(announcementsListBo.data[1].message, announcement.data[1].message)
        Assertions.assertEquals(
            announcementsListBo.data[1].dateCreated,
            announcement.data[1].dateCreated
        )
        Assertions.assertEquals(
            announcementsListBo.data[1].attachments,
            announcement.data[1].attachments
        )
        Assertions.assertEquals(
            announcementsListBo.data[1].importance,
            announcement.data[1].importance
        )
        Assertions.assertEquals(announcementsListBo.data[1].shareUrl, announcement.data[1].shareUrl)
        Assertions.assertEquals(
            announcementsListBo.data[1].isBookMarked,
            announcement.data[1].isBookMarked
        )
    }

    @Test
    fun executeOnBackground_returnAnnouncementsListDto() = runBlocking {
        //Given
        val dto = mockJson.getAnnouncementList()
        val parameters = AnnouncementsListQuery(1, 10)

        //when
        mockitoWhen(appRepository.getAnnouncementsList(parameters))
            .thenReturn(dto)

        val response = appRepository.getAnnouncementsList(parameters)

        //then
        Assert.assertEquals(response, announcementsListUseCase.executeOnBackground(parameters))
    }

}









