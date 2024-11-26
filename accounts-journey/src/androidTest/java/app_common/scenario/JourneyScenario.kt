package app_common.scenario

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.commitNow
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider

internal inline fun <reified F : Fragment> launchInJourneyContainer(
    fragmentArgs: Bundle? = null,
    initialState: Lifecycle.State = Lifecycle.State.RESUMED,
    forceRTL: Boolean = false,
    crossinline instantiate: () -> F
): FragmentJourneyScenario<F> = FragmentJourneyScenario.launchInJourneyContainer(
    fragmentClass = F::class.java,
    fragmentArgs = fragmentArgs,
    initialState = initialState,
    factory = object : FragmentFactory() {
        override fun instantiate(
            classLoader: ClassLoader,
            className: String
        ) = when (className) {
            F::class.java.name -> instantiate()
            else -> super.instantiate(classLoader, className)
        }
    },
    forceRTL = forceRTL
)

class FragmentJourneyScenario<F : Fragment> private constructor(
    private val fragmentClass: Class<F>,
    val activityScenario: ActivityScenario<UiTestJourneyActivity>
) {

    fun interface FragmentAction<F : Fragment> {
        fun perform(fragment: F)
    }

    fun onFragment(action: FragmentAction<F>): FragmentJourneyScenario<F> {
        activityScenario.onActivity { activity ->
            val fragment = requireNotNull(
                activity.supportFragmentManager.findFragmentByTag(FRAGMENT_TAG)
            ) {
                "The fragment has been removed from the FragmentManager already."
            }
            check(fragmentClass.isInstance(fragment))
            action.perform(requireNotNull(fragmentClass.cast(fragment)))
        }
        return this
    }

    companion object {
        private const val FRAGMENT_TAG = "FragmentJourneyScenario_Fragment_Tag"

        @JvmOverloads
        @JvmStatic
        internal fun <F : Fragment> launchInJourneyContainer(
            fragmentClass: Class<F>,
            fragmentArgs: Bundle? = null,
            initialState: Lifecycle.State = Lifecycle.State.RESUMED,
            factory: FragmentFactory? = null,
            forceRTL: Boolean = false
        ): FragmentJourneyScenario<F> = internalJourneyLaunch(
            fragmentClass = fragmentClass,
            fragmentArgs = fragmentArgs,
            initialState = initialState,
            factory = factory,
            forceRTL = forceRTL,
            containerViewId = android.R.id.content
        )

        @Suppress("LongParameterList")
        @SuppressLint("RestrictedApi")
        internal fun <F : Fragment> internalJourneyLaunch(
            fragmentClass: Class<F>,
            fragmentArgs: Bundle?,
            initialState: Lifecycle.State,
            factory: FragmentFactory?,
            forceRTL: Boolean,
            @IdRes containerViewId: Int
        ): FragmentJourneyScenario<F> {
            require(initialState != Lifecycle.State.DESTROYED) {
                "Cannot set initial Lifecycle state to $initialState for FragmentJourneyScenario"
            }
            val componentName = ComponentName(
                ApplicationProvider.getApplicationContext(),
                UiTestJourneyActivity::class.java
            )
            val startActivityIntent = Intent.makeMainActivity(componentName)
                .putExtra(UiTestJourneyActivity.FORCE_RTL_EXTRAS_BUNDLE_KEY, forceRTL)
            val scenario = FragmentJourneyScenario(
                fragmentClass,
                ActivityScenario.launch(
                    startActivityIntent
                )
            )
            scenario.activityScenario.onActivity { activity ->
                if (factory != null) {
                    UiTestJourneyFactoryHolderViewModel.getInstance(activity).fragmentFactory = factory
                    activity.supportFragmentManager.fragmentFactory = factory
                }
                val fragment = activity.supportFragmentManager.fragmentFactory
                    .instantiate(requireNotNull(fragmentClass.classLoader), fragmentClass.name)
                fragment.arguments = fragmentArgs
                activity.supportFragmentManager.commitNow {
                    add(containerViewId, fragment, FRAGMENT_TAG)
                    setMaxLifecycle(fragment, initialState)
                }
            }
            return scenario
        }
    }
}

object FragmentJourneyScenarioUtils {
    fun <F : Fragment> FragmentJourneyScenario<F>.waitForFragment(): F {
        var fragment: F? = null
        onFragment {
            fragment = it
        }
        return if (fragment != null) {
            fragment!!
        } else {
            error("The fragment scenario could not be initialized.")
        }
    }
}
