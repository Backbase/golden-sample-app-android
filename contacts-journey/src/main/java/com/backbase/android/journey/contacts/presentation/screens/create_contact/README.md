# MVI - POC
The package `com.backbase.android.journey.contacts.presentation.screens.create_contact` contains an 
example implementation of MVI. But be aware, that it doesn't follow 100% the MVI architecture, 
so you won't see `Reducer` or `Middleware` in this example. This was done by design, we didn't want 
to add a lot of overhead to a screen implementation. 

MVI is a pattern that part of the Android community follows, but it is not part of the [Android 
architecture principles](https://developer.android.com/topic/architecture/recommendations#compose). 
That is why we decided to implement a simpler approach, applying the 
[Unidirectional data flow](https://developer.android.com/develop/ui/compose/architecture#udf-compose)
pattern instead.

## How it works
Under the package `com.backbase.android.foundation.mvi` you will find the following classes:
- `IntentContext`: This class is used to launch effects and update the UI state.
- `IntentHandler`: This class is used to handle intents.
- `IntentScope`: This class is used to scope the intent handler, in there the IntentHandler has access
  to the Intent, the current UI state and the IntentContext.
- `ViewModel`: This class extends from [androidx.lifecycle.ViewModel] and is used to store the UI state.

These classes are supposed to be delivered as a separate framework.

### R&D - How to implement a screen
1. Define a `state` class representing the UI state.
2. (Optional) Define a `viewEffect` class representing the side effects that can be launched from the UI.
3. Define a set of `intents` that can modify the UI state.
4. Create a set of `IntentHandlers` that can handle the intents defined in 3.
    The `IntentHandlers` update the UI or launch a side effect based on the operation that the intent represents.
5. Create a ViewModel defining a initial state of the UI and a set of intent handlers.
6. Create a composable screen.
7. Define the navigation.

### Services - How to implement a screen
The number of steps will change depending on the customization. Services developers
can choose between modifying the default behavior of a screen or to extend the behavior, by for example
handling custom intents.

1. (Optional) Define a custom extension to be stored in the screen's state with extra information that the screen will need.
2. (Optional) Define a `viewEffect` class representing the side effects that can be launched from the UI.
3. (Optional) Define a set of `intents` that can modify the UI state to extend the default behavior.
4. Create a set of `IntentHandlers` that can handle: an intent defined in point 3 or an out-of-the-box intent.
5. Create a ViewModel defining a initial state of the UI and a set of intent handlers.
6. (Optional) Create a composable screen. If a client wants to support custom intents it is most
   likely that a new screen has to be created. Components from package
   com.backbase.android.journey.contacts.presentation.components can be used to reuse as much
   components as possible.
7. Define the navigation. TODO Define how to swap the navigation.
