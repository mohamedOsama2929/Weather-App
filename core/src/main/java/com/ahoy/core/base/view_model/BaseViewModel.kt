package com.ahoy.core.base.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahoy.core.base.fragment.NetworkBaseFragment
import com.ahoy.domain.base.CompletionBlock
import com.ahoy.entities.base.ErrorModel
import com.ahoy.entities.base.ErrorStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {
    private val isLoading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val isLoadingLiveData: LiveData<Boolean> by lazy { isLoading }

    private val error: MutableLiveData<ErrorModel> by lazy { MutableLiveData<ErrorModel>() }
    val errorLiveData: LiveData<ErrorModel> by lazy { error }

    private val cancellationMessage: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val cancellationMsgLiveData: LiveData<String> by lazy { cancellationMessage }


    fun setLoading(isLoading: Boolean) {
        this.isLoading.value = isLoading
    }

    fun setErrorReason(errorModel: ErrorModel) {
        error.value = errorModel
    }

    fun setCancellationReason(cancellationException: CancellationException) {
        cancellationMessage.value = cancellationException.message
    }

    fun <T> callApi(data: MutableLiveData<T>, apiCall: (CompletionBlock<T>) -> Unit) {
        if (NetworkBaseFragment.isNetworkConnected) {
            apiCall.invoke {
                isLoading {
                    isLoading.value = it
                }

                onComplete {
                    data.value = it!!
                }
                onError { throwable ->
                    error.value = throwable

                }
                onCancel {
                    cancellationMessage.value = it.message
                }
            }
        } else {
            error.value = ErrorModel("No internet connection", 0, ErrorStatus.NO_CONNECTION)
        }
    }


    fun <T> callDB(data: MutableLiveData<T>, apiCall: (CompletionBlock<T>) -> Unit) {
        if (NetworkBaseFragment.isNetworkConnected) {
            apiCall.invoke {

                isLoading {
                    isLoading.value = it
                }

                onComplete {
                    data.value = it!!
                }
                onError { throwable ->
                    error.value = throwable

                }
                onCancel {
                    cancellationMessage.value = it.message
                }
            }
        } else {
            error.value = ErrorModel("No DB connection", 0, ErrorStatus.NO_CONNECTION)
        }
    }

    fun <T> callApi(data: ConflatedBroadcastChannel<T>, apiCall: (CompletionBlock<T>) -> Unit) {
        if (NetworkBaseFragment.isNetworkConnected) {
            apiCall.invoke {
                isLoading {
                    isLoading.value = it
                }
                onComplete {
                    viewModelScope.launch {
                        data.offer(it)
                    }
                }
                onError { throwable ->
                    error.value = throwable
                }
                onCancel {
                    cancellationMessage.value = it.message
                }
            }
        } else {
            error.value = ErrorModel("No internet connection", 0, ErrorStatus.NO_CONNECTION)
        }
    }
}