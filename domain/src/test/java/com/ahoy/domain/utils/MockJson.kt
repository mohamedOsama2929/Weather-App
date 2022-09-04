package com.ahoy.domain.utils

import com.ahoy.domain.utils.Constants.ANNOUNCEMENT_DETAILS_FILENAME
import com.ahoy.domain.utils.Constants.ANNOUNCEMENT_FAVORITE_FILENAME
import com.ahoy.domain.utils.Constants.ANNOUNCEMENT_FILENAME
import com.ahoy.domain.utils.Constants.CHANGE_ANNOUNCEMENT_STATUS_FILENAME
import com.ahoy.domain.utils.Constants.CHANGE_INTERNAL_NEWS_STATUS_FILENAME
import com.ahoy.domain.utils.Constants.CHANGE_NEWS_FAVORITE_FILENAME
import com.ahoy.domain.utils.Constants.EVENTS_FILENAME
import com.ahoy.domain.utils.Constants.EVENT_DETAILS_FILENAME
import com.ahoy.domain.utils.Constants.INTERNAL_NEWS_DETAILS_FILENAME
import com.ahoy.domain.utils.Constants.INTERNAL_NEWS_FILENAME
import com.ahoy.domain.utils.Constants.NEWS_DETAILS_FILENAME
import com.ahoy.domain.utils.Constants.NEWS_FILENAME
import com.ahoy.domain.utils.Constants.OFFERS_LIST_FILENAME
import com.ahoy.domain.utils.Constants.OFFER_DETAILS_FILENAME
import com.ahoy.entities.announcements.response.AnnouncementDetailsResponse
import com.ahoy.entities.announcements.response.AnnouncementsListResponse
import com.ahoy.entities.announcements.response.ChangeAnnouncementFavouriteStatusResponse
import com.ahoy.entities.discussions.query.AddMessageQuery
import com.ahoy.entities.discussions.response.AddMessageResponse
import com.ahoy.entities.discussions.response.DiscussionsResponse
import com.ahoy.entities.events.response.EventDetailsResponse
import com.ahoy.entities.events.response.EventsListResponse
import com.ahoy.entities.internal_news.favorite.response.ChangeInternalNewsFavouriteStatusResponse
import com.ahoy.entities.internal_news.favorite.response.InternalNewsDetailsResponse
import com.ahoy.entities.internal_news.favorite.response.InternalNewsListResponse
import com.ahoy.entities.news.favorite.response.ChangeNewsFavouriteStatusResponse
import com.ahoy.entities.news.favorite.response.NewsDetailsResponse
import com.ahoy.entities.news.favorite.response.NewsListResponse
import com.ahoy.entities.offers.response.OfferDetailsResponse
import com.ahoy.entities.offers.response.OffersListResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject


