package com.dhkim.clean.viewmodel

import android.annotation.SuppressLint
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.dhkim.clean.base.BaseViewModel
import com.dhkim.domain.model.TextItem
import com.dhkim.domain.usecase.TextUseCase
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class RoomViewModel(
    private val textUseCase: TextUseCase
    ): BaseViewModel() {
    private var compositeDisposable = CompositeDisposable()
    val statusText = MutableLiveData<String>()
    var textList: List<TextItem> = ArrayList()
    val textListObservable: MutableLiveData<List<TextItem>> = MutableLiveData(textList)
    val noDataNotification = ObservableBoolean(false)

    @SuppressLint("SimpleDateFormat")
    fun insertText(content: String) {
        val simpleDate = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val strNow: String = simpleDate.format(Date(System.currentTimeMillis()))
        CoroutineScope(IO).launch {
            compositeDisposable.add(
                textUseCase.insertText(TextItem(null, strNow, content)).subscribe { id ->
                    statusText.value = "$strNow '$content' is inserted."
                    getAllTexts()
                }
            )
        }
    }

    fun deleteText(textItem: TextItem) = CoroutineScope(IO).launch {
        textUseCase.deleteText(textItem)
    }

    fun getAllTexts() = textUseCase.getAllLocalTexts()

    fun getSearchTexts(query: String) = textUseCase.getSearchTexts(query)
}