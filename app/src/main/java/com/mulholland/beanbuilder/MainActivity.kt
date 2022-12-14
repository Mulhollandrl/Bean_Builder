package com.mulholland.beanbuilder

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mulholland.beanbuilder.ui.theme.BeanBuilderTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BeanBuilderTheme {
                BeanBuilder()
            }
        }
    }
}



@Composable
fun BeanBuilder() {
    var blueTheme by remember { mutableStateOf(true) }
    var showHome by remember { mutableStateOf(true) }

    val shopPlayer = MediaPlayer.create(LocalContext.current, R.raw.shop)

    var beans : Long by rememberSaveable { mutableStateOf(0) }

    var greenBeans by rememberSaveable { mutableStateOf(0) }
    var kidneyBeans by rememberSaveable { mutableStateOf(0) }
    var coffeeBeans by rememberSaveable { mutableStateOf(0) }
    var pintoBeans by rememberSaveable { mutableStateOf(0) }
    var chocolateBeans by rememberSaveable { mutableStateOf(0) }
    var jellyBeans by rememberSaveable { mutableStateOf(0) }

    LaunchedEffect(key1 = showHome) {
        val updateAmount = (greenBeans) + (10 * kidneyBeans) + (100 * coffeeBeans) + (1000 * pintoBeans) + (10000 * chocolateBeans) + (100000 * jellyBeans)
        updateBeans {
            beans += updateAmount
        }
    }

    BeanBuilderTheme(darkTheme = !blueTheme) {
        if (showHome) {
            BeanScreen(
                beanAmount = beans,
                onShopClicked = { showHome = false },
                onThemeClicked = { blueTheme = !blueTheme},
                onBeanIncrease = { beans += 1 }
            )
        } else {
            shopPlayer.start()
            Shop(
                onHomeClicked = { showHome = true },
                increaseGreenBeans = {
                    fun increaseDecrease() {
                        if (beans >= 10) {
                            greenBeans += 1
                            beans -= 10
                        }
                    }
                    increaseDecrease()
                },
                increaseKidneyBeans = {
                    fun increaseDecrease() {
                        if (beans >= 100) {
                            kidneyBeans += 1
                            beans -= 100
                        }
                    }
                    increaseDecrease()
                },
                increaseCoffeeBeans = {
                    fun increaseDecrease() {
                        if (beans >= 1000) {
                            coffeeBeans += 1
                            beans -= 1000
                        }
                    }
                    increaseDecrease()
                },
                increasePintoBeans = {
                    fun increaseDecrease() {
                        if (beans >= 10000) {
                            pintoBeans += 1
                            beans -= 10000
                        }
                    }
                    increaseDecrease()
                },
                increaseChocolateBeans = {
                    fun increaseDecrease() {
                        if (beans >= 100000) {
                            chocolateBeans += 1
                            beans -= 100000
                        }
                    }
                    increaseDecrease()
                },
                increaseJellyBeans = {
                    fun increaseDecrease() {
                        if (beans >= 1000000) {
                            jellyBeans += 1
                            beans -= 1000000
                        }
                    }
                    increaseDecrease()
                },
                greenBeans = greenBeans,
                kidneyBeans = kidneyBeans,
                coffeeBeans = coffeeBeans,
                pintoBeans = pintoBeans,
                chocolateBeans = chocolateBeans,
                jellyBeans = jellyBeans
            )
        }
    }
}

@Composable
fun BeanScreen(
    beanAmount: Long, onShopClicked: () -> Unit,
    onThemeClicked: () -> Unit,
    onBeanIncrease: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(color = MaterialTheme.colors.secondary)
    ) {
        Button(onClick = onThemeClicked) {
            Text(text = "Switch Theme")
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.secondary),
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(onClick = onShopClicked) {
                Text(text = "Shop")
            }
            Button(onClick = {}) {
                Text(text = "Home")
            }
        }

        Button(
            onClick = onBeanIncrease,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent
            )
        ) {
            Image(
                painter = painterResource(R.drawable.bean),
                contentDescription = "Big Bean",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(300.dp)
                    .width(256.dp)
            )
        }
        
        Text(
            text = "Beans: $beanAmount",
            color = MaterialTheme.colors.onSecondary
        )
    }
}

