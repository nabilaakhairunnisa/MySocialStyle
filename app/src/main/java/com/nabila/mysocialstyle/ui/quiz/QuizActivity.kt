package com.nabila.mysocialstyle.ui.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.nabila.mysocialstyle.databinding.ActivityQuizBinding
import com.nabila.mysocialstyle.ui.result.ResultActivity

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding
    private val viewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.index.observe(this) { i ->
            isFirstQuestion()
            isLastQuestion()
            next(i)
            showSelectedAnswer(i)
            showqna(i)
            binding.apply {
                amiableAnswer.setOnClickListener { selectAnswer(i) }
                analyticalAnswer.setOnClickListener { selectAnswer(i) }
                expressiveAnswer.setOnClickListener { selectAnswer(i) }
                driverAnswer.setOnClickListener { selectAnswer(i) }
            }
        }
    }

    private fun showSelectedAnswer(index: Int) {
        binding.radioGroup.check(viewModel.selectedAnswers[index] ?: 0)
    }

    private fun isFirstQuestion() {
        if (viewModel.isFirstQuestion()) {
            disablePrevButton()
            binding.btPrev.setOnClickListener { }
        } else {
            enablePrevButton()
            binding.btPrev.setOnClickListener { viewModel.removeAnswer(viewModel.prev()) }
        }
    }

    private fun isLastQuestion() {
        if (viewModel.lastQuestionAnswered()) {
            enableFinishButton()
            binding.btNext.setOnClickListener { goToResultPage() }
        } else if (viewModel.lastQuestionNotAnswered()) {
            disableFinishButton()
            binding.btNext.setOnClickListener { showErrorMessage(this) }
        }
    }

    private fun next(i: Int) {
        if (viewModel.questionAnswered(i)) {
            enableNextButton()
            binding.btNext.setOnClickListener {
                viewModel.saveAnswer(answerId(), i)
                viewModel.next()
            }
        } else if (viewModel.questionNotAnswered(i)) {
            disableNextButton()
            binding.btNext.setOnClickListener { showErrorMessage(this) }
        }
    }

    private fun selectAnswer(i: Int) {
        if (i < 19) {
            enableNextButton()
            viewModel.saveAnswer(answerId(), i)
            viewModel.next()
        } else if (i == 19) {
            viewModel.removeAnswer(i)
            viewModel.saveAnswer(answerId(), i)
            isLastQuestion()
        }
    }

    private fun goToResultPage() {
        val intent = Intent(this, ResultActivity::class.java)
            .putExtra("AmiableResult", viewModel.amiableCount)
            .putExtra("AnalyticalResult", viewModel.analyticalCount)
            .putExtra("ExpressiveResult", viewModel.expressiveCount)
            .putExtra("DriverResult", viewModel.driverCount)
        startActivity(intent)
        finish()
    }
}