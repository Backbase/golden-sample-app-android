package com.backbase.golden_sample_app.common

val Any?.TAG: String get() = when {
    this == null -> "null"
    else -> this.javaClass.tag
}

private val Class<*>.tag: String get() {
    val simpleName: String = simpleName
    return when {
        simpleName.isEmpty() || simpleName.contains('$') -> {
            val enclosingClass = enclosingClass
            if (enclosingClass == null) {
                val fullName = name
                fullName.substring(fullName.lastIndexOf('.') + 1, fullName.indexOf('$'))
            } else {
                enclosingClass.tag
            }
        }
        else -> simpleName
    }
}