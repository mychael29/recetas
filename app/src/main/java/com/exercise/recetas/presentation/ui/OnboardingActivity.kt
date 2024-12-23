package com.exercise.recetas.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.exercise.recetas.R
import com.exercise.recetas.databinding.ActivityOnboardingBinding

class OnboardingActivity: AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private val pages = listOf(
        Page("Bienvenido a la app", R.drawable.ic_dashboard_black_24dp),
        Page("Descubre nuestras recetas para tus comidas", R.drawable.ic_home_black_24dp),
        Page("Podras ver tus recetas favoritas en cualquier momento", R.drawable.ic_launcher_foreground)
    )
    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updatePage()

        binding.btnContinue.setOnClickListener {
            if (currentPage < pages.size - 1) {
                currentPage++
                updatePage()
            } else {
                markOnboardingCompleted()
                goToMainActivity()
            }
        }
    }

    private fun updatePage() {
        val page = pages[currentPage]
        binding.textView.text = page.text
        binding.imageView.setImageResource(page.imageRes)
        binding.btnContinue.text = if (currentPage == pages.size - 1) "Finalizar" else "Siguiente"
        binding.btnContinue.setBackgroundColor(getColor(if (currentPage == pages.size - 1) R.color.purple_500 else R.color.teal_700 ))
    }

    private fun markOnboardingCompleted() {
        getSharedPreferences("OnboardingPrefs", MODE_PRIVATE)
            .edit()
            .putBoolean("OnboardingCompleted", true)
            .apply()
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    data class Page(val text: String, val imageRes: Int)

}