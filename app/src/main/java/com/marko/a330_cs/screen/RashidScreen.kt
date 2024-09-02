package com.marko.a330_cs.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun RashidScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "HELLO RASHID!",
            fontSize = 47.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}