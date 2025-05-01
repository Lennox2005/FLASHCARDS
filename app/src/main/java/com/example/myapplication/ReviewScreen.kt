package com.example.myapplication

import Question.Question
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class ReviewScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Get data from intent
            val userAnswers = intent.getBooleanArrayExtra("userAnswers") ?: booleanArrayOf()
            val questions = intent.getParcelableArrayListExtra<Question>("questions") ?: arrayListOf()
            val score = intent.getIntExtra("score", 0)

            // Layout to display results
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Display score
                Text(text = "Your Score: $score/${questions.size}")

                // Loop through questions and answers
                for (i in questions.indices) {
                    val question = questions[i]
                    val userAnswer = userAnswers.getOrNull(i) ?: false
                    val isCorrect = question.answer== userAnswer

                    // Display the question
                    Text(text = "Q${i + 1}: ${question.text}")
                    Text(text = "Correct Answer: ${if (question.answer as Boolean) "True" else "False"}")
                    Text(text = "Your Answer: ${if (userAnswer) "True" else "False"}")
                    Text(text = "Result: ${if (isCorrect) "Correct" else "Incorrect"}")
                    Spacer(modifier = Modifier.height(8.dp)) // Spacing between questions
                }

                // Try Again button to start the quiz again
                Button(onClick = {
                    // Restart the Quizscreen activity
                    val intent = Intent(this@ReviewScreen, ResultsScreen::class.java)
                    startActivity(intent)
                    finish() // Close the current activity
                }, modifier = Modifier.padding(top = 16.dp)) {
                    Text(text = "Try Again")
                }

                // Exit button to close the app
                Button(onClick = {
                    finishAffinity() // Close the app
                }, modifier = Modifier.padding(top = 8.dp)) {
                    Text(text = "Exit")
                }
            }
        }
    }
}

