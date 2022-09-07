package com.yyin.jetpackcomposelayout


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ScrollingView
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch


@Composable
fun SimpleColumn() {
    //通过remember创建了一个state对象
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(100) {
            Text(text = "Item #$it", style = MaterialTheme.typography.subtitle1)
        }
    }
}

@Composable
@Preview
fun ScrollingLazyListPreview() {
    ScrollingLazyList()
}

@Composable
fun ScrollingLazyList() {
    //通过remember创建了一个state对象
    val listSize = 100
    val scrollState = rememberLazyListState()
    //协程作用域
    val corountineScope = rememberCoroutineScope()
    Column() {
        Row() {
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    corountineScope.launch {
                        scrollState.animateScrollToItem(0)
                    }
                }) {
                Text(text = "Scroll to the top")
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = { corountineScope.launch {
                    scrollState.animateScrollToItem(listSize)
                } }) {
                Text(text = "Scroll to the end")
            }
        }

        LazyColumn(state = scrollState) {
            items(listSize) {
                ImageListItem(index = it)
            }
        }
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(
                data = "https://qiniu.yyin.top/mybatisplus2.png"
            ),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}
