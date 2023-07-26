package com.dhkim.clean.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {
    private val disposable = CompositeDisposable()
    protected fun addDisposal(d: Disposable) {
        disposable.add(d)
    }

    override fun onCleared() {
        if (!disposable.isDisposed) {
            disposable.clear()
        }
        super.onCleared()
    }
}