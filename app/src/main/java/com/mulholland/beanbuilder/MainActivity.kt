package com.mulholland.beanbuilder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mulholland.beanbuilder.ui.theme.BeanBuilderTheme

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
    var showShop by remember { mutableStateOf(true) }

    if (showShop) {
        Shop(onContinueClicked = { showShop = false })
    } else {
        BeanScreen(onContinueClicked = { showShop = true })
    }
}

@Composable
fun BeanScreen(onContinueClicked: () -> Unit) {
    Column {
        Row {
            Button(onClick = {
                onContinueClicked
            }) {
                Text(text = "Shop")
            }
            Button(onClick = {}) {
                Text(text = "Home")
            }
        }
        Text(text = "Beans")
    }
}

@Composable
fun Shop(onContinueClicked: () -> Unit) {
    var text by remember { mutableStateOf("Shop") }

    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        Row (

        ) {
            Button(onClick = {text = "Happy"}) {
                Text(text = "Shop")
            }
            Button(onClick = {
                onContinueClicked
            }) {
                Text(text = "Home")
            }
        }
        Text(text = text)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BeanBuilderTheme {
        BeanBuilder()
    }
}