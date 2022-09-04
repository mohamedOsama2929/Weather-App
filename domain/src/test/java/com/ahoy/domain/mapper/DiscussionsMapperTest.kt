package com.ahoy.domain.mapper

import com.ahoy.domain.utils.MockJson
import com.ahoy.domain.utils.TestUtils
import org.junit.Assert
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


/**
 * Created by Shehab elsarky.
 */

class DiscussionsMapperTest {
    //system under test
    private lateinit var discussionMapper: DiscussionMapper
    private lateinit var relativeDateTimeMapper: RelativeDateTimeMapper
    private lateinit var mockJson: MockJson

    @BeforeEach
    fun init() {
        mockJson = MockJson()
        relativeDateTimeMapper = RelativeDateTimeMapper()
        discussionMapper = DiscussionMapper(relativeDateTimeMapper)
    }

    @Test
    fun remoteDiscussionsObjectToDiscussionsObject_returnDiscussions() {
        //Arrange
        val remoteDiscussionsDto = mockJson.getDiscussions("")

        val discussionsBO = TestUtils.DISCUSSIONS_BO

        //Act
        val discussions = discussionMapper.convert(remoteDiscussionsDto)

        //Assert
        Assert.assertNotNull(discussions)
        assertEquals(discussions.size, 1)
        assertEquals(discussions[0].comment, discussionsBO.comment)
        assertEquals(discussions[0].date, discussionsBO.date)
        assertEquals(discussions[0].imageUrl, discussionsBO.imageUrl)
        assertEquals(discussions[0].department, discussionsBO.department)
        assertEquals(discussions[0].name, discussionsBO.name)
    }
}