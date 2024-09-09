package uz.devops.tasbih.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import uz.devops.tasbih.R

class Ui {
    @Composable
    fun RosaryUi(
        count: Int,
        CountPlus: () -> Unit,
        CountNull: () -> Unit,
        Sound: () -> Unit,
        songPermission: Boolean,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Malla)
        ) {
            val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(color = Malla)
            Image(
                painter = painterResource(id = R.drawable.ramadan),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Row(
                modifier = Modifier
                    .width(204.dp)
                    .height(254.dp)
                    .clip(RoundedCornerShape(40.dp, 40.dp, 85.dp, 85.dp))
                    .background(Color.White)
                    .align(alignment = Alignment.Center)
            ) {}
            Row(
                modifier = Modifier
                    .width(233.dp)
                    .height(183.dp)
                    .clip(RoundedCornerShape(60.dp, 60.dp, 80.dp, 80.dp))
                    .background(Color.White)
                    .align(alignment = Alignment.Center)
            ) {}
            Row(
                modifier = Modifier
                    .width(200.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(40.dp, 40.dp, 80.dp, 80.dp))
                    .background(Malla2)
                    .align(alignment = Alignment.Center)
            ) {

            }
            Row(
                modifier = Modifier
                    .width(230.dp)
                    .height(180.dp)
                    .clip(RoundedCornerShape(60.dp, 60.dp, 80.dp, 80.dp))
                    .background(Malla2)
                    .align(alignment = Alignment.Center)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 25.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .border(3.dp, Color.White, RoundedCornerShape(20.dp))
                            .width(190.dp)
                            .height(70.dp)
                            .align(alignment = Alignment.TopCenter)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(20.dp))
                                .background(Malla3)
                        )
                        Text(
                            text = count.toString(),
                            color = Color.White,
                            fontSize = 42.sp,
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                        )

                    }
                    Box(
                        modifier = Modifier
                            .offset(y = 90.dp, x = 30.dp)
                            .width(36.dp)
                            .height(36.dp)
                            .border(1.5.dp, Color.White, RoundedCornerShape(18.dp))
                            .clickable {
                                CountNull()
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_refresh_24),
                            contentDescription = null,
                            modifier = Modifier
                                .size(18.dp)
                                .align(alignment = Alignment.Center),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Box(
                        modifier = Modifier
                            .offset(y = (-30).dp, x = (-30).dp)
                            .width(36.dp)
                            .height(36.dp)
                            .align(alignment = Alignment.BottomEnd)
                            .border(1.5.dp, Color.White, RoundedCornerShape(18.dp))
                            .clickable {
                                Sound()
                            }
                    ) {
                        val icon = if (songPermission) {
                            R.drawable.baseline_volume_up_24
                        } else {
                            R.drawable.baseline_volume_off_24
                        }
                        Image(
                            painter = painterResource(id = icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(18.dp)
                                .align(alignment = Alignment.Center),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 80.dp)
            ) {
                Box(
                    modifier = Modifier
                        .border(3.dp, Color.White, RoundedCornerShape(35.dp))
                        .size(70.dp)
                        .align(Alignment.Center)
                        .clickable {
                            CountPlus()
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(35.dp))
                            .background(Purple40)
                    )
                }
            }

        }
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun GreetingPreview() {
        TasbihTheme {
            RosaryUi(count = 0, CountPlus = {}, CountNull = {}, Sound = {}, songPermission = false)
        }
    }
}
