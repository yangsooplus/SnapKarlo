package com.yangsooplus.snapkarlo.data

sealed class ApiState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Idle<T> : ApiState<T>()
    class Success<T>(data: T) : ApiState<T>(data)
    class Error<T>(data: T? = null, message: String) : ApiState<T>(data, message)
    class Loading<T> : ApiState<T>()
}
