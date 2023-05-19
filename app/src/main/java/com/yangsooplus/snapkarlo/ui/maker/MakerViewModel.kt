package com.yangsooplus.snapkarlo.ui.maker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yangsooplus.snapkarlo.data.KarloRepository
import com.yangsooplus.snapkarlo.ui.state.ApiState
import com.yangsooplus.snapkarlo.data.remote.model.Prompt
import com.yangsooplus.snapkarlo.data.remote.model.PromptData
import com.yangsooplus.snapkarlo.data.remote.model.T2iResponse
import com.yangsooplus.snapkarlo.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MakerViewModel @Inject constructor(
    private val karloRepository: KarloRepository
) : ViewModel() {

    private val _t2iResponseState = MutableStateFlow<ApiState<T2iResponse>>(ApiState.Idle())
    val t2iResponseState = _t2iResponseState.asStateFlow()

    val t2IUiState: StateFlow<UiState> = _t2iResponseState.map {
        when (it) {
            is ApiState.Error -> UiState.Idle
            is ApiState.Loading -> UiState.Loading
            is ApiState.Success -> UiState.Idle
            is ApiState.Idle -> UiState.Idle
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, UiState.Idle)

    fun getT2iImage(text: String, batchSize: Int = 1) {
        _t2iResponseState.value = ApiState.Loading()

        viewModelScope.launch {
            karloRepository.getT2iImage(PromptData(Prompt(text = text, batchSize = batchSize))).catch {
                _t2iResponseState.value = ApiState.Error(message = it.message ?: "api error")
            }.collect {
                it.data?.let { t2i ->
                    _t2iResponseState.value = ApiState.Success(data = t2i)
                    karloRepository.insertImage(t2i.images[0], text)
                }
            }
        }
    }
}
