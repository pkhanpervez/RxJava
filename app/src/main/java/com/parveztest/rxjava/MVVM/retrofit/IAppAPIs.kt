package com.parveztest.rxjava.MVVM.retrofit

import com.parveztest.rxjava.MVVM.model.User
import io.reactivex.Observable
import retrofit2.http.GET

interface IAppAPIs {

    @GET("users")
    fun getUsers() : Observable<List<User>>
}