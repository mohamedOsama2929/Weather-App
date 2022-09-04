package com.ahoy.core.custom_views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.ahoy.core.R


class CustomRadioButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val TEXT_SIZE = 12f

    private var mmTextSize = 0f
    private var mmTypeface: Typeface? = null

    private lateinit var rootLayout: LinearLayout
    private var optionItems = listOf<RadioButtonOptionItem>()
    private var switchListener: ((RadioButtonOptionItem) -> Unit)? = null

    init {
        initAttr(attrs)
        initViews()
    }

    private fun initAttr(attrs: AttributeSet?) {
        val typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CustomRadioButton)
        mmTextSize = typedArray.getDimension(R.styleable.CustomRadioButton_textSize, TEXT_SIZE)
        val mTypeface: String? = typedArray.getString(R.styleable.CustomRadioButton_typeface)
        if (!TextUtils.isEmpty(mTypeface)) {
            mmTypeface = Typeface.createFromAsset(context.assets, mTypeface)
        }
        typedArray.recycle()
    }

    private fun initViews() {
        rootLayout = LayoutInflater.from(context)
            .inflate(R.layout.custom_radio_button_layout, this, false) as LinearLayout
        addView(rootLayout)
    }

    fun addOptionItems(items: List<RadioButtonOptionItem>) {
        optionItems = items
        rootLayout.weightSum = items.size.toFloat()
        items.forEach {
            rootLayout.addView(getTextView(it))
        }
        selectCertainView(items[0].id)
    }

    private fun getTextView(item: RadioButtonOptionItem): TextView {
        val tv = TextView(context)
        tv.apply {
            text = item.text
            tag = item.id
            setOnClickListener(provideOnClickListener())
            val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f)
                .also { gravity = Gravity.CENTER }.also { setPadding(4, 0, 4, 0) }
            layoutParams = params
            textSize = mmTextSize
            typeface = mmTypeface
        }
        return tv
    }

    private fun provideOnClickListener(): OnClickListener {
        return OnClickListener {
            selectCertainView(it.tag.toString())
            switchListener?.invoke(getSelectedOptionItem(it.tag.toString()))
        }
    }


    private fun TextView.setSelectedTextView() {
        setBackgroundColor(ContextCompat.getColor(context, R.color.dark_blue_green))
        setTextColor(Color.WHITE)
    }

    private fun TextView.setUnSelectedTextView() {
        setBackgroundColor(Color.WHITE)
        setTextColor(ContextCompat.getColor(context, R.color.textColor))
    }

    private fun selectCertainView(viewTag: String) {
        rootLayout.children.iterator().forEach {
            (it as TextView).setUnSelectedTextView()
        }
        rootLayout.findViewWithTag<TextView>(viewTag).setSelectedTextView()
    }

    private fun getSelectedOptionItem(itemId: String): RadioButtonOptionItem {
        return optionItems.find { it.id == itemId }!!
    }

    fun addOnSwitchListener(listener: (RadioButtonOptionItem) -> Unit) {
        this.switchListener = listener
    }

}

data class RadioButtonOptionItem(val text: String, val id: String)
