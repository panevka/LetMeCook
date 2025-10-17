package com.letmecook.domain.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
internal fun UserView () {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text("text user profile view")
        }

}
