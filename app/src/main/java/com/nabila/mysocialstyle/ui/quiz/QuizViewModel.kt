package com.nabila.mysocialstyle.ui.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    private val _index = MutableLiveData(0)
    val index: LiveData<Int> get() = _index
    val selectedAnswers: MutableMap<Int, Int> = mutableMapOf()
    var amiableCount = 0
    var analyticalCount = 0
    var expressiveCount = 0
    var driverCount = 0

    fun next() {
        if (_index.value!! < 19) {
            _index.value = _index.value!! + 1
        }
    }

    fun prev(): Int {
        if (_index.value!! > 0) {
            _index.value = _index.value!! - 1
        }
        return _index.value!!
    }

    fun isFirstQuestion(): Boolean {
        return _index.value!! == 0
    }

    fun lastQuestionAnswered(): Boolean {
        return _index.value!! == 19 && isAnswered(19)
    }

    fun lastQuestionNotAnswered(): Boolean {
        return _index.value!! == 19 && !isAnswered(19)
    }

    fun questionAnswered(questionNumber: Int): Boolean {
        return _index.value!! < 19 && isAnswered(questionNumber)
    }

    fun questionNotAnswered(questionNumber: Int): Boolean {
        return _index.value!! < 19 && !isAnswered(questionNumber)
    }

    fun isAnswered(questionNumber: Int): Boolean {
        return selectedAnswers[questionNumber] != null
    }

    fun saveAnswer(selectedId: Int, questionNumber: Int) {
        selectedAnswers[questionNumber] = selectedId
        when (selectedId) {
            amiableId -> amiableCount++
            analyticalId -> analyticalCount++
            expressiveId -> expressiveCount++
            driverId -> driverCount++
        }
    }

    fun removeAnswer(i: Int) {
        if (selectedAnswers[i] == amiableId) amiableCount--
        else if (selectedAnswers[i] == analyticalId) analyticalCount--
        else if (selectedAnswers[i] == expressiveId) expressiveCount--
        else if (selectedAnswers[i] == driverId) driverCount--
    }
}