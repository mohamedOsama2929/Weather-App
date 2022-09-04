package com.ahoy.task.ui.fragment.task.adapter

import android.annotation.SuppressLint
import android.view.View
import com.ahoy.core.base.adapter.diffutilsAdapter.BaseRecyclerAdapter
import com.ahoy.entities.task.local.ForeCastDayItem
import com.ahoy.task.R
import com.ahoy.task.ui.fragment.task.convertDateToFormat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_day_weather.view.*

class DaysWeatherAdapter() :
    BaseRecyclerAdapter<ForeCastDayItem>({ _, _ -> false }) {


    override val itemLayoutRes: Int = R.layout.item_day_weather

    @SuppressLint("SetTextI18n")
    override fun bind(view: View, item: ForeCastDayItem, position: Int) {
        view.apply {

            tvHumidityNumber.text =
                item.day.avgHumidity.toString() + "\u00B0"

            if (item.isF) {
                tvTemperatureNumber.text =
                    item.day.avgTempF.toString() + "\u00B0" + "F"
            } else {
                tvTemperatureNumber.text =
                    item.day.avgTempC.toString() + "\u00B0" + "C"
            }
            tvDateName.text =
                convertDateToFormat(item.date)
            Glide.with(context)
                .load("https:${item.day.condition?.icon}")
                .into(img)
        }
    }

}