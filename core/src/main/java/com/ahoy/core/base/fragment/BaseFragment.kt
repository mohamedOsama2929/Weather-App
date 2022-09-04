package com.ahoy.core.base.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.ahoy.core.R
import com.ahoy.core.base.activity.ToolbarListener
import com.ahoy.core.base.dialog.NoInternetDialog
import com.ahoy.core.base.dialog.ServerErrorDialog
import com.ahoy.core.base.dialog.UnknownHostDialog
import com.ahoy.core.base.view_model.BaseViewModel
import com.ahoy.core.utils.LoadingListener
import com.ahoy.entities.base.ErrorModel
import com.ahoy.entities.base.ErrorStatus
import com.ahoy.entities.side_menu.SideMenuEnum
import kotlinx.coroutines.ExperimentalCoroutinesApi

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

@ExperimentalCoroutinesApi
abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(
    private val inflate: Inflate<VB>
) : NetworkBaseFragment() {

    private val TAG = BaseFragment::class.java.simpleName

    //added line to view binding
    private var _binding: VB? = null
    val binding get() = _binding!!

    private var mLoader: LoadingListener? = null

    abstract val viewModel: VM

    protected var toolbarListener: ToolbarListener? = null

    // if null back btn will be displayed
    // otherwise menu icon will be displayed and this item will be selected when open side menu
    open var currentSideMenuItem: SideMenuEnum? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            errorLiveData.observe(viewLifecycleOwner, Observer {
                when (it.errorStatus) {
                    ErrorStatus.NO_DATA -> return@Observer
                    ErrorStatus.NO_CONNECTION -> NoInternetDialog.showDialog(
                        requireContext(),
                        it.errorStatus
                    )
                    ErrorStatus.UNAUTHORIZED -> handleUnAuthError()
                    ErrorStatus.UNPROCESSABLE_ENTITY -> handleUnprocessableEntity()
                    ErrorStatus.INTERNAL_SERVER_ERROR -> ServerErrorDialog.showDialog(
                        requireContext(),
                        it.errorStatus
                    )
                    ErrorStatus.UNKNOWN_HOST -> UnknownHostDialog.showDialog(
                        requireContext(),
                        it.errorStatus
                    )
                    ErrorStatus.FORRBIDEN, ErrorStatus.NOT_FOUND -> Toast.makeText(
                        context,
                        "Something went wrong",
                        Toast.LENGTH_SHORT
                    ).show()

                    ErrorStatus.EMPTY_RESPONSE -> Toast.makeText(
                        context,
                        "${it?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    else -> Toast.makeText(context, "${it?.message}", Toast.LENGTH_SHORT).show()
                }
                onViewModelError()
            })

            cancellationMsgLiveData.observe(viewLifecycleOwner, Observer {
                Log.d(TAG, it)
            })

            isLoadingLiveData.observe(viewLifecycleOwner, Observer {
                showLoading(it)
                Log.d(TAG, "Loading observer is called")
            })
        }

        val fragmentToolbar: Toolbar? = view.findViewById(R.id.toolbar)

        if (fragmentToolbar == null) {
            // fragment doesn't have a custom toolbar
            toolbarListener?.showActivityToolbar(currentSideMenuItem)
        } else {
            // If fragment has its own toolbar and we are attached to a toolbarListener, hide activity toolbar
            toolbarListener?.hideActivityToolbar()
            toolbarListener?.initToolbarNavigationIcon(fragmentToolbar, currentSideMenuItem)
        }


        view.findViewById<ImageView>(R.id.img_profile)?.run {
            setOnClickListener { toolbarListener?.openUserProfile() }
        }


        view?.findViewById<ImageView>(R.id.img_search)?.run {
            setOnClickListener {
            }
        }

    }


    override fun onStop() {
        super.onStop()
        viewModel.setErrorReason(ErrorModel(ErrorStatus.NO_DATA))
    }

    open fun onViewModelError() {

    }

    open fun handleUnAuthError() {
    }

    open fun handleUnprocessableEntity() {}

    fun setActivityToolbarTitle(title: String, gravity: Int? = null) {
        toolbarListener?.setActivityToolbarTitle(title, gravity)
    }

    fun hideActivityToolbar() {
        toolbarListener?.hideActivityToolbar()
    }


    override fun onDestroyView() {
        with(viewModel) {
            errorLiveData.removeObservers(viewLifecycleOwner)
            cancellationMsgLiveData.removeObservers(viewLifecycleOwner)
            showLoading(false)
        }
        super.onDestroyView()
        _binding = null
    }


    open fun showLoading(show: Boolean) {
        mLoader?.showLoading(show)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.let {
            if (it is LoadingListener) mLoader = it

            if (it is ToolbarListener) toolbarListener = it

        }
    }

    override fun onDetach() {
        super.onDetach()
        mLoader = null
        toolbarListener = null
    }
}
