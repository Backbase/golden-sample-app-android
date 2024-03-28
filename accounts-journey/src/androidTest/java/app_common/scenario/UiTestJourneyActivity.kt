package app_common.scenario

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.backbase.android.retail.journey.R

class UiTestJourneyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (intent.getBooleanExtra(
                FORCE_RTL_EXTRAS_BUNDLE_KEY,
                false
            )
        ) {
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }

        val factory = UiTestJourneyFactoryHolderViewModel.getInstance(this).fragmentFactory
        if (factory != null) {
            supportFragmentManager.fragmentFactory = factory
        }

        // FragmentFactory needs to be set before calling the super.onCreate, otherwise the
        // Activity crashes when it is recreating and there is a fragment which has no
        // default constructor.
        super.onCreate(savedInstanceState)
    }

    companion object {
        const val FORCE_RTL_EXTRAS_BUNDLE_KEY = "UiTestJourneyActivity.FORCE_RTL_EXTRAS_BUNDLE_KEY"
    }
}