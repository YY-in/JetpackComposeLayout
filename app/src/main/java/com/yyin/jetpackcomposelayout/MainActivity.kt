package com.yyin.jetpackcomposelayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yyin.jetpackcomposelayout.ui.theme.JetpackComposelayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposelayoutTheme {
                MyOwnColumnSample()
            }
        }
    }
}