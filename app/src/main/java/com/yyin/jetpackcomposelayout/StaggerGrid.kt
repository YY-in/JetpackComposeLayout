package com.yyin.jetpackcomposelayout

import android.app.assist.AssistContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max


val topics = listOf(
    "Arts & Crafts",
    "Beauty",
    "Book",
    "Business",
    "History",
    "Film",
    "Maths",
    "A","B",
    "c","d",
    "e",
    "F","G"
)
@Composable
fun StaggerGrid(
    modifier: Modifier = Modifier,
    rows: Int = 3,
    content: @Composable () -> Unit
) {
    val rowWidths = IntArray(rows) { 0 }
    val rowHeights = IntArray(rows) { 0 }
    //计算每一行的高度、宽度
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->

        //测量组件大小
        val placeables = measurables.mapIndexed() { index, measurable ->
            //测量每一个元素,获取元素的参数集合
            val placeable = measurable.measure(constraints)
            val row = index % rows
            //一行的宽度等于这一行所有元素宽度之和
            rowWidths[row] += placeable.width
            //一行高度，应该是一行中高度最高的那个元素的高度
            rowHeights[row] = max(rowHeights[row], placeable.height)
            placeable
        }

        // 计算表格的宽高
        // 表格的宽度，应该是所有行当中最宽的那一行
        val width = rowWidths.maxOrNull() ?: constraints.minWidth
        val height = rowHeights.sumOf { it }

        //设置每一行的Y坐标
        val rowY = IntArray(rows) { 0 }
        //索引从1开始，因为第一行Y坐标为0，row[0] = 0
        for (i in 1 until rows) {
            //rowY[1] = rowY[0] + rowHeights[0]
            rowY[i] = rowY[i - 1] + rowHeights[i - 1]
        }

        //设置布局,设置坐标
        layout(width, height) {
            val rowX = IntArray(rows) { 0 }
            //设置每个元素的坐标
            placeables.forEachIndexed { index, placeable ->
                val row = index % rows
                placeable.placeRelative(
                    x = rowX[row],
                    y = rowY[row]
                )
                rowX[row] += placeable.width
            }
        }
    }
}


@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String
) {
    //圆角卡片
    Card(
        modifier = modifier,
        border = BorderStroke(color = Color.Black, width = Dp.Hairline),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, top = 3.dp, end = 8.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp, 16.dp)
                    .background(color = MaterialTheme.colors.secondary)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = text)
        }

    }
}

@Preview
@Composable
fun ChipPre(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(color = Color.LightGray)
            .padding(16.dp)
            .horizontalScroll(
                rememberScrollState()
            ),
        content = {
            StaggerGrid(modifier = Modifier) {
                for (topic in topics){
                    Chip(modifier = modifier.padding(8.dp), text = topic)
                }
            }
        }
    )
}