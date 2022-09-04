package com.ahoy.domain.mapper

import com.ahoy.domain.utils.InstantExecutorExtension
import com.ahoy.domain.utils.TestUtils
import org.junit.Assert
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * Created by Shehab elsarky.
 */
@ExtendWith(InstantExecutorExtension::class)
class AnnouncementMapperTest {
    //system under test
    private lateinit var announcementMapper: AnnouncementMapper
    private lateinit var attachmentMapper: AttachmentMapper

    @BeforeEach
    fun init() {
        attachmentMapper = AttachmentMapper()
        announcementMapper = AnnouncementMapper(attachmentMapper)
    }

    @Test
    fun remoteAnnouncementObjectToAnnouncementObject_returnAnnouncement() {
        //Arrange
        val remoteAnnouncement = TestUtils.ANNOUNCEMENT_REMOTE

        val announcementBO = TestUtils.ANNOUNCEMENT_BO

        //Act
        val announcement = announcementMapper.convert(remoteAnnouncement)

        //Assert
        Assert.assertNotNull(announcement)
        assertEquals(announcementBO.id, announcement.id)
        assertEquals(announcementBO.dateCreated, announcement.dateCreated)
        assertEquals(announcementBO.importance, announcement.importance)
        assertEquals(announcementBO.isBookMarked, announcement.isBookMarked)
        assertEquals(announcementBO.message, announcement.message)
        assertEquals(announcementBO.shareUrl, announcement.shareUrl)
        assertEquals(announcementBO.attachments, announcement.attachments)
    }
}