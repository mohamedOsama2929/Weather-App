package com.ahoy.domain.usecase.test

import com.ahoy.data.mapper.CloudErrorMapper
import com.ahoy.data.repository.AppRepository
import com.ahoy.domain.base.RemoteUseCase
import com.ahoy.entities.task.local.LocalForecast
import javax.inject.Inject

class GetLocalFavirouteCitiesUseCase @Inject constructor(
    errorUtil: CloudErrorMapper,
    private val appRepository: AppRepository,
) : RemoteUseCase<Unit, List<LocalForecast>, List<LocalForecast>>(errorUtil) {


    public override suspend fun convert(dto: List<LocalForecast>): List<LocalForecast> {
        return dto
    }

    public override suspend fun executeOnBackground(parameters: Unit): List<LocalForecast> {
        return appRepository.getLocalFavirouteCities()
    }

}