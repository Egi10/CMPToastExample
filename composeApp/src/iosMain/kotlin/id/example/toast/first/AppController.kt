package id.example.toast.first

import platform.UIKit.UIViewController

object AppController {
    var rootViewController: UIViewController? = null

    fun set(controller: UIViewController) {
        rootViewController = controller
    }

    fun get(): UIViewController? = rootViewController
}