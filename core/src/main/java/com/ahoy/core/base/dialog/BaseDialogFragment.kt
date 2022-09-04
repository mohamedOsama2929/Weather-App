package com.ahoy.core.base.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.ahoy.core.base.view_model.BaseViewModel
import com.ahoy.core.utils.LoadingListener
import com.ahoy.entities.base.ErrorStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
abstract class BaseDialogFragment<VM : BaseViewModel> : DialogFragment() {

    private val TAG = BaseBottomSheetFragment::class.java.simpleName

    abstract var layoutResourceId: Int
    private var mLoader: LoadingListener? = null

    abstract val viewModel: VM

    open var isRoundedCorners = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (dialog != null && dialog?.window != null) {
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return inflater.inflate(layoutResourceId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            errorLiveData.observe(viewLifecycleOwner, Observer {
                when (it.errorStatus) {
                    ErrorStatus.NO_CONNECTION -> NoInternetDialog.showDialog(
                        requireContext(),
                        it.errorStatus
                    )
                    ErrorStatus.INTERNAL_SERVER_ERROR -> ServerErrorDialog.showDialog(
                        requireContext(),
                        it.errorStatus
                    )
                    else -> Toast.makeText(context, "${it?.message}", Toast.LENGTH_LONG).show()
                }
            })

            cancellationMsgLiveData.observe(viewLifecycleOwner, Observer {
                Log.d(TAG, it)
            })

            isLoadingLiveData.observe(viewLifecycleOwner, Observer {
                showLoading(it)
            })
        }
    }


    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onStop() {
        super.onStop()
        with(viewModel) {
            errorLiveData.removeObservers(viewLifecycleOwner)
            cancellationMsgLiveData.removeObservers(viewLifecycleOwner)
            showLoading(false)
        }
    }


    open fun showLoading(show: Boolean) {
        mLoader?.showLoading(show)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.let {
            if (it is LoadingListener) mLoader = it
        }
    }

    override fun onDetach() {
        super.onDetach()
        mLoader = null
    }
}
