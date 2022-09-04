package com.ahoy.core.base.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ahoy.core.R
import com.ahoy.core.base.fragment.SideMenuFragment
import com.ahoy.core.utils.LanguageUtils
import com.ahoy.core.utils.LoadingListener
import com.ahoy.core.utils.LocaleUtils
import com.ahoy.core.utils.VersionUtils
import com.ahoy.entities.side_menu.SideMenuEnum
import kotlinx.android.synthetic.main.activity_common.*

abstract class BaseActivity : AppCompatActivity(), LoadingListener, ToolbarListener {
    private val TAG = BaseActivity::class.java.simpleName

    private lateinit var navFragment: NavHostFragment
    lateinit var navController: NavController

    abstract var navGraphResourceId: Int
    protected lateinit var bundle: Bundle

    @LayoutRes
    open var layoutRes = R.layout.activity_common


    override fun onCreate(savedInstanceState: Bundle?) {
        if (!VersionUtils.isOreoAndLater) {
            LanguageUtils.setLocale(this)
        }
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        setNavFragment()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleUtils.onAttach(base))
    }


    private fun setNavFragment() {
        navFragment =
            supportFragmentManager.findFragmentById(R.id.common_nav_fragment) as NavHostFragment
        navController = navFragment.navController
        if (::bundle.isInitialized) navController.setGraph(navGraphResourceId, bundle)
        else navController.setGraph(navGraphResourceId)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            tv_title.text = destination.label
        }

        img_profile?.setOnClickListener {
        }
    }


    override fun showActivityToolbar(currentSideMenuItem: SideMenuEnum?) {
        toolbar?.visibility = View.VISIBLE
        initToolbarNavigationIcon(toolbar, currentSideMenuItem)
    }

    override fun hideActivityToolbar() {
        toolbar?.visibility = View.GONE
    }

    fun hideImageProfile() {
        img_profile.visibility = View.GONE
    }

    fun hideImageSearch() {
        img_search.visibility = View.GONE
    }

    override fun setActivityToolbarTitle(title: String, gravity: Int?) {
        tv_title?.text = title
        gravity?.let {
            tv_title?.gravity = it
        }
    }

    override fun initToolbarNavigationIcon(toolbar: Toolbar, currentSideMenuItem: SideMenuEnum?) {

        val navIcon = toolbar.findViewById<ImageView>(R.id.img_navigation) ?: return

        if (currentSideMenuItem != null) {
            navIcon.setImageResource(R.drawable.ic_side_menu)

            navIcon.setOnClickListener {
                sideMenuContainer.visibility = View.VISIBLE

                supportFragmentManager.beginTransaction().replace(
                    R.id.sideMenuContainer, SideMenuFragment.newInstance(currentSideMenuItem)
                ).setCustomAnimations(R.anim.slide_in, R.anim.slide_out).commit()
            }

        } else {
            navIcon.setImageResource(R.drawable.back_arrow_layers)
            navIcon.setOnClickListener { onBackPressed() }
        }
    }


    override fun openUserProfile() {
    }


    fun setFragmentDestination(@IdRes resId: Int, bundle: Bundle?) =
        navController.navigate(resId, bundle)

    fun popupFragment() = navController.popBackStack()

    override fun showLoading(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}