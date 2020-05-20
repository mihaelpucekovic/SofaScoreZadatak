package com.sofascorezadatak.sofascorezadatak.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sofascorezadatak.sofascorezadatak.model.Repository
import com.sofascorezadatak.sofascorezadatak.network.Network
import com.sofascorezadatak.sofascorezadatak.network.NetworkAPI
import kotlinx.coroutines.launch
import com.sofascorezadatak.sofascorezadatak.network.Result
import com.sofascorezadatak.sofascorezadatak.safeResponse
import com.sofascorezadatak.sofascorezadatak.toImmutableLiveData

class MainActivityViewModel : ViewModel() {

    private val _liveDataRepositories = MutableLiveData<List<Repository>>()
    val liveDataRepositories = _liveDataRepositories.toImmutableLiveData()

    fun getRepositories(org: String) {
        viewModelScope.launch {
            val network = Network.retrofitInstance!!.create(NetworkAPI::class.java)

            val result = safeResponse {
                network.getRepositories(org)
            }

            when (result) {
                is Result.Success -> {
                    _liveDataRepositories.value = result.data
                }
                is Result.Error -> {
                    result.error
                }
            }
        }
    }
}