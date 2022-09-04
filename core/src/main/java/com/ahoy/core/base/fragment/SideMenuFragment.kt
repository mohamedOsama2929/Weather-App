package com.ahoy.core.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ahoy.core.BuildConfig
import com.ahoy.core.R
import com.ahoy.core.base.activity.SideMenuListener
import com.ahoy.core.base.adapter.SideMenuItemsAdapter
import com.ahoy.core.utils.getEnum
import com.ahoy.core.utils.putEnum
import com.ahoy.entities.side_menu.SideMenuEnum
import com.ahoy.entities.side_menu.SideMenuEnum.Home
import com.ahoy.entities.side_menu.SideMenuEnum.MyInbox
import com.ahoy.entities.side_menu.SideMenuItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.side_menu_content.*
import kotlinx.android.synthetic.main.side_menu_footer.*
import kotlinx.android.synthetic.main.side_menu_header.*

@AndroidEntryPoint
class SideMenuFragment : NetworkBaseFragment() {

    companion object {
        private const val SELECTED_ITEM = "SELECTED_ITEM"

        fun newInstance(selectedItem: SideMenuEnum) =
            SideMenuFragment().apply {
                arguments = Bundle().apply { putEnum(SELECTED_ITEM, selectedItem) }
            }
    }

    private var sideMenuListener: SideMenuListener? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_side_menu, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is SideMenuListener)
            sideMenuListener = context
    }

    override fun onDetach() {
        super.onDetach()
        sideMenuListener = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initHeader()
        initContent()
        initFooter()
    }

    private fun setUserInfo() {

    }

    private fun initHeader() {
        setUserInfo()

        img_close.setOnClickListener {
            sideMenuListener?.closeSideMenu()
        }

        btn_my_profile.setOnClickListener {
            sideMenuListener?.onMyProfileClicked()
        }
    }

    private fun initContent() {
        val items = listOf(
            SideMenuItem(R.string.diwan_home, R.drawable.ic_side_menu_home, Home),
            SideMenuItem(
                R.string.my_dashboard, R.drawable.ic_side_menu_dashboard,
                SideMenuEnum.MyDashboard
            ),
            SideMenuItem(R.string.my_inbox, R.drawable.ic_side_menu_inbox, MyInbox),
        )

        val adapter = SideMenuItemsAdapter(arguments?.getEnum(SELECTED_ITEM)!!) {
            if (it == arguments?.getEnum<SideMenuEnum>(SELECTED_ITEM)!!)
                sideMenuListener?.closeSideMenu()
            else
                sideMenuListener?.onSideMenuItemSelected(it)
        }

        recycler_navigation_items.adapter = adapter

        adapter.submitList(items)
    }

    private fun initFooter() {
        tv_version.text = getString(R.string.diwan_version, "1.0." + BuildConfig.VERSION_CODE)

        btn_sign_out.setOnClickListener {
        }
    }
}