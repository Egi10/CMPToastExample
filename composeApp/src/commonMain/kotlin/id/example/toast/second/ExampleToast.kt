package id.example.toast.second

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import kotlinx.coroutines.delay

enum class ExampleToastDuration(val duration: Long) {
    LENGTH_SHORT(2000),
    LENGTH_LONG(3500)
}

@Immutable
data class ExampleToastData(
    val text: String,
    val duration: ExampleToastDuration
)

@Stable
class ExampleToastState {
    private val _toastData = mutableStateOf<ExampleToastData?>(null)
    val toastData: ExampleToastData?
        get() = _toastData.value

    fun showToast(
        message: String,
        duration: ExampleToastDuration = ExampleToastDuration.LENGTH_SHORT
    ) {
        _toastData.value = ExampleToastData(
            text = message,
            duration = duration
        )
    }

    fun dismiss() {
        _toastData.value = null
    }
}

@Composable
fun rememberExampleToastState(): ExampleToastState {
    return remember {
        ExampleToastState()
    }
}

@Composable
fun ExampleToast(
    state: ExampleToastState,
    modifier: Modifier = Modifier,
    anchorPosition: IntOffset = IntOffset(0, 40),
    onDismiss: () -> Unit = {}
) {
    state.toastData?.let { data ->
        ExampleToastImpl(
            modifier = modifier,
            message = data.text,
            offset = anchorPosition,
            duration = data.duration.duration,
            containerColor = Color.Gray,
            onDismiss = {
                onDismiss.invoke()
                state.dismiss()
            }
        )
    }
}

@Composable
private fun ExampleToastImpl(
    message: String,
    offset: IntOffset,
    duration: Long,
    containerColor: Color,
    modifier: Modifier = Modifier,
    contentColor: Color = Color.White,
    onDismiss: () -> Unit
) {
    val alpha = remember {
        Animatable(0f)
    }

    LaunchedEffect(message) {
        alpha.animateTo(1f, animationSpec = tween(300))
        delay(duration)
        alpha.animateTo(0f, animationSpec = tween(300))
        onDismiss()
    }

    Popup(
        alignment = Alignment.BottomCenter,
        offset = offset
    ) {
        Box(
            modifier = modifier
                .alpha(alpha.value)
                .padding(
                    all = 16.dp
                )
                .background(
                    color = containerColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(
                    vertical = 14.dp,
                    horizontal = 16.dp
                )
        ) {
            Text(
                text = message,
                color = contentColor
            )
        }
    }
}