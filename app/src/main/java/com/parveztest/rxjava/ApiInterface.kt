package com.parveztest.rxjava

import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {

    @GET("users")
    fun getUsers() : Observable<List<User>>
}