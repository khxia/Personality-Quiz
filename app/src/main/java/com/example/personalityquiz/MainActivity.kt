package com.example.personalityquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    var gameStart: Boolean = false
    var questionNum: Int = 0
    var points: Int = 0
    val questions = arrayListOf(
        "q1",
        "q2",
        "q3",
        "q4",
        "q5",
        "q6",
        "q7"
        )

    lateinit var leftButton: Button
    lateinit var titleText: TextView
    lateinit var questionText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rightButton: Button = findViewById<Button>(R.id.rightButton)
        rightButton.setOnClickListener { rightButtonClicked() }

        leftButton = findViewById<Button>(R.id.leftButton)
        questionText = findViewById(R.id.questionText)
        titleText = findViewById<TextView>(R.id.titleText)

    }

    private fun rightButtonClicked(){
        if(rightButton.text == "Start"){
            playGame()
        }
        else if(rightButton.text == getString(R.string.playAgain)) {
            questionNum = 0
            points = 0
            playGame()
        }
        else {
            points++
            questionNum++
            playGame()
        }

    }

    private fun leftButtonClicked(){
        if(leftButton.text == "Quit"){
            finishAffinity()
            exitProcess(0)
        }
        else {
            points--
            questionNum++
            playGame()
        }

    }

    private fun playGame(){
        if (questionNum == 0 && !gameStart){
            rightButton.text = getString(R.string.yes)
            leftButton.text = getString(R.string.no)
            gameStart = true
        }
        else if (questionNum == 7){
            println("I was here")
            gameStart = false
            showResults()
            return
        }

        val qNumber = questionNum + 1
        titleText.text = "Question $qNumber"
        questionText.text = questions[questionNum]

        rightButton.setOnClickListener { rightButtonClicked() }
        leftButton.setOnClickListener { leftButtonClicked() }

    }

    private fun showResults(){
        val personality: String = when(points) {
            -7 -> {"Scum"}
            -6 -> {"Evil"}
            -5 -> {"Small-minded"}
            -4 -> {"Greedy"}
            -3 -> {"Need Help"}
            -2 -> {"Idiot"}
            -1 -> {"Slightly degenerate"}
            0 -> {"Acceptable"}
            1 -> {"Unremarkable"}
            2 -> {"A Weeb"}
            3 -> {"Slightly useful to society"}
            4 -> {"Has a chance to succeed in life"}
            5 -> {"Wait I just realized this is supposed to be personalities"}
            6 -> {"Either you are faking it or you are a samaritan"}
            7 -> {"Stop lying you showoff, go home"}
            else-> {"You have been deemed unjudgeable by the Sibyl System"}
        }
        // In actual workshop can use if statements for simplicity

        titleText.text = getString(R.string.resultsTitle)
        questionText.text = personality

        leftButton.text = getString(R.string.quit)
        rightButton.text = getString(R.string.playAgain)

        rightButton.setOnClickListener { rightButtonClicked() }
        leftButton.setOnClickListener { leftButtonClicked() }
    }


}

