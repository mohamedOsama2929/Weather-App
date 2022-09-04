package com.ahoy.core.base.adapter

import android.util.TypedValue
import android.view.View
import com.ahoy.core.R
import com.ahoy.core.base.adapter.diffutilsAdapter.BaseRecyclerAdapter
import com.ahoy.entities.side_menu.SideMenuEnum
import com.ahoy.entities.side_menu.SideMenuItem
import kotlinx.android.synthetic.main.item_side_menu.view.*


class SideMenuItemsAdapter(
    private val selectedItem: SideMenuEnum,
    private val onItemSelected: (SideMenuEnum) -> Unit
) : BaseRecyclerAdapter<SideMenuItem>({ oldItem, newItem -> oldItem.sideMenu == newItem.sideMenu }) {

    override val itemLayoutRes = R.layout.item_side_menu

    override fun bind(view: View, item: SideMenuItem, position: Int) {


        view.run {

            tv_item_name.text = view.context.getString(item.textRes)
            img_icon.setImageResource(item.iconRes)

            img_icon.alpha = if (item.sideMenu == selectedItem) 1.0f else 0.8f

            // set bg of the view to be color ocean_green if selected or to apply ripple effect
            val outValue = TypedValue()
            context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)

            rootView.setBackgroundResource(if (item.sideMenu == selectedItem) R.color.ocean_green_semi_trans else outValue.resourceId)

            rootView.setOnClickListener {
                onItemSelected(item.sideMenu)
            }
        }

    }
}