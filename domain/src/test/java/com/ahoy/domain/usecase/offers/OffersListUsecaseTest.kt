package com.ahoy.domain.usecase.offers

import com.ahoy.data.mapper.CloudErrorMapper
import com.ahoy.data.repository.AppRepoImp
import com.ahoy.data.repository.AppRepository
import com.ahoy.domain.mapper.AttachmentMapper
import com.ahoy.domain.mapper.OfferMapper
import com.ahoy.domain.utils.MockJson
import com.ahoy.domain.utils.TestUtils
import com.ahoy.entities.base.ResponsePagingResultModel
import com.ahoy.entities.offers.query.OffersListQuery
import com.ahoy.entities.offers.response.OffersListResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

/**
 * Created by Nader Nabil on 29/11/2020.
 */
class OffersListUsecaseTest {
    //system under test
    private lateinit var offerListUsecase: OffersListUsecase

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
        offerListUsecase = OffersListUsecase(
            errorUtil,
            appRepository,
            mapper
        )
    }


    @Test
    fun convertRemoteOffersDtoToOffersBo_returnNewsBo() = runBlocking {
        //Arrange
        val dto: OffersListResponse = mockJson.getOffersList()

        val offersListBo = ResponsePagingResultModel(
            TestUtils.OFFERS_LIST,
            2,
            1
        )

        //Act
        val offers = offerListUsecase.convert(dto)

        //here I choose data[1] because I created the second object in list as a real object not dummy one
        //Assert
        Assert.assertNotNull(offers)
        Assertions.assertEquals(offersListBo.data[1].id, offers.data[1].id)
        Assertions.assertEquals(offersListBo.data[1].title, offers.data[1].title)
        Assertions.assertEquals(offersListBo.data[1].terms, offers.data[1].terms)
        Assertions.assertEquals(offersListBo.data[1].imageUrl, offers.data[1].imageUrl)
        Assertions.assertEquals(offersListBo.data[1].description, offers.data[1].description)
        Assertions.assertEquals(offersListBo.data[1].expirationDate, offers.data[1].expirationDate)
        Assertions.assertEquals(offersListBo.data[1].createdDate, offers.data[1].createdDate)
    }

    @Test
    fun executeOnBackground_returnOffersListDto() = runBlocking {
        //Given
        val dto = mockJson.getOffersList()
        val parameters = OffersListQuery(1, 10, "", true)

        //when
        Mockito.`when`(appRepository.getOffersList(parameters))
            .thenReturn(dto)

        val response = appRepository.getOffersList(parameters)

        //then
        Assert.assertEquals(response, offerListUsecase.executeOnBackground(parameters))
    }

}