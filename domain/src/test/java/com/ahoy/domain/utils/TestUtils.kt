package com.ahoy.domain.utils

import com.ahoy.domain.mapper.RelativeDateTimeMapper
import com.ahoy.entities.announcements.local.Announcement
import com.ahoy.entities.announcements.local.AnnouncementImportance
import com.ahoy.entities.announcements.local.Attachment
import com.ahoy.entities.announcements.remote.RemoteAnnouncement
import com.ahoy.entities.announcements.remote.RemoteAttachment
import com.ahoy.entities.discussions.local.Discussions
import com.ahoy.entities.events.local.Event
import com.ahoy.entities.internal_news.favorite.local.InternalNews
import com.ahoy.entities.internal_news.favorite.remote.Tag
import com.ahoy.entities.news.favorite.local.News
import com.ahoy.entities.news.favorite.remote.RemoteNews
import com.ahoy.entities.offers.local.Offer
import com.ahoy.entities.offers.remote.RemoteOffer
import java.util.*

class TestUtils {
    companion object {

        val REMOTE_NEWS_DTO = RemoteNews(
            false,
            null,
            "2020-11-02T09:59:11.407",
            "Test description api",
            null,
            "2020-11-02T09:59:11.407",
            "f0001f01-bfa3-45d5-9c18-affc619da703",
            "TEst API"
        )

        val NEWS_BO = News(
            "f0001f01-bfa3-45d5-9c18-affc619da703",
            "TEst API",
            "02 November 2020",
            "",
            "Test description api",
            "",
            false
        )

        val NEWS_BO2 = News(
            "8bd59b22-945e-480c-89c7-9a90f223f376",
            "TEst API",
            "02 November 2020",
            "http://40.114.214.132:81/api/FileManger/Download/008c7742-804c-4b8f-a635-7b57f092731a?Token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDQzNDU4NDQsImlzcyI6IjAwOGM3NzQyLTgwNGMtNGI4Zi1hNjM1LTdiNTdmMDkyNzMxYSJ9.wD1a1VsVmdZqBQ55QiLEOvIxYPSy1q1ZXXgxvApFx7Y",
            "Test description api",
            "http://40.114.214.132:82/api/News/8bd59b22-945e-480c-89c7-9a90f223f376",
            false
        )


        val NEWS_DETAILS_BO = News(
            "c5d9b40a-86a2-4382-be38-cd093cadf0e0",
            "TEst API",
            "02 November 2020",
            "http://40.114.214.132:81/api/FileManger/Download/4d14ba22-010c-4b5b-a900-1f6f8a44f82b?Token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDQzNDYyNTAsImlzcyI6IjRkMTRiYTIyLTAxMGMtNGI1Yi1hOTAwLTFmNmY4YTQ0ZjgyYiJ9.IcawbYGnhZMIRMv6Zy0pVgBqQnRSErqyP_GlziOypjo",
            "Test description api",
            "http://40.114.214.132:82/api/News/c5d9b40a-86a2-4382-be38-cd093cadf0e0",
            false
        )

        val NEWS_LIST_BO = Collections.unmodifiableList(
            ArrayList<News>().apply {
                add(NEWS_BO)
                add(NEWS_BO2)
            }
        )


        val tags = Collections.unmodifiableList(
            ArrayList<Tag>().apply {
                add(Tag().apply {
                    tagName = "Urgent"
                    tagColor = "#d09d42"
                    tagBackgroundColor = "#000000"
                })
                add(Tag().apply {
                    tagName = "Urgent"
                    tagColor = "#d09d42"
                    tagBackgroundColor = "#000000"
                })
            })


        val INTERNAL_NEWS_DETAILS_BO = InternalNews(
            "c5d9b40a-86a2-4382-be38-cd093cadf0e0",
            "TEst API",
            "02 November 2020",
            "http://40.114.214.132:81/api/FileManger/Download/4d14ba22-010c-4b5b-a900-1f6f8a44f82b?Token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDQzNDYyNTAsImlzcyI6IjRkMTRiYTIyLTAxMGMtNGI1Yi1hOTAwLTFmNmY4YTQ0ZjgyYiJ9.IcawbYGnhZMIRMv6Zy0pVgBqQnRSErqyP_GlziOypjo",
            "Test description api",
            "http://40.114.214.132:82/api/News/c5d9b40a-86a2-4382-be38-cd093cadf0e0",
            false,
            tags
        )

        val INTERNAL_NEWS_BO = InternalNews(
            "f0001f01-bfa3-45d5-9c18-affc619da703",
            "TEst API",
            "02 November 2020",
            "http://40.114.214.132:81/api/FileManger/Download/008c7742-804c-4b8f-a635-7b57f092731a?Token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDQzNDU4NDQsImlzcyI6IjAwOGM3NzQyLTgwNGMtNGI4Zi1hNjM1LTdiNTdmMDkyNzMxYSJ9.wD1a1VsVmdZqBQ55QiLEOvIxYPSy1q1ZXXgxvApFx7Y",
            "Test description api",
            "http://40.114.214.132:82/api/News/f0001f01-bfa3-45d5-9c18-affc619da703",
            false,
            tags
        )

        val INTERNAL_NEWS_BO2 = InternalNews(
            "6688f1a3-daa3-4540-af41-41733a132dd0",
            "stringstringstringstringstringstringstring",
            "01 November 2020",
            "http://40.114.214.132:81/api/FileManger/Download/e86adab8-def5-4069-a242-2cc2249fe16f?Token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDQzNDU4NDQsImlzcyI6ImU4NmFkYWI4LWRlZjUtNDA2OS1hMjQyLTJjYzIyNDlmZTE2ZiJ9.c1iFhBnSARnV-ixyQ4eaRI5MdMCKEkBMq7UZN0j_jm4",
            "stringstringstringstringstringstringstring",
            "http://40.114.214.132:82/api/News/6688f1a3-daa3-4540-af41-41733a132dd0",
            false,
            tags
        )


        val INTERNAL_NEWS_LIST_BO = Collections.unmodifiableList(
            ArrayList<InternalNews>().apply {
                add(INTERNAL_NEWS_BO)
                add(INTERNAL_NEWS_BO2)
            }
        )


        val ATTACHMENT_REMOTE = RemoteAttachment(
            "http://40.114.214.132:82/api/Announcement/ae9077a7-f9b6-4e4f-8f57-2f848979d3bd",
            attachmentName = "test"
        )

        val ATTACHMENT_REMOTE_NULL = RemoteAttachment(
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )

        val ATTACHMENT_BO = Attachment(
            "http://40.114.214.132:82/api/Announcement/ae9077a7-f9b6-4e4f-8f57-2f848979d3bd",
            attachmentName = "test"
        )

        val ATTACHMENT_BO_DEFAULT = Attachment(
            "",
            attachmentName = ""
        )


        val ANNOUNCEMENT_REMOTE = RemoteAnnouncement(
            false,
            "http://40.114.214.132:82/api/Announcement/ae9077a7-f9b6-4e4f-8f57-2f848979d3bd",
            "2020-11-05T11:11:21.937",
            "h",
            "3",
            null,
            "ae9077a7-f9b6-4e4f-8f57-2f848979d3bd",
            "string123",
            null
        )

        val ANNOUNCEMENT_BO = Announcement(
            false,
            "http://40.114.214.132:82/api/Announcement/ae9077a7-f9b6-4e4f-8f57-2f848979d3bd",
            "05 November 2020",
            AnnouncementImportance.URGENT,
            "ae9077a7-f9b6-4e4f-8f57-2f848979d3bd",
            "string123",
            emptyList()
        )


        val ANNOUNCEMENT_BO2 = Announcement(
            false,
            "http://40.114.214.132:82/api/Announcement/beb0ba9c-01aa-4e87-8e42-e88e2d84c42c",
            "05 November 2020",
            AnnouncementImportance.URGENT,
            "beb0ba9c-01aa-4e87-8e42-e88e2d84c42c",
            "stringeeee",
            emptyList()
        )

        val ANNOUNCEMENTS_LIST_BO = Collections.unmodifiableList(
            ArrayList<Announcement>().apply {
                add(ANNOUNCEMENT_BO)
                add(ANNOUNCEMENT_BO2)
            }
        )

        val ATTACHMENT_DETAILS_BO = Attachment(
            "bd1f6385-c23d-49c0-987d-cc45ca71fd35",
            "string123"
        )


        val ANNOUNCEMENT_DETAILS_BO = Announcement(
            false,
            "http://40.114.214.132:82/api/Announcement/ae9077a7-f9b6-4e4f-8f57-2f848979d3bd",
            "05 November 2020",
            AnnouncementImportance.URGENT,
            "ae9077a7-f9b6-4e4f-8f57-2f848979d3bd",
            "string123",
            ArrayList<Attachment>().apply {
                add(ATTACHMENT_DETAILS_BO)
            }
        )


        val EVENT_BO = Event(
            id = "3fa85f64-5717-4562-b3fc-2c963f66afa6",
            description = "title1",
            startTime = "02:52 PM",
            startDay = "22",
            startMonth = "Nov",
            endTime = "03:05 PM",
            endDate = "25 Nov",
            photo = "photo1",
            location = "location1"
        )


        val EVENT_BO2 = Event(
            id = "3fa85f64-5717-4562-b3fc-2c123645afa6",
            description = "title2",
            startTime = "10:52 AM",
            startDay = "23",
            startMonth = "Sep",
            endTime = "11:52 AM",
            endDate = "02 Oct",
            photo = "photo2",
            location = "location2"
        )

        val EVENT_DETAILS_BO = Event(
            id = "3fa85f64-5717-4562-b3fc-2c963f66afa6",
            description = "title1",
            startTime = "02:52 PM",
            startDay = "22",
            startMonth = "Nov",
            endTime = "03:05 PM",
            endDate = "25 Nov",
            photo = "photo1",
            location = "location1"
        )


        val EVENTS_LIST_BO = listOf(EVENT_BO, EVENT_BO2)


        val OFFER_REMOTE = RemoteOffer(
            ArrayList<RemoteAttachment>().apply {
                add(ATTACHMENT_REMOTE)
            },
            "DFC",
            10,
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,",
            "What is Lorem",
            20,
            "2020-11-22T12:13:59.0089246",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,",
            "http://40.114.214.132:81/api/FileManger/Download/d111ad30-bde1-4d4e-a81d-8eddcaccb13f?Token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDY2MDAyMzksImlzcyI6ImQxMTFhZDMwLWJkZTEtNGQ0ZS1hODFkLThlZGRjYWNjYjEzZiJ9.tq6aRxG_YBLmx8vr-7-GZ_E1SeYhtdWNS47rnyQAiTc",
            true,
            "706f993c-609e-46f0-9e11-237445c65a57",
            "Category 1",
            true,
            "5b66b79c-7aa1-4df4-a119-f4bc8a0e14ce",
            "2020-11-22T11:30:42.221"
        )

        val OFFER_BO1 = Offer(
            ArrayList<Attachment>().apply {
                add(ATTACHMENT_BO)
            },
            "DFC",
            10,
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,",
            "What is Lorem",
            20,
            "22 November 2020",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,",
            "http://40.114.214.132:81/api/FileManger/Download/d111ad30-bde1-4d4e-a81d-8eddcaccb13f?Token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDY2MDAyMzksImlzcyI6ImQxMTFhZDMwLWJkZTEtNGQ0ZS1hODFkLThlZGRjYWNjYjEzZiJ9.tq6aRxG_YBLmx8vr-7-GZ_E1SeYhtdWNS47rnyQAiTc",
            true,
            "706f993c-609e-46f0-9e11-237445c65a57",
            "Category 1",
            true,
            "5b66b79c-7aa1-4df4-a119-f4bc8a0e14ce",
            "22 November 2020"
        )

        val OFFER_BO2 = Offer(
            ArrayList<Attachment>().apply {
                add(ATTACHMENT_BO)
            },
            "DFC",
            10,
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,",
            "What is Lorem",
            20,
            "22 November 2020",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,",
            "http://40.114.214.132:81/api/FileManger/Download/d111ad30-bde1-4d4e-a81d-8eddcaccb13f?Token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDY2MDAyMzksImlzcyI6ImQxMTFhZDMwLWJkZTEtNGQ0ZS1hODFkLThlZGRjYWNjYjEzZiJ9.tq6aRxG_YBLmx8vr-7-GZ_E1SeYhtdWNS47rnyQAiTc",
            true,
            "706f993c-609e-46f0-9e11-237445c65a57",
            "Category 1",
            true,
            "5b66b79c-7aa1-4df4-a119-f4bc8a0e14ce",
            "22 November 2020"
        )

        val OFFER_DETAILS_BO = Offer(
            ArrayList<Attachment>().apply {
                add(ATTACHMENT_BO)
            },
            "DFC",
            10,
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,",
            "What is Lorem",
            20,
            "22 November 2020",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,",
            "http://40.114.214.132:81/api/FileManger/Download/d111ad30-bde1-4d4e-a81d-8eddcaccb13f?Token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDY2MDA2NzUsImlzcyI6ImQxMTFhZDMwLWJkZTEtNGQ0ZS1hODFkLThlZGRjYWNjYjEzZiJ9.FTwj3vXsnFPGxenJCDNWebOEZtMDapMyKxes3poejKM",
            true,
            "706f993c-609e-46f0-9e11-237445c65a57",
            "Category 1",
            true,
            "5b66b79c-7aa1-4df4-a119-f4bc8a0e14ce",
            "22 November 2020"
        )

        val OFFERS_LIST = listOf(OFFER_BO1, OFFER_BO2)


        val DISCUSSIONS_BO = Discussions(
            "http://10.50.15.56/FileManager/api/FileManager/GetUserPhoto?userMail=diwandemoa@gazt.gov.sa".convertToAccessibleURL(),
            "diwandemoA",
            "project manager",
            "hi",
            "",
            RelativeDateTimeMapper().convert((0) to ("2021-05-07T23:28:41.1476082" ?: ""))
        )

    }
}