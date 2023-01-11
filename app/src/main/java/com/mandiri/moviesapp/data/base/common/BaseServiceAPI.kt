package com.mandiri.moviesapp.data.base.common

import com.google.gson.Gson
import com.mandiri.moviesapp.MoviesApplication
import com.mandiri.moviesapp.R
import com.mandiri.moviesapp.data.base.response.ApiResponse
import com.mandiri.moviesapp.data.base.response.BaseErrorResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

open class BaseServiceAPI {
    private val defaultErrorMessage =
        MoviesApplication.instance.getString(R.string.message_error_default)

    protected suspend fun <T> callApi(callApiFunction: suspend () -> Response<T>): Flow<ApiResponse<T>> {
        return try {
            callApiFunction.invoke().unfold()
        } catch (exception: Exception) {
            flow<ApiResponse<T>> {
                emit(ApiResponse.Error(exception))
            }.flowOn(Dispatchers.IO)
        }
    }

    private fun <T> Response<T>.unfold(): Flow<ApiResponse<T>> {
        return flow<ApiResponse<T>> {
            try {
                val response = this@unfold
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ApiResponse.Success(it))
                    }
                        ?: emit(ApiResponse.Error(Throwable(defaultErrorMessage)))
                } else {
                    val message = try {
                        Gson().fromJson(
                            response.errorBody()?.string(),
                            BaseErrorResponse::class.java
                        ).message.toString()
                    } catch (e: Throwable) {
                        defaultErrorMessage
                    }
                    emit(ApiResponse.Error(Throwable(message)))
                }
            } catch (exception: Exception) {
                emit(ApiResponse.Error(exception))
            }
        }.flowOn(Dispatchers.IO)
    }
}