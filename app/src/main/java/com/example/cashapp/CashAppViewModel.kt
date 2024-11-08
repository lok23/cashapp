package com.example.cashapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cashapp.stocks.Stocks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CashAppViewModel : ViewModel() {
    private val _cashAppData = MutableLiveData<Stocks>()
    val cashAppData: LiveData<Stocks> get() = _cashAppData

    private val _errorData = MutableLiveData("")
    val errorData: LiveData<String> get() = _errorData

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    getValidCashAppData()
//                  getMalformedCashAppData()
//                  getEmptyCashAppData()
                } catch (e: java.io.IOException) {
                    _errorData.postValue("malformed json response")
                }
            }
        }
    }

    suspend fun getValidCashAppData() {
        _cashAppData.postValue(RetrofitClient.cashAppAPIService.getValidCashAppData())
    }

    suspend fun getMalformedCashAppData() {
        _cashAppData.postValue(RetrofitClient.cashAppAPIService.getMalformedCashAppData())
    }

    suspend fun getEmptyCashAppData() {
        _cashAppData.postValue(RetrofitClient.cashAppAPIService.getEmptyCashAppData())
    }
}