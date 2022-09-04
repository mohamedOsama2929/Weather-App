package com.ahoy.core.base.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahoy.domain.base.CompletionBlock
import com.ahoy.entities.base.ResponsePagingResultModel
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
abstract class BasePagingViewModel<T> : BaseViewModel() {

    var pageNumber = 0
    open var pageSize = 10
    var loadMore = false

    private val pagingListMutable = MutableLiveData<ResponsePagingResultModel<T>>()
    val pagingList: LiveData<ResponsePagingResultModel<T>> = pagingListMutable

    fun setPagingListLiveData(data: ResponsePagingResultModel<T>) {
        pagingListMutable.value = data
    }

    open fun initPageNumber() {
        if (pageNumber == 0) {
            pageNumber = 1
            callPagingApi()
        }
    }


    open fun resetPageNumber() {
        pageNumber = 1
        loadMore = false
        callPagingApi()
    }

    fun incrementAndFetchPage() {
        pageNumber++
        callPagingApi()
    }

    fun callPagingApi() = callApi(pagingListMutable, ::fetchPage)

    abstract fun fetchPage(completionBlock: CompletionBlock<ResponsePagingResultModel<T>>)
}