package com.saba.bookdetail

import android.provider.SyncStateContract
import androidx.lifecycle.ViewModel
import com.saba.bookdetail.usecases.GetBookDetailUseCase
import com.saba.core.base.CoroutineContextProvider
import com.saba.core.models.Book
import com.saba.core.models.ViewBook
import com.saba.core.usecases.category.GetCategoriesUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.json.JSONArray
import javax.inject.Inject

class BookDetailViewModel @Inject constructor(
    private val getBookDetailUseCase: GetBookDetailUseCase,
    private val coroutineContextProvider: CoroutineContextProvider) :
    ViewModel() {

    private val state: MutableStateFlow<BookDetailState> = MutableStateFlow(BookDetailState.init())
    private val job = SupervisorJob()
    val copyState: Flow<BookDetailState> = state

    fun onCreate(bookIsbn: String) {
        state.value = state.value.copy(isLoading = true, isIdling = false)
        getBookDetail(bookIsbn)
    }

    private fun getBookDetail(bookIsbn: String) {

        CoroutineScope(coroutineContextProvider.mainDispatcher + job).launch {

            val book = withContext(coroutineContextProvider.backgroundDispatcher) {
                ViewBook(getBookDetailUseCase.execute(isbn = bookIsbn))
            }
            state.value = state.value.copy(book = book, isLoading = false, isIdling = false)

        }

    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}