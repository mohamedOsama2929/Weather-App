package com.ahoy.domain.usecase.offers

import com.ahoy.data.mapper.CloudErrorMapper
import com.ahoy.data.repository.AppRepoImp
import com.ahoy.data.repository.AppRepository
import com.ahoy.domain.mapper.AttachmentMapper
import com.ahoy.domain.mapper.OfferMapper
import com.ahoy.domain.utils.MockJson
import com.ahoy.domain.utils.TestUtils
import com.ahoy.entities.offers.query.OfferDetailsQuery
import com.ahoy.entities.offers.response.OfferDetailsResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

/**
 * Created by Nader Nabil on 29/11/2020.
 */
class OfferDetailsUsecaseTest {
    //system under test
    private lateinit var offerDetailsUsecase: OfferDetailsUsecase

    private lateinit var errorUtil: CloudErrorMapper
    private lateinit var appRepository: AppRepository
    private lateinit var mockJson: MockJson
    private lateinit var mapper: OfferMapper
    private lateinit var attachmentMapper: AttachmentMapper

    @BeforeEach
    fun initEach() {
        errorUtil = Mockito.mock(CloudErrorMapper::class.java)
        appRepository = Mockito.mock(AppRepoImp::class.java)
        attachmentMapper = AttachmentMapper()
        mapper = OfferMapper(attachmentMapper)
        mockJson = MockJson()
        offerDetailsUsecase = OfferDetailsUsecase(
            errorUtil,
            mapper,
            appRepository
        )
    }


    @Test
    fun convertRemoteOffersDtoToOffersBo_returnNewsBo() = runBlocking {
        //Arrange
        val dto: OfferDetailsResponse = mockJson.getOfferDetails()

        val offerBO = TestUtils.OFFER_DETAILS_BO

        //Act
        val offers = offerDetailsUsecase.convert(dto)

        //here I choose data[1] because I created the second object in list as a real object not dummy one
        //Assert
        Assert.assertNotNull(offers)
        Assertions.assertEquals(offerBO.id, offers.id)
        Assertions.assertEquals(offerBO.title, offers.title)
        Assertions.assertEquals(offerBO.terms, offers.terms)
        Assertions.assertEquals(offerBO.imageUrl, offers.imageUrl)
        Assertions.assertEquals(offerBO.description, offers.description)
        Assertions.assertEquals(offerBO.expirationDate, offers.expirationDate)
        Assertions.assertEquals(offerBO.createdDate, offers.createdDate)
    }

    @Test
    fun executeOnBackground_returnOffersListDto() = runBlocking {
        //Given
        val dto = mockJson.getOfferDetails()
        val parameters = OfferDetailsQuery("")

        //when
        Mockito.`when`(appRepository.getOfferDetails(parameters))
            .thenReturn(dto)

        val response = appRepository.getOfferDetails(parameters)

        //then
        Assert.assertEquals(response, offerDetailsUsecase.executeOnBackground(parameters))
    }

}
