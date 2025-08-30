package me.androidbox.cakelightingcontrol

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CandleScreen(
    candleStates: List<CandleState>,
    onAction: (action: CandleAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        CandleBoard(
            candleStates = candleStates,
            onAction = onAction
        )

        AnimatedVisibility(
            visible = candleStates.any { (_, isLit) ->
                !isLit
            },
            enter = slideInVertically(
                animationSpec = spring(),
                initialOffsetY = { it }
            ),
            exit = slideOutVertically(
                animationSpec = spring(),
                targetOffsetY = { it }
            )
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.BottomCenter)
                    .clickable(
                        onClick = {
                            onAction(CandleAction.OnLightAllCandles)
                        }
                    ),
                color = Color(0xff95D3ED),
                shape = CircleShape
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 28.dp, vertical = 8.dp),
                    text = "Light all candles",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(60.dp))
    }
}

@Preview
@Composable
fun CandleScreenPreview() {
    val candleStates = listOf(
        CandleState(id = 0, isLit = false),
        CandleState(id = 1, isLit = true),
        CandleState(id = 2, isLit = true),
        CandleState(id = 3, isLit = false),
        CandleState(id = 4, isLit = true),
        CandleState(id = 5, isLit = false),
        CandleState(id = 6, isLit = true),
    )

    MaterialTheme {
        CandleScreen(
            candleStates = candleStates,
            onAction = {})
    }
}