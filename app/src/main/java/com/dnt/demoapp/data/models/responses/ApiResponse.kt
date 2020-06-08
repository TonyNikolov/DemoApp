package com.dnt.demoapp.data.models.responses

import com.dnt.demoapp.data.exceptions.NoConnectivityException
import okhttp3.Headers
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call

const val UNKNOWN_HOST_EXCEPTION = 1001101

class ApiResponse<T>(call: Call<T>) {

    var errorMessage: String? = null
    var body: T? = null
    var code: Int = -1
    var success = code in 200..300
    var headers: Headers? = null

    init {
        try {
            call.execute().apply {
                code = code()
                body = body()
                errorMessage = parseError(errorBody())
                success = code in 200..300
                headers = headers()
            }
        } catch (ex: Exception) {
            errorMessage = ex.message
            body = null
            code = if (ex is NoConnectivityException) {
                UNKNOWN_HOST_EXCEPTION
            } else {
                -1
            }
            success = false
            headers = null
        }
    }

    private fun parseError(errorBody: ResponseBody?): String {
        if (errorBody != null) {
            try {
                val errorObject = JSONObject(errorBody.string())
                if (errorObject.has("reason")) {
                    return errorObject.getString("reason")
                } else if (errorObject.has("message")) {
                    return errorObject.getString("message")
                }
            } catch (e: Exception) {
                return try {
                    errorBody.string()
                } catch (e: Exception) {
                    errorBody.toString()
                }
            }
        }

        return ""
    }
}