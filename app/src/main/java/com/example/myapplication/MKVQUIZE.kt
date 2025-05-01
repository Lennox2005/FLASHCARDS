package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MKVQUIZE : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            data class Question(val text: String, val answer: Boolean)


            val questions = listOf(
                Question("The Great Wall of China was built in a single year", false),
                Question("Nelson Mandela was the first black President of SOUTH AFRICA", true),
                Question("The Pyramids of Egypt were built after the invention of the wheel", false),
                Question("The Berlin Wall fell in 1989", true),
                Question("Christopher Columbus discovered Americas in 1776", false,)
            )

            var currentIndex by remember { mutableStateOf(0) }
            var score by remember { mutableStateOf(0) }
            var feedback by remember { mutableStateOf("") }
            var quizComplete by remember { mutableStateOf(false) }
            val userAnswers = remember { mutableStateListOf<Boolean?>(null, null, null, null, null) }

            // Check if the quiz is complete
            if (currentIndex >= questions.size) {
                quizComplete = true
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (quizComplete) {
                    // Display score and feedback
                    Text(text = "Quiz Complete! Your score is: $score/${questions.size}")

                    val feedbackMessage = if (score >= 3) {
                        "Great job!"
                    } else {
                        "Keep practicing!"
                    }
                    Text(text = feedbackMessage)

                    // Review button to show results
                    Button(onClick = {
                        // Move to review screen
                        val answersArray = userAnswers.map { it ?: false }.toBooleanArray()

                        val intent = Intent(this@MKVQUIZE, ResultsActivity::class.java).apply {
                            putExtra("userAnswers",answersArray)
                            putExtra("questions",ArrayList(questions))
                            putExtra("score",score)
                        }
                        startActivity(intent)
                        currentIndex = questions.size + 1
                    }) {
                        Text(text = "Review",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                            )
                    }
                } else if (currentIndex == questions.size + 1) {
                    // Render the ReviewScreen
                    ReviewScreen(questions, userAnswers, score) {
                        // Reset game
                        currentIndex = 0
                        score = 0
                        feedback = ""
                        userAnswers.fill(null)  // Clear user answers
                    }
                } else {
                    // Show current question
                    val currentQuestion = questions[currentIndex]
                    Text(text = currentQuestion.text)

                    Row(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = {
                            userAnswers[currentIndex] = true // Store user's answer
                            if (currentQuestion.answer) score++
                            feedback = if (currentQuestion.answer) "Correct" else "Incorrect"
                        }) {
                            Text(text = "True",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Button(onClick = {
                            userAnswers[currentIndex] = false // Store user's answer
                            if (!currentQuestion.answer) score++
                            feedback = if (!currentQuestion.answer) "Correct" else "Incorrect"
                        }) {
                            Text(text = "False",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Text(text = feedback, modifier = Modifier.padding(top = 16.dp))

                    Button(onClick = {
                        // Move to the next question
                        currentIndex++
                        feedback = ""
                    }, modifier = Modifier.padding(top = 16.dp)) {
                        Text("Next",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold )
                    }
                }
        }
    }
}

    private fun ReviewScreen(questions: List<Any>, userAnswers: SnapshotStateList<Boolean?>, score: Int, function: () -> Unit) {

    }
}

