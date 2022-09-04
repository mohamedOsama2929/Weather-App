package com.ahoy.task.ui.fragment.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahoy.core.base.view_model.BaseViewModel
import com.ahoy.domain.usecase.test.GetCitiesUseCase
import com.ahoy.domain.usecase.test.GetForeCastUseCase
import com.ahoy.domain.usecase.test.GetLocalFavirouteCitiesUseCase
import com.ahoy.domain.usecase.test.InsertLocalFavirouteCityUseCase
import com.ahoy.entities.task.local.LocalForecast
import com.ahoy.entities.task.local.SearchResultItem
import com.ahoy.entities.task.query.ForeCastQuery
import com.ahoy.entities.task.query.SearchQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getForeCastUseCase: GetForeCastUseCase,
    private val getCitiesUseCase: GetCitiesUseCase,
    private val insertLocalFavirouteCityUseCase: InsertLocalFavirouteCityUseCase,
    private val getLocalFavirouteCitiesUseCase: GetLocalFavirouteCitiesUseCase,
) : BaseViewModel() {

    private val forecastMutable = MutableLiveData<LocalForecast>()

    val forecastLiveData: LiveData<LocalForecast> =
        forecastMutable

    fun getForeCast(cityName: String) {
        callApi(forecastMutable) {
            getForeCastUseCase.execute(ForeCastQuery(cityName = cityName), it)
        }
    }


    private val insertFavirouteCityMutable = MutableLiveData<Unit>()

    fun insertFavirouteCity(cityWeather: LocalForecast) {
        callDB(insertFavirouteCityMutable) {
            insertLocalFavirouteCityUseCase.execute(cityWeather) {
                onComplete {
                    insertFavirouteCityMutable.postValue(it)
                }
            }
        }
    }


    private val getFavirouteCitiesMutable = MutableLiveData<List<LocalForecast>>()

    val getFavirouteCitiesLiveData: LiveData<List<LocalForecast>> =
        getFavirouteCitiesMutable

    fun getFavirouteCities() {
        callDB(getFavirouteCitiesMutable) {
            getLocalFavirouteCitiesUseCase.execute(Unit, it)
        }
    }

    private val downloadFavirouteCitiesMutable = MutableLiveData<List<LocalForecast>>()

    val downloadFavirouteCitiesLiveData: LiveData<List<LocalForecast>> =
        downloadFavirouteCitiesMutable

    fun downloadFavirouteCities() {
        callDB(downloadFavirouteCitiesMutable) {
            getLocalFavirouteCitiesUseCase.execute(Unit, it)
        }
    }

    private var searchMutable = MutableLiveData<List<SearchResultItem>>()

    val searchLiveData: LiveData<List<SearchResultItem>> =
        searchMutable

    fun searchCity(cityName: String) {
        callApi(searchMutable) {
            getCitiesUseCase.execute(SearchQuery(cityName = cityName)) {
                onComplete {
                    searchMutable.postValue(it)
                }
            }
        }
    }
}