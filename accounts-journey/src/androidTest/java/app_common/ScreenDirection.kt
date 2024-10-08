package app_common

enum class ScreenDirection(val value: Boolean) {
    RTL(true),
    LTR(false);

    override fun toString(): String = when (this) {
        RTL -> "rtl"
        LTR -> "ltr"
    }
}
