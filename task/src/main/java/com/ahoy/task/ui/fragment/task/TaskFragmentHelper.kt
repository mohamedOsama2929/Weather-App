package com.ahoy.task.ui.fragment.task

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.ahoy.entities.task.local.LocalForecast
import com.ahoy.entities.task.local.SearchResultItem
import com.ahoy.task.ui.fragment.task.adapter.CitiesDropDownAdapter
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun convertDateToDayName(dayDate: String): String {
    val inFormat = SimpleDateFormat("yyyy-MM-dd")
    val date: Date = inFormat.parse(dayDate)
    val outFormat = SimpleDateFormat("EEEE")
    return outFormat.format(date)
}

fun convertDateToFormat(dayDate: String): String {
    val originalFormat: DateFormat = SimpleDateFormat(
        "yyyy" +
                "-MM-dd", Locale.ENGLISH
    )
    val targetFormat: DateFormat = SimpleDateFormat("dd MMM yyyy")
    val date: Date = originalFormat.parse(dayDate)
    return targetFormat.format(date)
}

fun convertDateToTime(lastUpdate: String): String {
    val originalFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH)
    val targetFormat: DateFormat = SimpleDateFormat("hh:mm a")
    val date: Date = originalFormat.parse(lastUpdate)
    return targetFormat.format(date)
}

var isOptionsMenuOpened = false

internal class SearchDropDownUiHelper(
    private val textField: EditText,
    private val rvCities: RecyclerView,
    private val expandUpLayout: LinearLayout,
    private val clearSearchImg: ImageView,
    private val expandUpImg: ImageView,
    private val adapter: CitiesDropDownAdapter,
    private val onValidSearchQuerySubmitted: (String) -> Unit,
) {

    private val provideTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            CoroutineScope(Dispatchers.Main).launch {
                delay(1000)
                if (s.toString().trim().isNotEmpty() && (s?.length ?: 0) >= 1) {
                    onValidSearchQuerySubmitted(s.toString())
                    clearSearchImg.isVisible = true
                } else {
                    updateCitiesDropDownAdapter(arrayListOf())
                    clearSearchImg.isVisible = false
                }
            }
        }
    }

    private fun onActionTextFieldActionDone() {
        textField.setOnEditorActionListener { textView, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (textView.text.toString().trim().isNotEmpty()) {
                    onValidSearchQuerySubmitted(textView.text.toString().trim())
                } else {
                    updateCitiesDropDownAdapter(arrayListOf())
                }
            }
            false
        }
    }

    internal fun handleSearchDropdown() {
        rvCities.adapter = adapter
        textField.addTextChangedListener(provideTextWatcher)
        onActionTextFieldActionDone()
        expandUpImg.setOnClickListener {
            rvCities.isVisible = false
            expandUpLayout.isVisible = false
        }
    }

    internal fun updateCitiesDropDownAdapter(
        city: List<SearchResultItem>
    ) {
        adapter.submitList(city)
        if (city.isNotEmpty()) {
            rvCities.visibility = View.VISIBLE
            expandUpLayout.isVisible = true
            isOptionsMenuOpened = true
        } else {
            rvCities.visibility = View.GONE
            expandUpLayout.isVisible = false
            isOptionsMenuOpened = false
        }
    }

    internal fun updateCitiesDropdownText(
        updatedText: String
    ) {
        textField.apply {
            clearFocus()
            removeTextChangedListener(provideTextWatcher)
            setText(updatedText)
            addTextChangedListener(provideTextWatcher)
        }
        isOptionsMenuOpened = false
        rvCities.visibility = View.GONE
    }

}

fun generateFile(context: Context, fileName: String): File? {
    val csvFile = File(context.filesDir, fileName)
    csvFile.createNewFile()

    return if (csvFile.exists()) {
        csvFile
    } else {
        null
    }
}

fun goToFileIntent(context: Context, file: File): Intent {
    val intent = Intent(Intent.ACTION_VIEW)
    val contentUri =
        FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
    val mimeType = context.contentResolver.getType(contentUri)
    intent.setDataAndType(contentUri, mimeType)
    intent.flags =
        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION

    return intent
}

fun exportMoviesWithDirectorsToCSVFile(csvFile: File, list: List<LocalForecast>) {
    csvWriter().open(csvFile, append = false) {
        // Header
        list.forEachIndexed { index, city ->
            writeRow(listOf(index, city.name, city.current, city.location, city.forecast))
        }
    }
}

