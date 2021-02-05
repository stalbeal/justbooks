package com.saba.core.base

import com.saba.core.base.Result as MVIResult
import com.saba.core.base.ViewState as MVIViewState

interface Reducer<in Result : MVIResult, ViewState : MVIViewState> {

    suspend fun reduce(state: ViewState, from: Result): ViewState
}