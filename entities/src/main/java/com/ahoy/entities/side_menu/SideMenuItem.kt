package com.ahoy.entities.side_menu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class SideMenuItem(
    @StringRes val textRes: Int,
    @DrawableRes val iconRes: Int,
    val sideMenu: SideMenuEnum
)