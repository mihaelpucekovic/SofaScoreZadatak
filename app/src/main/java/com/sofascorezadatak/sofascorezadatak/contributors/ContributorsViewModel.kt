package com.sofascorezadatak.sofascorezadatak.contributors

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sofascorezadatak.sofascorezadatak.model.Contributor
import com.sofascorezadatak.sofascorezadatak.network.Network
import com.sofascorezadatak.sofascorezadatak.network.NetworkAPI
import com.sofascorezadatak.sofascorezadatak.toImmutableLiveData
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ContributorsViewModel : ViewModel() {
    private val _liveDataRepositoryContributors = MutableLiveData<List<Contributor>>()
    val liveDataRepositoryContributors = _liveDataRepositoryContributors.toImmutableLiveData()

    fun getRepositoryContributors(owner: String, repo: String) {
        viewModelScope.launch {
            val network = Network.retrofitInstance!!.create(NetworkAPI::class.java)
            val asyncRepositoryContributors = async { network.getRepositoryContributors(owner, repo) }

            _liveDataRepositoryContributors.value = asyncRepositoryContributors.await()
        }
    }
}