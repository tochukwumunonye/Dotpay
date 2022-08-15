package com.example.dotpay.presentation.producttypes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dotpay.domain.model.Makeup
import com.example.dotpay.domain.repository.MakeupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import com.example.dotpay.presentation.producttypes.MakeupListViewState.Loading
import com.example.dotpay.presentation.producttypes.MakeupListViewState.Success
import com.example.dotpay.presentation.producttypes.MakeupListViewState.Error
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MakeupViewModel @Inject constructor(
    private val repository: MakeupRepository
): ViewModel() {

    private val _viewState = MutableStateFlow<MakeupListViewState>(Success(emptyList()))

    val viewState: StateFlow<MakeupListViewState> = _viewState

    fun search(brandName: String, productType: String) {
        _viewState.value = Loading

        viewModelScope.launch {
            runCatching {
                repository.getMakeupItems(brandName, productType)
            }.onFailure {  error ->
                _viewState.value = Error(error.localizedMessage ?: "An error occurred")
            }.onSuccess { makeUp ->
                _viewState.value = Success(makeUp)
            }
        }
    }
}

sealed class MakeupListViewState {
    object Loading : MakeupListViewState()
    data class Error(val error: String) : MakeupListViewState()
    data class Success(val product: List<Makeup>) : MakeupListViewState()
}
