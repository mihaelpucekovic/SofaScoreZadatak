package com.sofascorezadatak.sofascorezadatak

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import retrofit2.HttpException
import java.net.SocketTimeoutException
import com.sofascorezadatak.sofascorezadatak.network.Result

suspend fun <T> CoroutineScope.safeResponse(func: suspend CoroutineScope.() -> T): Result<T> {
    return try {
        Result.Success(func.invoke(this))
    }
    catch (e: HttpException) {
        Result.Error(e)
    }
    catch (e: SocketTimeoutException) {
        Result.Error(e)
    }
}

fun <T> MutableLiveData<T>.toImmutableLiveData(): LiveData<T> = this