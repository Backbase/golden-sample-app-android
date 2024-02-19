package observability

import com.backbase.android.observability.Tracker
import org.koin.dsl.module

fun observabilityModule(tracker: Tracker) = module {
    single { tracker }
}
