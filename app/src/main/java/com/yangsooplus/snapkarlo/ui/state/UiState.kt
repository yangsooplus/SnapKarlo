package com.yangsooplus.snapkarlo.ui.state

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
}
