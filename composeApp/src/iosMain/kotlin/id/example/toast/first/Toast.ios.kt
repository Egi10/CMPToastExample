package id.example.toast.first

import platform.CoreGraphics.CGFloat
import platform.UIKit.NSLayoutConstraint
import platform.UIKit.NSTextAlignmentCenter
import platform.UIKit.UIColor
import platform.UIKit.UIFont
import platform.UIKit.UILabel
import platform.UIKit.UIView
import platform.UIKit.UIViewAnimationOptionCurveEaseIn
import platform.UIKit.UIViewAnimationOptionCurveEaseOut

actual object Toast {
    actual fun makeText(text: String, duration: ToastDuration) {
        val controller = AppController.get() ?: return
        val durationSeconds = when (duration) {
            ToastDuration.LENGTH_SHORT -> 2.0
            ToastDuration.LENGTH_LONG -> 3.5
        }

        val toastLabel = UILabel()
        toastLabel.text = text
        toastLabel.textAlignment = NSTextAlignmentCenter
        toastLabel.textColor = UIColor.whiteColor
        toastLabel.font = UIFont.systemFontOfSize(
            fontSize = 14.0
        )
        toastLabel.numberOfLines = 0
        toastLabel.layer.cornerRadius = 10.0
        toastLabel.clipsToBounds = true
        toastLabel.translatesAutoresizingMaskIntoConstraints = false

        val padding: CGFloat = 12.0
        val toastContainer = UIView()
        toastContainer.backgroundColor = UIColor.blackColor.colorWithAlphaComponent(0.7)
        toastContainer.layer.cornerRadius = 10.0
        toastContainer.translatesAutoresizingMaskIntoConstraints = false
        toastContainer.clipsToBounds = true
        toastContainer.addSubview(toastLabel)

        controller.view.addSubview(toastContainer)

        NSLayoutConstraint.activateConstraints(
            listOf(
                toastLabel.topAnchor.constraintEqualToAnchor(toastContainer.topAnchor, padding),
                toastLabel.bottomAnchor.constraintEqualToAnchor(
                    toastContainer.bottomAnchor,
                    -padding
                ),
                toastLabel.leadingAnchor.constraintEqualToAnchor(
                    toastContainer.leadingAnchor,
                    padding
                ),
                toastLabel.trailingAnchor.constraintEqualToAnchor(
                    toastContainer.trailingAnchor,
                    -padding
                ),
            )
        )
        NSLayoutConstraint.activateConstraints(
            listOf(
                toastContainer.centerXAnchor.constraintEqualToAnchor(controller.view.centerXAnchor),
                toastContainer.widthAnchor.constraintLessThanOrEqualToAnchor(
                    controller.view.widthAnchor,
                    multiplier = 0.85
                )
            )
        )

        // Position
        toastContainer.bottomAnchor.constraintEqualToAnchor(
            controller.view.bottomAnchor,
            -50.0
        ).active = true

        UIView.animateWithDuration(
            duration = 0.5,
            delay = 0.0,
            options = UIViewAnimationOptionCurveEaseOut,
            animations = {
                toastContainer.alpha = 1.0
            },
            completion = {
                UIView.animateWithDuration(
                    duration = 0.5,
                    delay = durationSeconds,
                    options = UIViewAnimationOptionCurveEaseIn,
                    animations = {
                        toastContainer.alpha = 0.0
                    },
                    completion = {
                        toastContainer.removeFromSuperview()
                    }
                )
            }
        )
    }
}