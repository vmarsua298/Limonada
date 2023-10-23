package com.example.limonada

import android.graphics.Bitmap
import android.graphics.Color.rgb
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.radialGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.limonada.ui.theme.LimonadaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimonadaTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableStateOf(1) }
    var timesClick by remember { mutableStateOf(0) }
    var numeroClick by remember{ mutableStateOf(0) }
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        color = MaterialTheme.colorScheme.background
    ) {
        when (currentStep) {
            1 -> {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    Spacer(modifier = Modifier.height(32.dp))
                    Image(
                        painter = painterResource(R.drawable.lemon_tree),
                        contentDescription = stringResource(R.string.limonero),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                currentStep = 2
                            }
                    )
                    Text(
                        text = stringResource(id = R.string.texto1),
                        fontSize = 18.sp
                    )
                    timesClick = (2..4).random()
                    numeroClick = 0
                }
            }
            2 -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Spacer(modifier = Modifier.height(32.dp))
                        Image(
                            painter = painterResource(R.drawable.lemon_squeeze),
                            contentDescription = stringResource(R.string.limon),
                            modifier = Modifier.clickable {
                                if (timesClick-1 == numeroClick) {
                                    currentStep = 3
                                } else {
                                    numeroClick++
                                }
                            }
                        )
                        Text(
                            text = stringResource(id = R.string.texto2),
                            fontSize = 18.sp
                        )
                        Text(
                            text = "Necesitas pulsar $timesClick veces",
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Has pulsado $numeroClick veces",
                            fontSize = 16.sp
                        )
                    }
            }
            3 -> {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    Spacer(modifier = Modifier.height(32.dp))
                    Image(
                        painter = painterResource(R.drawable.lemon_drink),
                        contentDescription = stringResource(R.string.vaso_de_limonada),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                currentStep = 4
                            }
                    )
                    Text(
                        text = stringResource(id = R.string.texto3),
                        fontSize = 18.sp
                    )
                }
            }
            4 -> {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    Spacer(modifier = Modifier.height(32.dp))

                    Image(
                        painter = painterResource(R.drawable.lemon_restart),
                        contentDescription = stringResource(R.string.vaso_vacio),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                currentStep = 1
                            }

                    )
                    Box(
                            Modifier.drawWithCache {
                                val gradient = Brush.linearGradient(
                                    colors = listOf(Color.Red, Color.Blue),
                                    start = Offset.Zero,
                                    end = Offset(size.width, size.height)
                                )
                                onDrawBehind {
                                    drawRect(gradient)
                                }
                            }
                            )
                    Text(
                        text = stringResource(id = R.string.texto4),
                        fontSize = 18.sp
                    )
                }
            }
}

}
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LimonadaTheme {
        LemonApp()
    }
}
