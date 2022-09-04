package com.ahoy.core.utils

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

fun Bundle.putEnum(key: String, enum: Enum<*>) {
    this.putString(key, enum.name)
}

inline fun <reified T : Enum<*>> Bundle.getEnum(key: String): T {
    return enumValueOf(getString(key) ?: "")
}

fun View.showPopup(menuRes: Int, onItemClickAction: (Int) -> Boolean) {
    val popup = PopupMenu(this.context, this)
    popup.setOnMenuItemClickListener {
        onItemClickAction(it.itemId)
    }
    val inflater = popup.menuInflater
    inflater.inflate(menuRes, popup.menu)
    popup.show()
}

fun Fragment.showDialog(dialog: DialogFragment) {
    dialog.show(childFragmentManager, dialog::class.java.name)
}

fun Fragment.getIntent(): Intent? {
    return requireActivity().intent
}


inline fun <T, R> T.convertTo(transform: (T) -> R): R {
    return transform(this)
}
