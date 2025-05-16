package id.example.toast

import android.app.Application
import id.example.toast.first.AppContext

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize the AppContext singleton
        AppContext.initialize(this)
    }
}