package com.ahoy.task.ui.fragment.task.adapter

import android.annotation.SuppressLint
import android.view.View
import com.ahoy.core.base.adapter.diffutilsAdapter.BaseRecyclerAdapter
import com.ahoy.entities.task.local.LocalForecast
import com.ahoy.task.R
import kotlinx.android.synthetic.main.item_faviourte_city.view.*

class FavirouteCitiesAdapter(private val onItemSelected: (LocalForecast) -> Unit) :
    BaseRecyclerAdapter<LocalForecast>({ _, _ -> false }) {

    private var selectedItem: LocalForecast? = null

    override val itemLayoutRes: Int = R.layout.item_faviourte_city

    @SuppressLint("SetTextI18n")
    override fun bind(view: View, item: LocalForecast, position: Int) {
        view.apply {

            tvFavoriteCityName.text = item.location.name

            setOnClickListener {
                onItemSelected(item)
                selectedItem = item
            }
        }
    }

    fun getSelectedItem(): LocalForecast? = selectedItem
}