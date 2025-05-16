package id.example.toast

import androidx.compose.ui.window.ComposeUIViewController
import id.example.toast.first.AppController
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    val controller = ComposeUIViewController { App() }

    AppController.set(controller)

    return controller
}