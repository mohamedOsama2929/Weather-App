package com.ahoy.task.ui.fragment.task.adapter

import android.annotation.SuppressLint
import android.view.View
import com.ahoy.core.base.adapter.diffutilsAdapter.BaseRecyclerAdapter
import com.ahoy.entities.task.local.SearchResultItem
import com.ahoy.task.R
import kotlinx.android.synthetic.main.simple_drop_down_item.view.*

class CitiesDropDownAdapter(private val onDropDownItemSelected: (SearchResultItem) -> Unit) :
    BaseRecyclerAdapter<SearchResultItem>({ _, _ -> false }) {

    private var selectedItem: SearchResultItem? = null

    override val itemLayoutRes: Int = R.layout.simple_drop_down_item

    @SuppressLint("SetTextI18n")
    override fun bind(view: View, item: SearchResultItem, position: Int) {
        view.apply {

            tvCityName.text = item.name
            tvCityRegion.text = "- ${item.region}"

            setOnClickListener {
                onDropDownItemSelected(item)
                selectedItem = item
            }
        }
    }

    fun getSelectedItem(): SearchResultItem? = selectedItem
}