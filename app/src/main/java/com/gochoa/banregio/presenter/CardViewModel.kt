package com.gochoa.banregio.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gochoa.banregio.data.remote.ApiResponseStatus
import com.gochoa.banregio.data.remote.response.CardInfo
import com.gochoa.banregio.data.remote.response.MovementsResponseItem
import com.gochoa.banregio.domain.repository.RepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CardViewModel @Inject constructor(
    private val repository: RepositoryImp
) : ViewModel() {

    private val _card = MutableLiveData<ApiResponseStatus<CardInfo>>(ApiResponseStatus.Loading())
    val card: LiveData<ApiResponseStatus<CardInfo>> get() = _card

    private val _movements =
        MutableLiveData<ApiResponseStatus<List<MovementsResponseItem>>>(ApiResponseStatus.Loading())
    val movements: LiveData<ApiResponseStatus<List<MovementsResponseItem>>> get() = _movements

    private var _timer = MutableStateFlow<Long>(0)
    val timer: StateFlow<Long> = _timer


    init {
        getMovements()
        getCardInfo()
    }

    fun timerFlow(intervalMillis: Long): Flow<Long> = flow {
        _timer.value = intervalMillis
        var currentTime = 0L
        while (true) {
            emit(currentTime)
            delay(intervalMillis)
            currentTime += intervalMillis
            _timer.value = currentTime
        }
    }


    private fun getCardInfo() = viewModelScope.launch {
        repository.getCardInfo().let {
            when (it) {
                is ApiResponseStatus.Error -> {
                    _card.value = ApiResponseStatus.Error(it.messageID)
                }

                is ApiResponseStatus.Loading -> {}
                is ApiResponseStatus.Success -> _card.value = ApiResponseStatus.Success(it.data)
            }
        }
    }

    private fun getMovements() = viewModelScope.launch {
        repository.getMovements().let {
            when (it) {
                is ApiResponseStatus.Error -> _movements.value =
                    ApiResponseStatus.Error(it.messageID)

                is ApiResponseStatus.Loading -> {}
                is ApiResponseStatus.Success -> _movements.value =
                    ApiResponseStatus.Success(it.data)
            }
        }
    }

}