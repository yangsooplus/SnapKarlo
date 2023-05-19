package com.yangsooplus.snapkarlo.ui.maker

sealed class MakerUiState {
    object Idle : MakerUiState()
    object Loading : MakerUiState()
}
