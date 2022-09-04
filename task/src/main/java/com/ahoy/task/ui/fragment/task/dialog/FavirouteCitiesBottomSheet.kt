package com.ahoy.task.ui.fragment.task.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.ahoy.core.base.dialog.BaseBottomSheetFragment
import com.ahoy.entities.task.local.LocalForecast
import com.ahoy.task.R
import com.ahoy.task.ui.fragment.task.TaskViewModel
import com.ahoy.task.ui.fragment.task.adapter.FavirouteCitiesAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.bottom_sheet_faviroute_cities.*
import kotlinx.coroutines.ExperimentalCoroutinesApi


@AndroidEntryPoint
@ExperimentalCoroutinesApi
class FavirouteCitiesBottomSheet(
    private val favirouteCities: List<LocalForecast>,
    private val applyFilter: (LocalForecast?) -> Unit
) : BaseBottomSheetFragment<TaskViewModel>() {

    private lateinit var favirouteCitiesAdapter: FavirouteCitiesAdapter

    override var layoutResourceId = R.layout.bottom_sheet_faviroute_cities

    override val viewModel: TaskViewModel by viewModels()

    override fun setupBottomSheet(dialogInterface: DialogInterface) {
        super.setupBottomSheet(dialogInterface)

        val bottomSheetDialog = dialogInterface as BottomSheetDialog
        val bottomSheet = bottomSheetDialog.findViewById<View>(
            R.id.design_bottom_sheet
        ) ?: return
        BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCloseBtn()
        initFavirouteCitiesAdapter()
    }

    private fun initFavirouteCitiesAdapter() {
        favirouteCitiesAdapter = FavirouteCitiesAdapter {
            applyFilter(it)
            dismiss()
        }
        favirouteCitiesAdapter.submitList(favirouteCities)
        rvFavirouteCities.adapter = favirouteCitiesAdapter
    }

    private fun initCloseBtn() {
        img_close.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }


}