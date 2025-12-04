package com.samir.jetpackcomposemvvmclean.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.samir.jetpackcomposemvvmclean.R
import com.samir.jetpackcomposemvvmclean.utils.TestTags

@Composable
fun EmptyView(message: String) {
    Box(
        modifier = Modifier
            .semantics { testTag = TestTags.EMPTY_VIEW }
            .fillMaxSize(),
        contentAlignment = Alignment.Center) {

        Column {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.nodata))
            LottieAnimation(
                composition,
                modifier = Modifier.wrapContentWidth(),
                restartOnPlay = true,
                alignment = Alignment.Center,
                iterations = 200
            )

            Text(
                text = message,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .wrapContentHeight()
                    .testTag("emptyText"),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall
            )

            Button(
                onClick = {}, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .wrapContentHeight()
                    .testTag("button")
            ) {
                Text("Click")
            }
        }
    }
}