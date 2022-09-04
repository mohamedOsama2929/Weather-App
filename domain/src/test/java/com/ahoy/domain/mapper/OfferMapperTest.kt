package com.ahoy.domain.mapper

import com.ahoy.domain.utils.InstantExecutorExtension
import com.ahoy.domain.utils.TestUtils
import org.junit.Assert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * Created by Nader Nabil on 29/11/2020.
 */
@ExtendWith(InstantExecutorExtension::class)
class OfferMapperTest {
    //system under test
    private lateinit var offerMapper: OfferMapper
    private lateinit var attachmentMapper: AttachmentMapper

    @BeforeEach
    fun init() {
        attachmentMapper = AttachmentMapper()
        offerMapper = OfferMapper(attachmentMapper)
    }

    @Test
    fun remoteOfferObjectToOfferObject_returnOffer() {
        //Arrange
        val remoteOffer = TestUtils.OFFER_REMOTE

        val offerBo = TestUtils.OFFER_BO1

        //Act
        val offer = offerMapper.convert(remoteOffer)

        //Assert
        Assert.assertNotNull(offer)
        Assertions.assertEquals(offerBo.id, offer.id)
        Assertions.assertEquals(offerBo.createdDate, offer.createdDate)
        Assertions.assertEquals(offerBo.expirationDate, offer.expirationDate)
        Assertions.assertEquals(offerBo.title, offer.title)
        Assertions.assertEquals(offerBo.description, offer.description)
        Assertions.assertEquals(offerBo.terms, offer.terms)
        Assertions.assertEquals(offerBo.attachments, offer.attachments)
    }
}