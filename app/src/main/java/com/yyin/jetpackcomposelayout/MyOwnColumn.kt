package com.yyin.jetpackcomposelayout

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yyin.jetpackcomposelayout.ui.theme.JetpackComposelayoutTheme
import androidx.compose.material.Text
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    /**
     * @see
     * Layout是布局的主要核心组件。 它可用于测量和定位零个或多个布局子级
     * measurables 包含一个元素，对应于我们的每个布局子级。
     * constraints 是我们的父母布局目前衡量我们的约束。
     */
    Layout(
        modifier = Modifier,
        content = content
    ) { measurables, constraints ->
        //返回placeables的map
        val placeables = measurables.map { measurable ->
            /** 测量元素
             * @param measurables: List<Measurable>,
             * @param constraints: Constraints
             * 每个 Measurable在 measurables list 中对应于布局的一个布局子级，不同的孩子可以用不同的约束来衡量
             * 测量一个孩子会返回一个 Placeable，它显示了孩子根据自己的测量结果选择的尺寸。 根据孩子的大小，父母定义孩子的位置
             */
            measurable.measure(constraints)
        }
        var yPosition = 0
        //当前布局的大小
        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEach { placeable ->
                //设置元素的位置
                placeable.placeRelative(x=0,y=yPosition)
                //增加当前元素的高度
                yPosition += placeable.height
            }
        }
    }
}

@Composable
fun MyOwnColumnSample() {
    JetpackComposelayoutTheme {
        MyOwnColumn(Modifier.padding(8.dp)) {
            Text("MyOwnColumn")
            Text("place items")
            Text("vertically")
            Text("We've done it by hand!")
        }
    }
}

@Composable
@Preview
fun MyOwnColumnPre(){
   MyOwnColumnSample()
}