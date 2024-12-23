package com.exercise.recetas.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.exercise.recetas.databinding.ActivitySplashscreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            delay(2000)
            goToHome()
        }

    }

    private fun goToHome() {
        if (isOnboardingCompleted()) {
            goToMainActivity()
        } else {
            goToOnboarding()
        }
    }

    private fun isOnboardingCompleted(): Boolean {
        val prefs: SharedPreferences = getSharedPreferences("OnboardingPrefs", MODE_PRIVATE)
        return prefs.getBoolean("OnboardingCompleted", false)
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToOnboarding() {
        val intent = Intent(this, OnboardingActivity::class.java)
        startActivity(intent)
        finish()
    }

}