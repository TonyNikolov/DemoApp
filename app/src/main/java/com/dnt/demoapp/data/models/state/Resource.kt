package com.dnt.demoapp.data.models.state

import com.dnt.demoapp.data.models.responses.ApiResponse

class Resource<T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val code: Int?
) {

    companion object {
        @JvmStatic
        fun <T> success(data: T?, code: Int?): Resource<T> =
            Resource(Status.SUCCESS, data, null, code)

        @JvmStatic
        fun <T> error(message: String?, data: T?, code: Int = -1): Resource<T> =
            Resource(Status.ERROR, data, message, code)

        fun <T> loading(data: T?): Resource<T> = Resource(Status.LOADING, data, null, -1)

        @JvmStatic
        fun <T> loading(): Resource<T> = loading(null)
    }

    constructor(response: ApiResponse<T>) : this(
        if (response.success) Status.SUCCESS else Status.ERROR,
        response.body,
        response.errorMessage,
        response.code
    )
}