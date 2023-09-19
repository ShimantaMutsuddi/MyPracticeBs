package com.mutsuddi_s.mypracticebs.utils

/*sealed class Response<T>(val  data: T?=null, val errorMessage :String?=null) {
    class Loading<T> : Response<T>()
    class Success<T>(data: List<T> =null): Response<T>(data=data)
    class Error<T>( errorMessage :String) : Response<T>(errorMessage=errorMessage)
}*/

sealed class Resource<T>(val  data: T?=null, val errorMessage :String?=null) {
    class Loading<T> : Resource<T>()
    class Success<T>(  data: T?=null): Resource<T>(data=data)
    class Error<T>( errorMessage :String) : Resource<T>(errorMessage=errorMessage)
}


