package com.example.wifirecons.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wifirecons.app.WifiRecon
import com.example.wifirecons.room.Data
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {

    private val _data = MutableLiveData<List<Data>>()
    val data: LiveData<List<Data>> get() = _data


    fun insetData(reconData: Data) {
        viewModelScope.launch {
            WifiRecon.appDao.insertData(reconData)
        }
    }

    fun fetchData() {
        viewModelScope.launch {
            val response = WifiRecon.appDao.fetchData()
            _data.value = response.value
        }
    }

    fun fetchList():LiveData<List<Data>>{
        return WifiRecon.appDao.fetchData()
    }


}