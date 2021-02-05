package com.saba.core.base

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CoroutineContextProvider  constructor(
    val mainDispatcher: CoroutineDispatcher,
    val backgroundDispatcher: CoroutineDispatcher
)