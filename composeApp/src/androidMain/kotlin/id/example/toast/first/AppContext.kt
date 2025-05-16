package id.example.toast.first

import android.content.Context

object AppContext {
    private var context: Context? = null

    fun initialize(context: Context) {
        this.context = context.applicationContext
    }

    fun getContext(): Context? = context
}