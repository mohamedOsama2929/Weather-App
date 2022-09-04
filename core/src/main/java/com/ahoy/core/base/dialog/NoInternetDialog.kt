package com.ahoy.core.base.dialog

import android.content.Context
import com.ahoy.entities.base.ErrorStatus

object NoInternetDialog : BaseNetworkingDialog() {
    override fun showDialog(context: Context, errorStatus: ErrorStatus) {

    }
}
