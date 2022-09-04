package com.ahoy.domain.usecase.announcements

import com.ahoy.data.mapper.CloudErrorMapper
import com.ahoy.data.repository.AppRepoImp
import com.ahoy.data.repository.AppRepository
import com.ahoy.domain.mapper.AnnouncementMapper
import com.ahoy.domain.mapper.AttachmentMapper
import com.ahoy.domain.utils.InstantExecutorExtension
import com.ahoy.domain.utils.MockJson
import com.ahoy.domain.utils.TestUtils
import com.ahoy.entities.announcements.query.AnnouncementDetailsQuery
import com.ahoy.entities.announcements.response.AnnouncementDetailsResponse
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
class AnnouncementDetailsUseCaseTest {

    //system under test
    private lateinit var announcementDetailsUsecase: AnnouncementDetailsUsecase

    private lateinit var errorUtil: CloudErrorMapper
    private lateinit var appRepository: AppRepository
    private lateinit var mockJson: MockJson
    private lateinit var attachmentMapper: AttachmentMapper
    private lateinit var mapper: AnnouncementMapper

    @BeforeEach
    fun initEach() {
        errorUtil = Mockito.mock(CloudErrorMapper::class.java)
        appRepository = Mockito.mock(AppRepoImp::class.java)
        attachmentMapper = AttachmentMapper()
        mapper = AnnouncementMapper(attachmentMapper)
        mockJson = MockJson()
        announcementDetailsUsecase = AnnouncementDetailsUsecase(
            errorUtil,
            appRepository,
            mapper
        )
    }


    @Test
    fun convertRemoteAnnouncementsDtoToAnnouncementBo_returnAnnouncementBo() = runBlocking {

        //Arrange
        val dto: AnnouncementDetailsResponse = mockJson.getAnnouncementDetails()
        val AnnouncementBO = TestUtils.ANNOUNCEMENT_DETAILS_BO

        //Act
        val announcement = announcementDetailsUsecase.convert(dto)

        //Assert
        Assert.assertNotNull(dto)
        Assert.assertNotNull(announcement)
        Assertions.assertEquals(AnnouncementBO.id, announcement.id)
        Assertions.assertEquals(AnnouncementBO.message, announcement.message)
        Assertions.assertEquals(AnnouncementBO.dateCreated, announcement.dateCreated)
        Assertions.assertEquals(AnnouncementBO.importance, announcement.importance)
        Assertions.assertEquals(
            AnnouncementBO.attachments[0].attachmentName,
            announcement.attachments[0].attachmentName
        )
        Assertions.assertEquals(
            AnnouncementBO.attachments[0].attachmentURL,
            announcement.attachments[0].attachmentURL
        )
        Assertions.assertEquals(AnnouncementBO.shareUrl, announcement.shareUrl)
        Assertions.assertEquals(AnnouncementBO.isBookMarked, announcement.isBookMarked)
    }

    @Test
    fun executeOnBackground_returnAnnouncementDetailsDto() = runBlocking {
        //Given
        val dto = mockJson.getAnnouncementDetails()
        val parameters = AnnouncementDetailsQuery("")

        //when
        mockitoWhen(appRepository.getAnnouncementDetails(parameters))
            .thenReturn(dto)

        val response = appRepository.getAnnouncementDetails(parameters)

        //then
        Assert.assertEquals(response, announcementDetailsUsecase.executeOnBackground(parameters))
    }

}









