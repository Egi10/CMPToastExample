package id.example.toast.first

enum class ToastDuration {
    LENGTH_SHORT,
    LENGTH_LONG
}

expect object Toast {
    fun makeText(text: String, duration: ToastDuration)
}