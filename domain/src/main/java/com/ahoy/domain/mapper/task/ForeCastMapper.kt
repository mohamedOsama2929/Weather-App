package com.ahoy.domain.mapper.task

import com.ahoy.domain.base.ModelMapper
import com.ahoy.entities.task.local.*
import com.ahoy.entities.task.remote.RemoteForecast
import javax.inject.Inject

class ForeCastMapper @Inject constructor(
) : ModelMapper<RemoteForecast, LocalForecast> {
    override fun convert(from: RemoteForecast?): LocalForecast {
        return from?.let {
            LocalForecast(
                name = it.location?.name ?: "",
                current = LocalCurrent(
                    feelsLikeC = it.current?.feelslikeC ?: 0.0,
                    windDegree = it.current?.windDegree ?: 0,
                    tempC = it.current?.tempC ?: 0.0,
                    tempF = it.current?.tempF ?: 0.0,
                    condition = LocalCondition(
                        code = it.current?.condition?.code ?: 0,
                        icon = it.current?.condition?.icon ?: "",
                        text = it.current?.condition?.text ?: ""
                    ), windMph = it.current?.windMph ?: 0.0,
                    humidity = it.current?.humidity ?: 0,
                    lastUpdated = it.current?.lastUpdated ?: ""
                ),
                forecast = Forecast(
                    foreCastDay = it.forecast?.forecastday?.map { foreCastDay ->
                        ForeCastDayItem(
                            date = foreCastDay?.date ?: "",
                            dateEpoch = foreCastDay?.dateEpoch ?: 0,
                            day = Day(
                                avgTempF = foreCastDay?.day?.avgtempF ?: 0.0,
                                avgTempC = foreCastDay?.day?.avgtempC ?: 0.0,
                                avgHumidity = foreCastDay?.day?.avghumidity ?: 0.0,
                                condition = LocalCondition(
                                    code = foreCastDay?.day?.condition?.code ?: 0,
                                    icon = foreCastDay?.day?.condition?.icon ?: "",
                                    text = foreCastDay?.day?.condition?.text ?: ""
                                ),
                                maxWindMph = foreCastDay?.day?.maxwindMph ?: 0.0,
                            )
                        )
                    }
                ),
                location = LocalLocation(
                    localtime = it.location?.localtime ?: "",
                    country = it.location?.country ?: "",
                    localtimeEpoch = it.location?.localtimeEpoch ?: 0,
                    name = it.location?.name ?: "",
                    lon = it.location?.lon ?: 0.0,
                    lat = it.location?.lat ?: 0.0,
                    region = it.location?.region ?: ""
                )
            )
        } ?: LocalForecast()
    }
}