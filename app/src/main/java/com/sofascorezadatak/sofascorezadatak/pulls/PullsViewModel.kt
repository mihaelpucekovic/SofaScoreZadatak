package com.sofascorezadatak.sofascorezadatak.pulls

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sofascorezadatak.sofascorezadatak.model.Pull
import com.sofascorezadatak.sofascorezadatak.network.Network
import com.sofascorezadatak.sofascorezadatak.network.NetworkAPI
import com.sofascorezadatak.sofascorezadatak.toImmutableLiveData
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PullsViewModel : ViewModel() {
    private val _liveDataRepositoryPulls = MutableLiveData<List<Pull>>()
    val liveDataRepositoryPulls = _liveDataRepositoryPulls.toImmutableLiveData()

    fun getRepositoryPulls(owner: String, repo: String) {
        viewModelScope.launch {
            val network = Network.retrofitInstance!!.create(NetworkAPI::class.java)
            val asyncRepositoryPulls = async { network.getRepositoryPulls(owner, repo) }

            _liveDataRepositoryPulls.value = asyncRepositoryPulls.await()
        }
    }
}