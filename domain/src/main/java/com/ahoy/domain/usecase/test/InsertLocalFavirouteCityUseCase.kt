package com.ahoy.domain.usecase.test

import com.ahoy.data.mapper.CloudErrorMapper
import com.ahoy.data.repository.AppRepository
import com.ahoy.domain.base.RemoteUseCase
import com.ahoy.entities.task.local.LocalForecast
import javax.inject.Inject

class InsertLocalFavirouteCityUseCase @Inject constructor(
    errorUtil: CloudErrorMapper,
    private val appRepository: AppRepository,
) : RemoteUseCase<LocalForecast, Unit, Unit>(errorUtil) {


    public override suspend fun convert(dto: Unit) {
        return dto
    }

    public override suspend fun executeOnBackground(parameters: LocalForecast) {
        return appRepository.insertFavirouteCity(parameters)
    }

}