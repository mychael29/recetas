package com.exercise.recetas.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {
    val error = MutableLiveData<Throwable>()
    val progress = MutableLiveData<Boolean>()

    fun executeSuspend(func: suspend () -> Unit) =
        viewModelScope.launch {
            try {
                progress.value = true
                func()
                progress.value = false
            } catch (ex: Exception) {
                progress.value = false
                handleException(ex)
            }
        }

    private fun handleException(ex: Exception) {
        ex.printStackTrace()
        error.value = ex
    }
}