# My Social Style

An Android application for identifying an individual's social style based on the Social Styles theory, designed to enhance effective communication and collaboration in personal and professional interactions.

<img src="https://github.com/nabilaakhairunnisa/mysocialstyle/blob/master/demo/ui.png" />

## Demo
[see Demo on YouTube](https://youtube.com/shorts/5R8UExCCEck)

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

## How to Install
You can try the **MySocialStyle App** by installing the APK file directly on your Android device.
1. Download the latest release APK: [my-social-style.apk](https://github.com/nabilaakhairunnisa/MySocialStyle/raw/refs/heads/master/my-social-style.apk)
2. Open the downloaded file on your Android device.
3. If prompted, allow installation from unknown sources:
   - Go to **Settings > Security > Install unknown apps**.
   - Enable permission for your browser or file manager.
4. Tap **Install** and wait for the process to finish.
5. Once installed, open **MySocialStyle App** and start exploring your social style.

**Note:** The app is safe to install, but since it’s not from Google Play, Android requires extra permission for security.

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
