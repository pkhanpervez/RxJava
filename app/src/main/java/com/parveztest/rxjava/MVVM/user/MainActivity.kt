package com.parveztest.rxjava.MVVM.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parveztest.rxjava.MVVM.adapter.RecyclerAdapter
import com.parveztest.rxjava.MVVM.model.User
import com.parveztest.rxjava.MVVM.retrofit.IAppAPIs
import com.parveztest.rxjava.MVVM.retrofit.RetrofitClient
import com.parveztest.rxjava.R
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity(), UserView {

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter
    lateinit var progressBar: ProgressBar
    lateinit var userViewModel: UserViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializer()

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        userViewModel.fetchUsers(this)
        observeData()
    }

    private fun initializer() {
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerAdapter = RecyclerAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter
    }

    private fun observeData() {
        userViewModel.getUsers().observe(this, Observer { users ->
            recyclerAdapter.setUserListItems(users)
        })
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun setError(message: String) {

    }
}