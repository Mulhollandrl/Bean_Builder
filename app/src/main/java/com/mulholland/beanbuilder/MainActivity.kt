package com.mulholland.beanbuilder

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mulholland.beanbuilder.ui.theme.BeanBuilderTheme
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule

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
    val coroutineScope = rememberCoroutineScope()

    var showHome by remember { mutableStateOf(true) }

    var beans by rememberSaveable { mutableStateOf(0) }

    var greenBeans by rememberSaveable { mutableStateOf(0) }
    var kidneyBeans by rememberSaveable { mutableStateOf(0) }
    var coffeeBeans by rememberSaveable { mutableStateOf(0) }
    var pintoBeans by rememberSaveable { mutableStateOf(0) }
    var chocolateBeans by rememberSaveable { mutableStateOf(0) }
    var jellyBeans by rememberSaveable { mutableStateOf(0) }

    LaunchedEffect(key1 = true, block = {
        beans = updateBeans(
            currentBeans = beans,
            updateAmount = (greenBeans) + (10 * kidneyBeans) + (100 * coffeeBeans) + (1000 * pintoBeans) + (10000 * chocolateBeans) + (100000 * jellyBeans)
        )
    })

    if (showHome) {
        BeanScreen(
            beanAmount = beans,
            onContinueClicked = { showHome = false },
            onBeanIncrease = { beans += 1 }
        )
    } else {
        Shop(
            beanAmount = beans,
            onContinueClicked = { showHome = true },
            increaseGreenBeans = { fun increaseDecrease(){
                if (beans > 10) {
                    greenBeans += 1
                    beans -= 10
                }
            }
                increaseDecrease() },
            increaseKidneyBeans = { fun increaseDecrease(){
                if (beans > 100) {
                    kidneyBeans += 1
                    beans -= 100
                }
            }
                increaseDecrease() },
            increaseCoffeeBeans = { fun increaseDecrease(){
                if (beans > 1000) {
                    coffeeBeans += 1
                    beans -= 1000
                }
            }
                increaseDecrease() },
            increasePintoBeans = { fun increaseDecrease(){
                if (beans > 10000) {
                    pintoBeans += 1
                    beans -= 10000
                }
            }
                increaseDecrease() },
            increaseChocolateBeans = { fun increaseDecrease(){
                if (beans > 100000) {
                    chocolateBeans += 1
                    beans -= 100000
                }
            }
                increaseDecrease() },
            increaseJellyBeans = { fun increaseDecrease(){
                if (beans > 1000000) {
                    jellyBeans += 1
                    beans -= 1000000
                }
            }
                increaseDecrease() },
            greenBeans = greenBeans,
            kidneyBeans = kidneyBeans,
            coffeeBeans = coffeeBeans,
            pintoBeans = pintoBeans,
            chocolateBeans = chocolateBeans,
            jellyBeans = jellyBeans
        )
    }
}

@Composable
fun BeanScreen(beanAmount: Int, onContinueClicked: () -> Unit, onBeanIncrease: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(onClick = onContinueClicked) {
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
        
        Text(text = "Beans: $beanAmount")
    }
}

@Composable
fun Shop(
    beanAmount: Int,
    onContinueClicked: () -> Unit,
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
        modifier = Modifier.fillMaxSize()
    ) {
        Row {
            Button(onClick = {}) {
                Text(text = "Shop")
            }
            Button(onClick = onContinueClicked) {
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

                Text(text = "Owned: $greenBeans")
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

                Text(text = "Owned: $kidneyBeans")
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

                Text(text = "Owned: $coffeeBeans")
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

                Text(text = "Owned: $pintoBeans")
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

                Text(text = "Owned: $chocolateBeans")
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

                Text(text = "Owned: $jellyBeans")
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
        Shop(0, {}, {}, {}, {}, {}, {}, {}, 0, 0, 0, 0, 0, 0, )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    BeanBuilderTheme {
        BeanScreen(0, {}, {})
    }
}

fun updateBeans(currentBeans: Int, updateAmount: Int): Int {
    var newBeans = 0

    while(true) {
        Thread.sleep(1000L)

        return currentBeans + updateAmount
    }
}