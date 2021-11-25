package com.parveztest.rxjava.MVVM.user

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.parveztest.rxjava.MVVM.model.User
import com.parveztest.rxjava.MVVM.retrofit.IAppAPIs
import com.parveztest.rxjava.MVVM.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val usersList = MutableLiveData<List<User>>()
    private val userService: IAppAPIs = RetrofitClient.retrofitInstance.create(
        IAppAPIs::class.java)

    private var compositeDisposable = CompositeDisposable()

    fun getUsers(): LiveData<List<User>> {
        return usersList
    }

    fun fetchUsers(userview: UserView) {
        compositeDisposable.add(
            userService.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe({
                    userview.showProgress()
                })
                .doOnTerminate({
                    userview.hideProgress()
                })
                .subscribe(
                    { user ->
                        usersList.value = user
                    },
                    { throwable ->
                        Log.e("MainActivity", throwable.message ?: "onError")
                    }
                )
        )
    }
}