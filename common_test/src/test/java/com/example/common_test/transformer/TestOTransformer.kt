package com.example.common_test.transformer

import com.example.domain.executor.transformer.OTransformer
import io.reactivex.Observable
import io.reactivex.ObservableSource

class TestOTransformer<T> : OTransformer<T>() {
    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream
    }
}