package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    // Состояние: храним номер текущей картинки (1, 2 или 3)
    var currentArtwork by remember { mutableStateOf(1) }

    // Определяем контент в зависимости от номера
    val imageRes = when (currentArtwork) {
        1 -> R.drawable.art1
        2 -> R.drawable.art2
        else -> R.drawable.art3
    }
    val titleRes = when (currentArtwork) {
        1 -> R.string.art1_title
        2 -> R.string.art2_title
        else -> R.string.art3_title
    }
    val artistRes = when (currentArtwork) {
        1 -> R.string.art_artist
        2 -> R.string.art_artist
        else -> R.string.art_artist
    }
    val yearRes = when (currentArtwork) {
        1 -> R.string.art1_year
        2 -> R.string.art2_year
        else -> R.string.art3_year
    }

    // Вертикальная колонка (добавлен скролл для планшетов/альбомной ориентации)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Сама картинка
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = stringResource(id = titleRes),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = false) // Занимает доступное место, но не растягивается бесконечно
                .padding(horizontal = 24.dp, vertical = 8.dp) // 1. Внешний отступ от краев экрана
                .border(

                    width = 15.dp, // Толщина рамки
                    color = MaterialTheme.colorScheme.primary, // Цвет рамки (возьмем основной цвет темы)
                    shape = RoundedCornerShape(8.dp) // Слегка скругляем углы
                )
                .padding(8.dp) // 2. Внутренний отступ (паспарту) между рамкой и картинкой
                .fillMaxWidth()
                .weight(1f, fill = false)

        )

        Spacer(modifier = Modifier.height(24.dp))

        // Рамка с текстом (Title, Artist, Year)
        Surface(
            shadowElevation = 8.dp, // Тень
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(id = titleRes),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(modifier = Modifier.padding(top = 8.dp)) {
                    Text(
                        text = stringResource(id = artistRes),
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "(${stringResource(id = yearRes)})",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Кнопки навигации
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Кнопка "Назад"
            Button(onClick = {
                if (currentArtwork > 1) {
                    currentArtwork--
                } else {
                    currentArtwork = 3 // Если были на первой, переходим на последнюю
                }
            }) {
                Text(text = stringResource(id = R.string.prev_button))
            }

            // Кнопка "Вперед"
            Button(onClick = {
                if (currentArtwork < 3) {
                    currentArtwork++
                } else {
                    currentArtwork = 1 // Если были на последней, переходим на первую
                }
            }) {
                Text(text = stringResource(id = R.string.next_button))
            }
        }
        Spacer(modifier = Modifier.height(5.dp)) // Отступ снизу
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}