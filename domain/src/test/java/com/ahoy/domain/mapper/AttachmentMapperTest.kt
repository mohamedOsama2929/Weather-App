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
class AttachmentMapperTest {
    //system under test
    private lateinit var attachmentMapper: AttachmentMapper

    @BeforeEach
    fun init() {
        attachmentMapper = AttachmentMapper()
    }

    @Test
    fun remoteAttachmentObjectToAttachmentObject_returnAttachment() {
        //Arrange
        val remoteAttachment = TestUtils.ATTACHMENT_REMOTE

        val attachmentBO = TestUtils.ATTACHMENT_BO

        //Act
        val attachment = attachmentMapper.convert(remoteAttachment)

        //Assert
        Assert.assertNotNull(attachment)
        assertEquals(attachmentBO.attachmentName, attachment.attachmentName)
        assertEquals(attachmentBO.attachmentURL, attachment.attachmentURL)
    }

    @Test
    fun remoteAttachmentNullObjectToAttachmentObject_returnDefaultAttachment() {
        //Arrange
        val remoteAttachment = TestUtils.ATTACHMENT_REMOTE_NULL

        val attachmentBO = TestUtils.ATTACHMENT_BO_DEFAULT

        //Act
        val attachment = attachmentMapper.convert(remoteAttachment)

        //Assert
        Assert.assertNotNull(attachment)
        assertEquals(attachmentBO.attachmentName, attachment.attachmentName)
        assertEquals(attachmentBO.attachmentURL, attachment.attachmentURL)
    }
}