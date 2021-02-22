package com.saba.bookdetail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class BookDetailViewModel : ViewModel(){

    private val state : MutableStateFlow<BookDetailState> = MutableStateFlow(BookDetailState.init())
    val copyState: Flow<BookDetailState> = state

    fun prueba(){
        state.value = state.value.copy(isLoading = true)
    }

}