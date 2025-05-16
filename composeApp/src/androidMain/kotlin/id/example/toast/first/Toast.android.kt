package id.example.toast.first

import android.widget.Toast

actual object Toast {
    actual fun makeText(text: String, duration: ToastDuration) {
        val duration = when (duration) {
            ToastDuration.LENGTH_SHORT -> Toast.LENGTH_SHORT
            ToastDuration.LENGTH_LONG -> Toast.LENGTH_LONG
        }
        Toast.makeText(AppContext.getContext(), text, duration)
            .show()
    }
}