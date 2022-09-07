package com.yyin.jetpackcomposelayout

import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {
        //引用是使用createRefs()或createRefFor()创建的，ConstraintLayout中的每个可组合项都需要有与之关联的引用。
        val (button, text) = createRefs()
        Button(
            onClick = {},
            // 使用Modifier.constrainAs来提供约束，引用作为它的第一个参数
            // 在lambda表达式中指定约束规则
            modifier = Modifier.constrainAs(button) {
                // 约束条件是使用linkTo()或者其他有用指定的
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text(text = "Button")
        }
        Text(text = "Text", Modifier.constrainAs(text) {
            top.linkTo(button.bottom, margin = 16.dp)
            // 在ConstraintLayout中水平居中
            centerHorizontallyTo(parent)
        })
    }
}

@Composable
fun ConstraintLayoutContent2() {
    ConstraintLayout {
        val (button1, button2, text) = createRefs()
        Button(
            onClick = { },
            modifier = Modifier.constrainAs(button1){
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text(text = "Button1")
        }
        Text(text = "Text", Modifier.constrainAs(text) {
            top.linkTo(button1.bottom, margin = 16.dp)
            // text的中间在button的end位置
            centerAround(button1.end)
        })
        //将button和text组合起来，建立一个barrier
        val barrier = createEndBarrier(button1,text)
        Button(
            onClick = { },
            modifier = Modifier.constrainAs(button2){
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(barrier)
            }
        ) {
            Text(text = "Button2")
        }
    }
}

@Composable
@Preview
fun ConstraintLayoutPreview() {
    ConstraintLayoutContent2()
}
