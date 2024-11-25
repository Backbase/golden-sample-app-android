package app_common.scenario

import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UiTestJourneyFactoryHolderViewModel: ViewModel() {
    var fragmentFactory: FragmentFactory? = null

    override fun onCleared() {
        super.onCleared()
        fragmentFactory = null
    }

    companion object {

         private val FACTORY: ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val viewModel =
                        UiTestJourneyFactoryHolderViewModel()
                    return viewModel as T
                }
            }

        fun getInstance(activity: FragmentActivity): UiTestJourneyFactoryHolderViewModel {
            val viewModel: UiTestJourneyFactoryHolderViewModel by activity.viewModels { FACTORY }
            return viewModel
        }
    }
}
