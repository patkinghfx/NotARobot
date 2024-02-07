package com.example.notarobot

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notarobot.ui.theme.NotARobotTheme
import java.util.Random
import com.example.notarobot.models.TestImage
import com.example.notarobot.R.*

val dogOne = TestImage(false, drawable.dog1)
val dogTwo = TestImage(false, drawable.dog2)
val dogThree = TestImage(false, drawable.dog3)
val dogFour = TestImage(false, drawable.dog4)
val dogFive = TestImage(false, drawable.dog5)

val catOne = TestImage(true, drawable.cat1)
val catTwo = TestImage(true, drawable.cat2)
val catThree = TestImage(true, drawable.cat3)

var dogImages: List<TestImage> = mutableListOf (dogOne, dogTwo, dogThree, dogFour, dogFive)
var catImages: List<TestImage> = mutableListOf(catOne,catTwo,catThree)



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotARobotTheme(darkTheme = true) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Banner()
                    var catsShuffled: List<TestImage> = catImages.shuffled(random = Random())
                    var finalList: List<TestImage> = dogImages.plus(catsShuffled[0])
                    finalList = finalList.shuffled(random = Random())
                    RobotTestColumn(finalList)
                }
            }
        }
    }
}

@Composable
fun Banner() {
    Row{
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Blue,
            )) {
            Text("Select all squares with", modifier = Modifier.padding(top = 20.dp, start = 50.dp, bottom = 2.dp),)
            Text("Cats", fontWeight = FontWeight.Bold, fontSize = 30.sp, modifier = Modifier.padding(start = 50.dp, bottom = 50.dp),)
        }
    }
}

@Composable
fun RobotTestColumn(list: List<TestImage>){
    val context = LocalContext.current
    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .padding(start = 0.dp, top = 100.dp, end = 0.dp, bottom = 0.dp)) {
        LazyColumn() {
            items(6) { index ->
                var message = ""
                message = if (list[index].isCat) {
                    "Hurray, You're not a Robot!"
                } else {
                    "Oops, that's not a cat!"
                }
                Image(
                    modifier = Modifier.clickable {
                        Toast.makeText(
                            context,
                            message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                        .padding(4.dp)
                        .size(80.dp)
                    , painter = painterResource(id = list[index].imgResource),
                    contentDescription = "Dog",
                )
            }
        }
    }
}