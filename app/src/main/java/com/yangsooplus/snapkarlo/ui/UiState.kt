package com.yangsooplus.snapkarlo.ui

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
}
