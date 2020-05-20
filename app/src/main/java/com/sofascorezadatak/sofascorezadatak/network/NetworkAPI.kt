package com.sofascorezadatak.sofascorezadatak.network

import com.sofascorezadatak.sofascorezadatak.model.Contributor
import com.sofascorezadatak.sofascorezadatak.model.Issue
import com.sofascorezadatak.sofascorezadatak.model.Pull
import com.sofascorezadatak.sofascorezadatak.model.Repository
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkAPI {
    @GET("orgs/{org}/repos")
    suspend fun getRepositories(@Path("org") org: String): List<Repository>

    @GET("repos/{owner}/{repo}/contributors")
    suspend fun getRepositoryContributors(@Path("owner") owner: String, @Path("repo") repo: String): List<Contributor>

    @GET("repos/{owner}/{repo}/issues")
    suspend fun getRepositoryIssues(@Path("owner") owner: String, @Path("repo") repo: String): List<Issue>

    @GET("repos/{owner}/{repo}/pulls")
    suspend fun getRepositoryPulls(@Path("owner") owner: String, @Path("repo") repo: String): List<Pull>
}