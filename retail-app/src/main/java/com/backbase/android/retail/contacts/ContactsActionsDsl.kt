package com.backbase.android.retail.contacts

import android.app.Activity
import org.koin.android.ext.android.getKoin
import org.koin.dsl.module

fun Activity.contactsActions(block: ContactsActionsScope.() -> Unit) {
    val scope = ContactsActionsScope().apply(block)
    val modules = listOf(
        module {

        }
    )
    this.getKoin().loadModules(modules)
}

class ContactsActionsScope {
    var onContactClicked: ((id:String) -> Unit)? = null
}
