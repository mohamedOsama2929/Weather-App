package com.ahoy.task.ui.fragment.task

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.ahoy.core.base.fragment.BaseFragment
import com.ahoy.core.utils.CurrentLocation
import com.ahoy.core.utils.showDialog
import com.ahoy.entities.task.local.ForeCastDayItem
import com.ahoy.entities.task.local.LocalForecast
import com.ahoy.entities.task.local.SearchResultItem
import com.ahoy.task.R
import com.ahoy.task.databinding.FragmentTaskBinding
import com.ahoy.task.ui.fragment.task.adapter.CitiesDropDownAdapter
import com.ahoy.task.ui.fragment.task.adapter.DaysWeatherAdapter
import com.ahoy.task.ui.fragment.task.dialog.FavirouteCitiesBottomSheet
import com.bumptech.glide.Glide
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 * mohamedosama2929@gmail.com
 * api key
 * 35f50bcdc90844ba8ec121932221808
 *
 */
@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class TaskFragment : BaseFragment<FragmentTaskBinding, TaskViewModel>(
    FragmentTaskBinding::inflate
), View.OnClickListener {


    private var firstTime: Boolean = true
    private lateinit var weatherData: LocalForecast
    override val viewModel: TaskViewModel by viewModels()

    private val searchDropDownUiHelper: SearchDropDownUiHelper by lazy {
        SearchDropDownUiHelper(
            binding.searchDropDown.etSearchDropDown,
            binding.searchDropDown.rvSearch,
            binding.searchDropDown.layoutExpandUp,
            binding.searchDropDown.imgCloseSearch,
            binding.searchDropDown.imgExpandUp,
            citiesDropDownAdapter,
            provideOnValidCitySearchQuerySubmitted()
        )
    }
    private var citiesDropDownAdapter =
        CitiesDropDownAdapter(provideOnCityItemSelected())
    private var daysWeatherAdapter = DaysWeatherAdapter()

    private fun observeSearchEmployeeSearchResult() {
        viewModel.searchLiveData.observe(viewLifecycleOwner) {
            searchDropDownUiHelper.updateCitiesDropDownAdapter(it)
        }
    }

    private fun provideOnCityItemSelected(): (SearchResultItem) -> Unit = {
        searchDropDownUiHelper.updateCitiesDropdownText("${it.name} - ${it.region}")
        viewModel.getForeCast(it.name)
        searchViewVisibility(false)
    }

    private fun provideOnValidCitySearchQuerySubmitted(): (String) -> Unit = {
        viewModel.searchCity(it)
    }

    var isTemperatureF = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarListener?.hideActivityToolbar()
        //Current Location
        getWeatherDetailsCurrentLocation()
        binding.imgSearch.setOnClickListener(this)
        binding.searchDropDown.imgBack.setOnClickListener(this)
        binding.searchDropDown.imgCloseSearch.setOnClickListener(this)
        intiTemperatureSwitch()
        intiShowFavirouteCitiesBtn()
        intiAddToFaviroute()
        intiImgDownload()
        searchDropDownUiHelper.handleSearchDropdown()
        observeForeCast()
        observeSearchEmployeeSearchResult()
        observeFavirouteCities()
        observeDownloadFavirouteCities()
    }

    private fun getWeatherDetailsCurrentLocation() {
        val currentLocation: CurrentLocation? = CurrentLocation.getInstance(requireActivity())
        currentLocation?.observe(viewLifecycleOwner) {
            if (firstTime) {
                currentLocation.getAddressName(
                    requireContext(),
                    LatLng(it.latitude, it.longitude)
                )
                    ?.let { it1 -> viewModel.getForeCast(it1) }
            }
            firstTime = false
        }
    }

    private fun intiImgDownload() {
        binding.imgDownload.setOnClickListener {
            viewModel.downloadFavirouteCities()
        }
    }

    private fun observeDownloadFavirouteCities() {
        viewModel.downloadFavirouteCitiesLiveData.observe(viewLifecycleOwner) {
            exportDatabaseToCSVFile(it)
        }
    }

    private fun intiAddToFaviroute() {
        binding.btnAddFavirouteCities.setOnClickListener {
            viewModel.insertFavirouteCity(weatherData)
            Toast.makeText(requireContext(), getString(R.string.added), Toast.LENGTH_SHORT).show()
        }
    }

    private fun intiTemperatureSwitch() {
        binding.switchTemperature.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                renderCTemperature()
            } else {
                renderFTemperature()
            }
        }
    }

    private fun intiShowFavirouteCitiesBtn() {
        binding.btnShowFavirouteCities.setOnClickListener {
            getLocalFavirouteCities()
        }
    }

    private fun observeFavirouteCities() {
        viewModel.getFavirouteCitiesLiveData.observe(viewLifecycleOwner) {
            showDialog(
                FavirouteCitiesBottomSheet(
                    it,
                    ::applyFilter,
                )
            )
        }
    }

    private fun applyFilter(favoriteCity: LocalForecast?) {
        favoriteCity?.let { renderData(it) }
    }

    private fun getLocalFavirouteCities() {
        viewModel.getFavirouteCities()
    }

    private fun renderCTemperature() {
        isTemperatureF = false
        binding.tvTemperatureNumber.text = weatherData.current.tempC.toString()
        binding.tvTemperatureDegree.text = requireContext().getString(R.string.c)
        binding.tvTemperatureDegree.isVisible = true
        val daysWeatherC = weatherData.forecast.foreCastDay
        daysWeatherC?.forEach {
            it?.isF = false
        }
        daysWeatherAdapter.submitList(daysWeatherC)
        daysWeatherAdapter.notifyDataSetChanged()
    }

    private fun renderFTemperature() {
        isTemperatureF = true
        binding.tvTemperatureNumber.text = weatherData.current.tempF.toString()
        binding.tvTemperatureDegree.text = requireContext().getString(R.string.f)
        binding.tvTemperatureDegree.isVisible = true
        val daysWeatherF = weatherData.forecast.foreCastDay
        daysWeatherF?.forEach {
            it?.isF = true
        }
        daysWeatherAdapter.submitList(daysWeatherF)
        daysWeatherAdapter.notifyDataSetChanged()
    }

    private fun observeForeCast() {
        lifecycleScope.launchWhenStarted {
            viewModel.forecastLiveData.observe(viewLifecycleOwner) {
                renderData(it)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderData(data: LocalForecast) {
        weatherData = data
        binding.tvCityName.text = data.location.name
        binding.tvCityName.isVisible = true
        if (isTemperatureF) {
            binding.tvTemperatureNumber.text = data.current.tempF.toString()
            binding.tvTemperatureDegree.text = requireContext().getString(R.string.f)
        } else {
            binding.tvTemperatureNumber.text = data.current.tempC.toString()
            binding.tvTemperatureDegree.text = requireContext().getString(R.string.c)
        }
        binding.tvTemperatureDegree.isVisible = true
        binding.tvTime.isVisible = true
        binding.tvTime.text = convertDateToTime(data.current.lastUpdated)
        binding.tvTodayName.text =
            convertDateToDayName(data.forecast.foreCastDay?.get(0)?.date.toString()) + ","
        binding.tvDate.text =
            convertDateToFormat(data.forecast.foreCastDay?.get(0)?.date.toString())
        Glide.with(requireContext()).load("https:${data.current.condition.icon}")
            .into(binding.imgWeatherStatus)
        binding.tvWeatherStatus.text = data.current.condition.text
        binding.tvHumidityNumber.text = data.current.humidity.toString()
        binding.tvWindNumber.text = data.current.windMph.toString()
        binding.tvWindDegree.isVisible = true
        intiDaysWeather(data.forecast.foreCastDay, isTemperatureF)
        binding.imgHumidity.isVisible = true
        binding.imgWind.isVisible = true
        binding.tvHumidityPercent.isVisible = true

    }

    private fun intiDaysWeather(foreCastDayList: List<ForeCastDayItem?>?, isTemperatureF: Boolean) {
        foreCastDayList?.forEach {
            it?.isF = isTemperatureF

        }
        daysWeatherAdapter.submitList(foreCastDayList)
        binding.rvWeatherDays.adapter = daysWeatherAdapter
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.imgSearch -> {
                searchViewVisibility(true)
            }
            binding.searchDropDown.imgBack -> {
                searchViewVisibility(false)
            }
            binding.searchDropDown.imgCloseSearch -> {
                binding.searchDropDown.etSearchDropDown.setText("")
            }
            binding.searchDropDown.layoutExpandUp -> {
            }
        }
    }

    private fun searchViewVisibility(visible: Boolean) {
        binding.searchDropDown.root.isVisible = visible
        if (!visible) {
            binding.searchDropDown.etSearchDropDown.setText("")
        }
    }

    private fun getCSVFileName(): String =
        getString(R.string.data_base_name)

    private fun exportDatabaseToCSVFile(list: List<LocalForecast>) {
        val csvFile = generateFile(requireContext(), getCSVFileName())
        if (csvFile != null) {
            exportMoviesWithDirectorsToCSVFile(csvFile, list)

            Toast.makeText(
                requireContext(),
                getString(R.string.file_generated),
                Toast.LENGTH_LONG
            ).show()
            val intent = goToFileIntent(requireContext(), csvFile)
            startActivity(intent)
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.file_not_generated),
                Toast.LENGTH_LONG
            ).show()
        }
    }


}