@Composable
fun Shop(
    onHomeClicked: () -> Unit,
    increaseGreenBeans: () -> Unit,
    increaseKidneyBeans: () -> Unit,
    increaseCoffeeBeans: () -> Unit,
    increasePintoBeans: () -> Unit,
    increaseChocolateBeans: () -> Unit,
    increaseJellyBeans: () -> Unit,
    greenBeans: Int,
    kidneyBeans: Int,
    coffeeBeans: Int,
    pintoBeans: Int,
    chocolateBeans: Int,
    jellyBeans: Int
) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.secondary)
    ) {
        Row {
            Button(onClick = {}) {
                Text(text = "Shop")
            }
            Button(onClick = onHomeClicked) {
                Text(text = "Home")
            }
        }

        Row {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = increaseGreenBeans,
                    modifier = Modifier.clip(CircleShape),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent
                    )
                ) {
                    Image(
                        painter = painterResource(R.drawable.greenbeans),
                        contentDescription = "Green Beans",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(128.dp)
                    )
                }

                Text(
                    text = "Owned: $greenBeans",
                    color = MaterialTheme.colors.onSecondary
                )
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = increaseKidneyBeans,
                    modifier = Modifier.clip(CircleShape),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent
                    )
                ) {
                    Image(
                        painter = painterResource(R.drawable.kidneybeans),
                        contentDescription = "Kidney Beans",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(128.dp)
                    )
                }

                Text(
                    text = "Owned: $kidneyBeans",
                    color = MaterialTheme.colors.onSecondary
                )
            }
        }

        Row {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = increaseCoffeeBeans,
                    modifier = Modifier.clip(CircleShape),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent
                    )
                ) {
                    Image(
                        painter = painterResource(R.drawable.coffeebeans),
                        contentDescription = "Coffee Beans",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(128.dp)
                    )
                }

                Text(
                    text = "Owned: $coffeeBeans",
                    color = MaterialTheme.colors.onSecondary
                )
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = increasePintoBeans,
                    modifier = Modifier.clip(CircleShape),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent
                    )
                ) {
                    Image(
                        painter = painterResource(R.drawable.pintobeans),
                        contentDescription = "Pinto Beans",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(128.dp)
                    )
                }

                Text(
                    text = "Owned: $pintoBeans",
                    color = MaterialTheme.colors.onSecondary
                )
            }
        }

        Row {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = increaseChocolateBeans,
                    modifier = Modifier.clip(CircleShape),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent
                    )
                ) {
                    Image(
                        painter = painterResource(R.drawable.chocolatebeans),
                        contentDescription = "Chocolate Beans",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(128.dp)
                    )
                }

                Text(
                    text = "Owned: $chocolateBeans",
                    color = MaterialTheme.colors.onSecondary
                )
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = increaseJellyBeans,
                    modifier = Modifier.clip(CircleShape),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent
                    )
                ) {
                    Image(
                        painter = painterResource(R.drawable.jellybeans),
                        contentDescription = "Jelly Beans",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(128.dp)
                    )
                }

                Text(
                    text = "Owned: $jellyBeans",
                    color = MaterialTheme.colors.onSecondary
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BeanBuilderTheme {
        BeanBuilder()
    }
}

@Preview(showBackground = true)
@Composable
fun ShopPreview() {
    BeanBuilderTheme {
        Shop({}, {}, {}, {}, {}, {}, {},0, 0, 0, 0, 0, 0)
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    BeanBuilderTheme {
        BeanScreen(0, {}, {}) {}
    }
}

fun updateBeans(update:() -> Unit) {
    val task = object : TimerTask() {
        override fun run() {
            update()
        }

    }
    val timer = Timer()
    timer.scheduleAtFixedRate(task, 1000, 1000)
}