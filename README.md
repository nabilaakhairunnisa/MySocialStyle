# My Social Style

An Android application for identifying an individual's social style based on the Social Styles theory.

## About the Social Styles Theory

The Social Styles theory categorizes individuals into four distinct social styles based on their behavior and communication patterns. Each style has unique characteristics, strengths, and weaknesses.

The four social styles are:
* **Amiable:** Focused on relationships, cooperation, and loyalty.
* **Analytical:** Driven by logic, facts, and precision.
* **Expressive:** Energetic, creative, and highly social.
* **Driver:** Goal-oriented, decisive, and focused on results.

**The content and descriptions for the quiz, including the traits, strengths, weaknesses, and communication tips, are compiled from a variety of academic and psychological literatures on the subject of social styles.**

## Features

* **User Authentication:** Secure sign-up and sign-in using Firebase Authentication (email and password).
* **Quiz Data Storage:** User's quiz results are saved to Firebase Realtime Database for historical tracking.
* **Shareable Results:** Users can share their quiz results in an image format.
* **Interactive Quiz Navigation:** Users can navigate back to previous questions, with their selected answers automatically saved and displayed via a `MutableMap<Int, Int>` that maps the `radioButtonId` to the question number.

## Documentation
* [TSD (Technical System Documentation)](https://github.com/nabilaakhairunnisa/MySocialStyle/blob/master/documentation/TSD.pdf): This document outlines the technical architecture, data models, and implementation details of the application.
* [FSD (Functional System Documentation)](https://github.com/nabilaakhairunnisa/MySocialStyle/blob/master/documentation/FSD.pdf): This document describes the application's features and how it functions from a user's perspective. 

## Demo

<div align="center">
  <img src="https://github.com/nabilaakhairunnisa/mysocialstyle/blob/master/demo/auth.gif"" width="250" />
  <h4>Authentication</h4>
  <p>The authentication process includes validation for email format and minimum password length.</p>
</div>

<div align="center">
  <img src="https://github.com/nabilaakhairunnisa/MySocialStyle/blob/master/demo/main.gif" width="250" />
  <h4>Main Screen & Quiz History</h4>
  <p>The main screen displays a list of past quiz results, allowing users to review their social style history.</p>
</div>

<div align="center">
  <img src="https://github.com/nabilaakhairunnisa/mysocialstyle/blob/master/demo/quiz.gif" width="250" />
  <h4>Quiz</h4>
  <p>The "Next" button remains inactive until a user selects an answer. Upon pressing, it shows an error message. The "Finish" button appears and becomes active only on the last question after an answer is selected.</p>
</div>

<div align="center">
  <img src="https://github.com/nabilaakhairunnisa/mysocialstyle/blob/master/demo/result.gif" width="250" />
  <h4>Quiz Results</h4>
  <p>The results screen provides details about the identified social style, including strengths, weaknesses, solutions, communication tips, and suitable roles/tasks.</p>
</div>

<div align="center">
  <img src="https://github.com/nabilaakhairunnisa/mysocialstyle/blob/master/demo/share.gif" width="250" />
  <h4>Share Quiz Results</h4>
  <p>Users can easily share their quiz results as a generated image to social media or other applications.</p>
</div>

## Code Snippets

### Quiz Content Loading

Questions and answers for the quiz are fetched from Android string resources.

```kotlin
// src/main/java/com/nabila/mysocialstyle/ui/quiz/QuizUtils.kt
question.text = resources.getStringArray(R.array.questions)[index]
amiableAnswer.text = resources.getStringArray(R.array.amiableAnswers)[index]
analyticalAnswer.text = resources.getStringArray(R.array.analyticalAnswers)[index]
expressiveAnswer.text = resources.getStringArray(R.array.expressiveAnswers)[index]
driverAnswer.text = resources.getStringArray(R.array.driverAnswers)[index]
```

### Social Style Identification Logic

The application determines the user's social style based on the highest score from the quiz.

```kotlin
// src/main/java/com/nabila/mysocialstyle/ui/result/ResultViewModel.kt
private fun determineSocialStyle(): String {
    return when {
        ami >= ana && ami >= exp && ami >= dri -> "Amiable"
        ana >= ami && ana >= exp && ana >= dri -> "Analytical"
        exp >= ami && exp >= ana && exp >= dri -> "Expressive"
        else -> "Driver"
    }
}
```

## Future Development
Multilingual Support: Adding an option to select different languages.
