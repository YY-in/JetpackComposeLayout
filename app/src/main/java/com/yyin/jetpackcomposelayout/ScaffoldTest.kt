package com.yyin.jetpackcomposelayout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.material.IconButton
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ScaffoldTest() {
    Scaffold(
        topBar = {
            TopAppBar(
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                    }
                },
                title = {
                    Text(text = "YY-in")
                }

            )
        }
    ) {it ->
        BodyContent(Modifier.padding(it))
    }
}

@Composable
fun BodyContent(modifier: Modifier=Modifier){
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Hi there")
        Text(text = "Thanks for going through the LayoutStudy")
    }

}
@Composable
@Preview
fun PreviewScaffold(){
    ScaffoldTest ()
}