package com.ahoy.core.base.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView


abstract class BaseViewHolder<T>(val context: Context, view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bindData(data: T)
}