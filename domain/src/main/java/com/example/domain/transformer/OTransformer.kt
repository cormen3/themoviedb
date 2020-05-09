package com.example.domain.executor.transformer

import io.reactivex.ObservableTransformer

/**
 * A transformer to io thread for flowables.
 */
abstract class OTransformer<T> : ObservableTransformer<T, T>