open class MockJson
@Inject
constructor() {
    /**
     * Reads input file and converts to json
     */
    fun getJson(fileName: String): String {
        val bytes = javaClass.classLoader!!.getResourceAsStream(fileName).readBytes()
        return String(bytes)
    }


    fun getNewsList(): NewsListResponse {
        val rawJson = getJson(NEWS_FILENAME)
        return Gson().fromJson(
            rawJson,
            object : TypeToken<NewsListResponse>() {}.type
        )
    }


    fun getFavouriteNewsList(): NewsListResponse {
        val rawJson = getJson(NEWS_FILENAME)
        return Gson().fromJson(
            rawJson,
            object : TypeToken<NewsListResponse>() {}.type
        )
    }


    fun getNewsDetails(): NewsDetailsResponse {
        val rawJson = getJson(NEWS_DETAILS_FILENAME)
        return Gson().fromJson(
            rawJson,
            object : TypeToken<NewsDetailsResponse>() {}.type
        )
    }


    fun changeFavouriteNewsStatus(): ChangeNewsFavouriteStatusResponse {
        val rawJson = getJson(CHANGE_NEWS_FAVORITE_FILENAME)
        return Gson().fromJson(
            rawJson,
            object : TypeToken<ChangeNewsFavouriteStatusResponse>() {}.type
        )
    }


    fun getAnnouncementList(): AnnouncementsListResponse {
        val rawJson = getJson(ANNOUNCEMENT_FILENAME)
        return Gson().fromJson(
            rawJson,
            object : TypeToken<AnnouncementsListResponse>() {}.type
        )
    }


    fun getAnnouncementFavoritesList(): AnnouncementsListResponse {
        val rawJson = getJson(ANNOUNCEMENT_FAVORITE_FILENAME)
        return Gson().fromJson(
            rawJson,
            object : TypeToken<AnnouncementsListResponse>() {}.type
        )
    }

    fun getAnnouncementDetails(): AnnouncementDetailsResponse {
        val rawJson = getJson(ANNOUNCEMENT_DETAILS_FILENAME)
        return Gson().fromJson(
            rawJson,
            object : TypeToken<AnnouncementDetailsResponse>() {}.type
        )
    }

    fun changeFavouriteAnnouncementStatus(): ChangeAnnouncementFavouriteStatusResponse {
        val rawJson = getJson(CHANGE_ANNOUNCEMENT_STATUS_FILENAME)
        return Gson().fromJson(
            rawJson,
            object : TypeToken<ChangeAnnouncementFavouriteStatusResponse>() {}.type
        )
    }


    fun getInternalNewsList(): InternalNewsListResponse {
        val rawJson = getJson(INTERNAL_NEWS_FILENAME)
        return Gson().fromJson(
            rawJson,
            object : TypeToken<InternalNewsListResponse>() {}.type
        )
    }

    fun getInternalFavouriteNewsList(): InternalNewsListResponse {
        val rawJson = getJson(INTERNAL_NEWS_FILENAME)
        return Gson().fromJson(
            rawJson,
            object : TypeToken<InternalNewsListResponse>() {}.type
        )
    }

    fun getInternalNewsDetails(): InternalNewsDetailsResponse {
        val rawJson = getJson(INTERNAL_NEWS_DETAILS_FILENAME)
        return Gson().fromJson(
            rawJson,
            object : TypeToken<InternalNewsDetailsResponse>() {}.type
        )
    }

    fun changeInternalFavouriteNewsStatus(): ChangeInternalNewsFavouriteStatusResponse {
        val rawJson = getJson(CHANGE_INTERNAL_NEWS_STATUS_FILENAME)
        return Gson().fromJson(
            rawJson,
            object : TypeToken<ChangeInternalNewsFavouriteStatusResponse>() {}.type
        )
    }


    fun getEventsList(): EventsListResponse {
        val rawJson = getJson(EVENTS_FILENAME)
        return Gson().fromJson(
            rawJson,
            object : TypeToken<EventsListResponse>() {}.type
        )
    }


    fun getEventDetails(): EventDetailsResponse {
        val rawJson = getJson(EVENT_DETAILS_FILENAME)
        return Gson().fromJson(
            rawJson,
            object : TypeToken<EventDetailsResponse>() {}.type
        )
    }

    fun getOffersList(): OffersListResponse {
        val rawJson = getJson(OFFERS_LIST_FILENAME)
        return Gson().fromJson(
            rawJson,
            object : TypeToken<OffersListResponse>() {}.type
        )
    }

    fun getOfferDetails(): OfferDetailsResponse {
        val rawJson = getJson(OFFER_DETAILS_FILENAME)
        return Gson().fromJson(
            rawJson,
            object : TypeToken<OfferDetailsResponse>() {}.type
        )
    }


    fun addMessage(query: AddMessageQuery): AddMessageResponse {
        val rawJson = getJson(Constants.SEND_MESSAGE_FILENAME)
        return Gson().fromJson(
            rawJson,
            object : TypeToken<AddMessageResponse>() {}.type
        )
    }

    fun getDiscussions(requestId: String): DiscussionsResponse {
        val rawJson = getJson(Constants.DISCUSSIONS_FILENAME)
        return Gson().fromJson(
            rawJson,
            object : TypeToken<DiscussionsResponse>() {}.type
        )
    }
}