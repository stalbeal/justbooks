package com.saba.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import com.saba.core.base.Result as MVIResult
import com.saba.core.base.ViewState as MVIViewState
import com.saba.core.base.Wish as MVIWish

abstract class AbstractViewModel<Wish: MVIWish, ViewState : MVIViewState>(
    initialState: ViewState
) : ViewModel() {

    protected abstract val coroutineContextProvider: CoroutineContextProvider

    private val _state: StateFlow<ViewState> = MutableStateFlow(initialState)

    /**
     * La vista escucha este estado para saber las salidas
     */
    val state: Flow<ViewState>
        get() = _state

    /**
     * La vista solo debe conocer este metodo del viewModel,
     * por aca ingresan las peticiones de la vista hacia el view model
     */
    abstract fun processWish(wish: Wish)
}


@Deprecated(
    "Use the no compat version",
    ReplaceWith("replace with", "com.saba.core.base.AbstractViewModel")
)
abstract class AbstractViewModelCompat<Wish : MVIWish, Result : MVIResult, ViewState : MVIViewState>(
    initialState: ViewState
) : ViewModel() {

    protected abstract val reducer: Reducer<Result, ViewState>
    protected abstract val coroutineContextProvider: CoroutineContextProvider

    private val wishProcessor = ConflatedBroadcastChannel<Wish>()

    val state: Flow<ViewState> = wishProcessor.asFlow()
        .flatMapMerge {
            getResult(it)
        }.scan(initialState) { state, result ->
            reducer.reduce(state, result)
        }

    protected val defaultExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, exception -> throw exception }

    protected abstract suspend fun getResult(wish: Wish): Flow<Result>


    fun onWish(wish: Wish) {
        wishProcessor.offer(wish)
    }

    override fun onCleared() {
        wishProcessor.close()
        super.onCleared()
    }
}

