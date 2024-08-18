package com.football.league.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel


open class BaseViewHolder : ViewModel() {
    protected val scope = MainScope()

    fun onDestroyView() {
        scope.cancel()
    }
}