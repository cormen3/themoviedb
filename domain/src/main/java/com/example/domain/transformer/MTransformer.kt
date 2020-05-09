package com.example.domain.executor.transformer

import io.reactivex.MaybeTransformer

/**
 * A transformer to io thread for flowables.
 */
abstract class MTransformer<T> : MaybeTransformer<T, T>