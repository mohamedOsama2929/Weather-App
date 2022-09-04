package com.ahoy.core.base.activity

import com.ahoy.entities.side_menu.SideMenuEnum

interface SideMenuListener {

    fun onSideMenuItemSelected(item: SideMenuEnum)

    fun closeSideMenu()
    fun onMyProfileClicked()
}