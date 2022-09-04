package com.ahoy.core.base.adapter

import androidx.recyclerview.widget.RecyclerView


abstract class BaseRecycleViewAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private var data: MutableList<T>


    init {
        data = ArrayList()
    }

    fun setData(data: List<T>) {
        if (data.isNotEmpty()) {
            this.data = data as MutableList<T>
            notifyDataSetChanged()
        }
    }

    fun clear() {
        this.data.clear()
        notifyDataSetChanged()
    }

    fun getData(): MutableList<T> {
        return this.data
    }

    fun addData(data: List<T>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun addItem(data: T) {
        this.data.add(data)
        if (this.data.size == 0) {
            notifyItemChanged((this.data.size))
        } else
            notifyItemChanged((this.data.size - 1))
    }

    fun removeItem(data: T, position: Int) {
        this.data.remove(data)
        notifyItemRemoved(position)
    }

    fun getItem(position: Int): T {
        return data[position]
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bindData(getItem(position))
    }
}
