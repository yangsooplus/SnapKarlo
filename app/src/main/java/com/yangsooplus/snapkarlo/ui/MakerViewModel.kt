package com.yangsooplus.snapkarlo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yangsooplus.snapkarlo.data.ApiState
import com.yangsooplus.snapkarlo.data.KarloRepository
import com.yangsooplus.snapkarlo.data.model.Prompt
import com.yangsooplus.snapkarlo.data.model.PromptData
import com.yangsooplus.snapkarlo.data.model.T2iResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MakerViewModel @Inject constructor(
    private val karloRepository: KarloRepository
) : ViewModel() {

    private val _t2iResponseState = MutableStateFlow<ApiState<T2iResponse>>(ApiState.Loading())
    val t2iResponseState: StateFlow<ApiState<T2iResponse>> = _t2iResponseState

    fun getT2iImage(text: String, batchSize: Int = 1) {
        _t2iResponseState.value = ApiState.Loading()

        viewModelScope.launch {
            karloRepository.getT2iImage(PromptData(Prompt(text = text, batchSize = batchSize))).catch {
                _t2iResponseState.value = ApiState.Error(message = it.message ?: "api error")
            }.collect {
                it.data?.let { t2i ->
                    _t2iResponseState.value = ApiState.Success(data = t2i)
                }
            }
        }
    }
}
