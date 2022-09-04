package com.ahoy.domain.usecase.test

import com.ahoy.data.mapper.CloudErrorMapper
import com.ahoy.data.repository.AppRepository
import com.ahoy.domain.base.RemoteUseCase
import com.ahoy.domain.mapper.task.SearchMapper
import com.ahoy.entities.task.local.SearchResultItem
import com.ahoy.entities.task.query.SearchQuery
import com.ahoy.entities.task.remote.RemoteSearchResultItem
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    errorUtil: CloudErrorMapper,
    private val appRepository: AppRepository,
    private val mapper: SearchMapper
) : RemoteUseCase<SearchQuery, List<RemoteSearchResultItem>, List<SearchResultItem>>(errorUtil) {


    public override suspend fun convert(dto: List<RemoteSearchResultItem>): List<SearchResultItem> {
        return mapper.convert(dto)
    }

    public override suspend fun executeOnBackground(parameters: SearchQuery): List<RemoteSearchResultItem> {
        return appRepository.getCities(parameters)
    }

}