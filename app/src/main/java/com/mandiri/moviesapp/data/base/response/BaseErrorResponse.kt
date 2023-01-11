package com.mandiri.moviesapp.data.base.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
open class BaseErrorResponse(
    @SerializedName("status_message")
    val message: String? = null
)