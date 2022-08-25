package com.paweljablonski.quote.screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paweljablonski.quote.exception.DataOrException
import com.paweljablonski.quote.model.Quote
import com.paweljablonski.quote.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QuoteScreenViewModel @Inject constructor(
    private val repository: QuoteRepository
): ViewModel(){

//    val quote: MutableState<DataOrException<Quote, Boolean, Exception>> = mutableStateOf(
//        DataOrException(null, true, Exception(""))
//    )

//    val quote: MutableLiveData<DataOrException<Quote, Boolean, Exception>> by lazy {
//        MutableLiveData<DataOrException<Quote, Boolean, Exception>>().also {
//            getQuote()
//        }
//    }


    private val _quote = MutableLiveData<DataOrException<Quote, Boolean, Exception>>()
    val quote: LiveData<DataOrException<Quote, Boolean, Exception>> get() = _quote


//    class MyViewModel : ViewModel() {
//        private val myLiveData = MutableLiveData(1)
//
//        init {
//            viewModelScope.launch {
//                myLiveData.asFlow().collect {
//                    // Do Something
//                }
//            }
//        }
//    }
//    Use StateFlow
//    class MyViewModel : ViewModel() {
//        private val myFlow = MutableStateFlow(1)
//        private val myLiveData = myFlow.asLiveData(viewModelScope.coroutineContext)
//    }
//
//    init{
//        viewModelScope.launch {
//            quote.observe(vi)
//        }
//    }

    init {
        getQuote()
    }


    fun fetchQuote(){
        getQuote()
        Log.d("FETCH", "BON APETITO FETCH: ${quote.value?.data.toString()}")
    }

    private fun getQuote(){
        viewModelScope.launch {
            _quote.value?.loading = true
            _quote.value = repository.getQuote()
            if (_quote.value!!.data.toString().isNotEmpty()){
                _quote.value!!.loading = false
            }
        }
    }

}
