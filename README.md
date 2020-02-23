# Personality-Quiz


### Let's start coding!

First of all, we have to start with how our app will look. Ultimately, we want to get it to look something like this:

<img src="https://github.com/khxia/Personality-Quiz/blob/master/images/Preview.PNG">

As you can see, this all starts with the `activity_main` file, which contains the main layout code for our initial activity. 

I will demonstrate how to design our app using the design tab. If you have trouble following along, you can always copy and paste the XML code [here](app/src/main/java/com/example/personalityquiz/MainActivity.kt).


- After copying the XML, you might find references to `@string` resources in the layout XML files. We have to create the string resource yourself. 

With all the layout out of the way, we can start implementing the logic behind our app.

In the kotlin file, the main thing that we are concerned with is the `MainActivity` class. 
And within the `MainActivity` class, the most important function, is the `OnCreate()` function, which is the function that is first executed when our activity is created. 

So now, we are going to use the `findViewByID` method to get a reference to any view that we created in our XML file. 
Then, we use the `setOnClickListener` method to give some functionality to our buttons so that once the button is clicked, it will execute whatever function is inside the curly braces `{}`. 

```kt
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
```
For those who are new to kotlin, the `lateinit` keyword is just basically telling android that this variable exists and we will define it later. 

Now lets define these functions. 

Feel free to copy and paste the code below if you would rather follow along with the workshop.

But before that, we are going to define some member variables for our class. 

```kt
var gameStart: Boolean = false
    var questionNum: Int = 0
    var points: Int = 0
    val questions = arrayListOf(
        "If you found a $100 bill on the floor, would you keep it for yourself?",
        "Do you say sorry to people for no reason?",
        "Do you watch anime?",
        "Do you watch Kdrama?",
        "Would you say money is an indicator of success?",
        "Do you like dogs?",
        "Do you like BPlate?"
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
```

Now, we can start with our first function. 

```kt
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
```

```kt
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
```

```kt
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
            5 -> {"Koreaboo"}
            6 -> {"Either you are faking it or you are a samaritan"}
            7 -> {"Stop lying you showoff, go home"}
            else-> {"You have been deemed unjudgeable by the Sibyl System"}
        }

        titleText.text = getString(R.string.resultsTitle)
        questionText.text = personality

        leftButton.text = getString(R.string.quit)
        rightButton.text = getString(R.string.playAgain)

        rightButton.setOnClickListener { rightButtonClicked() }
        leftButton.setOnClickListener { leftButtonClicked() }
    }
```

