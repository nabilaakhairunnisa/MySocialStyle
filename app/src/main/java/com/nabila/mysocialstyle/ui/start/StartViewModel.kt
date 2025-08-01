package com.nabila.mysocialstyle.ui.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StartViewModel : ViewModel() {

    private val _index = MutableLiveData(0)
    val index: LiveData<Int> get() = _index

    fun next() {
        if (_index.value!! < 2) {
            _index.value = _index.value!! + 1
        }
    }

    fun prev() {
        if (_index.value!! > 0) {
            _index.value = _index.value!! - 1
        }
    }

    fun setIndex(newIndex: Int) {
        _index.value = newIndex
    }
}



