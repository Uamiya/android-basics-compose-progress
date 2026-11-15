package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonApp()
                }
            }
        }
    }
}

@Composable
fun LemonApp() {
    // 1. Храним текущий шаг (от 1 до 4)
    var currentStep by remember { mutableStateOf(1) }
    // 2. Храним, сколько раз осталось нажать на лимон
    var squeezeCount by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // В зависимости от шага, показываем свою картинку и текст
        when (currentStep) {
            1 -> {
                LemonTextAndImage(
                    textId = R.string.lemon_tree_prompt,
                    imageId = R.drawable.lemon_tree,
                    descId = R.string.lemon_tree_desc,
                    onClick = {
                        // При клике на дерево: генерируем случайное число нажатий (2..4)
                        // и переходим на шаг 2 (сжатие лимона)
                        squeezeCount = (2..4).random()
                        currentStep = 2
                    }
                )
            }
            2 -> {
                LemonTextAndImage(
                    textId = R.string.lemon_squeeze_prompt,
                    imageId = R.drawable.lemon_squeeze,
                    descId = R.string.lemon_desc,
                    onClick = {
                        // Уменьшаем счетчик нажатий
                        squeezeCount--
                        // Если нажали достаточно раз, переходим к стакану (шаг 3)
                        if (squeezeCount == 0) {
                            currentStep = 3
                        }
                    }
                )
            }
            3 -> {
                LemonTextAndImage(
                    textId = R.string.lemon_drink_prompt,
                    imageId = R.drawable.lemon_drink,
                    descId = R.string.lemon_drink_desc,
                    onClick = {
                        // Выпили лимонад, переходим к пустому стакану (шаг 4)
                        currentStep = 4
                    }
                )
            }
            4 -> {
                LemonTextAndImage(
                    textId = R.string.lemon_restart_prompt,
                    imageId = R.drawable.lemon_restart,
                    descId = R.string.lemon_restart_desc,
                    onClick = {
                        // Начинаем заново с дерева (шаг 1)
                        currentStep = 1
                    }
                )
            }
        }
    }
}

// Вынесенная функция, чтобы не дублировать код (текст + картинка + рамка)
@Composable
fun LemonTextAndImage(
    textId: Int,
    imageId: Int,
    descId: Int,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(textId),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(imageId),
            contentDescription = stringResource(descId),
            modifier = Modifier
                .clickable { onClick() } // Делаем картинку кликабельной
                .border(
                    width = 2.dp,
                    color = Color(0xFF69CDD8), // Цвет рамки (105, 205, 216)
                    shape = RoundedCornerShape(4.dp) // Закругленные углы
                )
                .padding(16.dp) // Немного отступа внутри рамки
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonAppPreview() {
    LemonadeTheme {
        LemonApp()
    }
}