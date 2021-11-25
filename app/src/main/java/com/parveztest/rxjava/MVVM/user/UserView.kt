package com.parveztest.rxjava.MVVM.user

interface UserView {
    fun showProgress()
    fun hideProgress()
    fun setError(message: String)
}