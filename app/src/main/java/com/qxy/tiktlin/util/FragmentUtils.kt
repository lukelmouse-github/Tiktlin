package com.qxy.tiktlin.util

import android.os.Build
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.MenuRes
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions

val Fragment.navController get() = NavHostFragment.findNavController(this)

fun NavController.navigateMain(@IdRes resId: Int) {
    val options = navOptions {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
    navigate(resId, null, options)
}

fun Fragment.setupToolbar(
    toolbar: Toolbar,
    title: String?,
    @DrawableRes navigationIcon: Int? = null,
    navigationOnClick: View.OnClickListener? = null,
    @MenuRes menuRes: Int? = null,
    onMenuOptionSelected: ((MenuItem) -> Unit)? = null
) {
    navigationOnClick?.let { toolbar.setNavigationOnClickListener(it) }
    navigationIcon?.let { toolbar.setNavigationIcon(navigationIcon) }
    toolbar.title = title
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) toolbar.tooltipText = title
    if (menuRes != null) {
        val menuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(menuRes, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return onMenuOptionSelected?.let {
                    it(menuItem); true
                } ?: false
            }
        }
        toolbar.inflateMenu(menuRes)
        toolbar.setOnMenuItemClickListener(menuProvider::onMenuItemSelected)
        requireActivity().addMenuProvider(menuProvider)
        menuProvider.onPrepareMenu(toolbar.menu)
    }
}
