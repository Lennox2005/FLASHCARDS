package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Create a Column layout to display the welcome message and the button
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Welcome message
                Text(text = "WELCOME HISTORICAL ENTHUSIAST",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Black
                )
                Text(text = "This app features TRUE/FALSE questions",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black
               )
                Text(text = "Answer the question to get feedback on your performance."+
                            "At the end of the quiz, you will see which answer were correct and which were wrong.\n"+
                            "You will also have the option to try the quiz again!",
                    modifier = Modifier.padding(bottom = 20.dp)


                )

                // Start button
                Button(onClick = {
                    // Explicit intent to start MKVQUIZE
                    val intent = Intent(this@MainActivity, MKVQUIZE::class.java)
                    startActivity(intent)
                }) {
                    Text(text = "Start",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                        )
                }
            }
        }
    }
}

