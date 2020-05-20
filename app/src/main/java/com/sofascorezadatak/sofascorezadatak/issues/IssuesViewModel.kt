package com.sofascorezadatak.sofascorezadatak.issues

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sofascorezadatak.sofascorezadatak.model.Issue
import com.sofascorezadatak.sofascorezadatak.network.Network
import com.sofascorezadatak.sofascorezadatak.network.NetworkAPI
import com.sofascorezadatak.sofascorezadatak.toImmutableLiveData
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class IssuesViewModel : ViewModel() {
    private val _liveDataRepositoryIssues = MutableLiveData<List<Issue>>()
    val liveDataRepositoryIssues = _liveDataRepositoryIssues.toImmutableLiveData()

    fun getRepositoryIssues(owner: String, repo: String) {
        viewModelScope.launch {
            val network = Network.retrofitInstance!!.create(NetworkAPI::class.java)
            val asyncRepositoryIssues = async { network.getRepositoryIssues(owner, repo) }

            _liveDataRepositoryIssues.value = asyncRepositoryIssues.await()
        }
    }
}