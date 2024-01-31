package com.backbase.golden_sample_app.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.backbase.android.design.header.TabHeaderViewModel
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.databinding.ActivityMainBinding
import com.backbase.golden_sample_app.menu.moreMenuModule
import com.backbase.golden_sample_app.payments.paymentsMenuModule
import com.backbase.golden_sample_app.presentation.bottom.setupBottomBar
import com.backbase.golden_sample_app.presentation.header.updateStatusBarColor
import com.backbase.golden_sample_app.router.AppRouting
import com.backbase.golden_sample_app.session.sessionModule
import kotlinx.coroutines.flow.map
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules

/**
 * Single activity approach and the setup of the navigation.
 *
 * Created by Backbase R&D B.V on 17/08/2023
 */
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val navigator: AppRouting by inject()
    private val tabHeaderViewModel: TabHeaderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_container)
        navigator.bind(navController)

        updateStatusBarColor(isInRootScreen = tabHeaderViewModel.uiState.map { it.isInRootScreen })
        setupBottomBar(isInRootScreen = tabHeaderViewModel.uiState.map { it.isInRootScreen })

        if (savedInstanceState == null) { loadScopedDependencies() }
    }

    private fun loadScopedDependencies() {
        val navController = Navigation.findNavController(this@MainActivity, R.id.nav_host_container)
        loadKoinModules(
            listOf(
                sessionModule(navController),
                moreMenuModule(navController),
                paymentsMenuModule(navController)
            )
        )
    }
}
