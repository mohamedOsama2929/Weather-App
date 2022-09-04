package com.ahoy.domain.usecase.test

import com.ahoy.data.mapper.CloudErrorMapper
import com.ahoy.data.repository.AppRepository
import com.ahoy.domain.base.RemoteUseCase
import com.ahoy.domain.mapper.task.ForeCastMapper
import com.ahoy.entities.task.local.LocalForecast
import com.ahoy.entities.task.query.ForeCastQuery
import com.ahoy.entities.task.remote.RemoteForecast
import javax.inject.Inject

class GetForeCastUseCase @Inject constructor(
    errorUtil: CloudErrorMapper,
    private val appRepository: AppRepository,
    private val mapper: ForeCastMapper
) : RemoteUseCase<ForeCastQuery, RemoteForecast, LocalForecast>(errorUtil) {


    public override suspend fun convert(dto: RemoteForecast): LocalForecast {
        return mapper.convert(dto)
    }

    public override suspend fun executeOnBackground(parameters: ForeCastQuery): RemoteForecast {
        return appRepository.getForeCast(parameters)
    }

}