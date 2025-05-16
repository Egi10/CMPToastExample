package id.example.toast

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import id.example.toast.first.Toast
import id.example.toast.first.ToastDuration
import id.example.toast.second.ExampleToast
import id.example.toast.second.rememberExampleToastState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val toastState = rememberExampleToastState()

        ExampleToast(
            state = toastState
        )

        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = {
                    toastState.showToast(
                        message = "This is a toast message",
                    )
                }
            ) {
                Text("Toast With Compose")
            }

            Button(
                onClick = {
                    Toast.makeText(
                        text = "This is a toast message",
                        duration = ToastDuration.LENGTH_LONG
                    )
                }
            ) {
                Text("Toast With Native")
            }
        }
    }
}