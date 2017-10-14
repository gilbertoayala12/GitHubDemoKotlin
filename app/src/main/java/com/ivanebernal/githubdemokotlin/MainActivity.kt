package com.ivanebernal.githubdemokotlin

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val searchField: EditText by lazy { findViewById<EditText>(R.id.repo_search) }
    val searchButton: Button by lazy { findViewById<Button>(R.id.button_search) }
    val searchProgress: ProgressBar by lazy { findViewById<ProgressBar>(R.id.progress_search) }
    val usersRV: RecyclerView by lazy { findViewById<RecyclerView>(R.id.users_rv) }

    val gitHubAdapter = GitHubAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchProgress.visibility = View.GONE
        usersRV.layoutManager = LinearLayoutManager(this)
        usersRV.adapter = gitHubAdapter

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.github.com")
                .build()
        val gitHubClient = retrofit.create(GithubClient::class.java)

        searchButton.setOnClickListener {
            hideKeyboard()
            searchProgress.visibility = View.VISIBLE
            gitHubClient.reposForUser(searchField.text.toString()).enqueue(
                    object : Callback<GitHubSearchResult> {
                        override fun onFailure(call: Call<GitHubSearchResult>?, t: Throwable?) {
                            t?.printStackTrace()
                            Toast.makeText(this@MainActivity, t?.message, Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(call: Call<GitHubSearchResult>?, response: Response<GitHubSearchResult>?) {
                            searchProgress.visibility = View.GONE
                            gitHubAdapter.updateUsers(response?.body()?.items?: listOf())
                        }

                    }
            )
            //WITH RXJAVA
//                    .subscribeOn(Schedulers.newThread())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(
//                            {
//                                searchProgress.visibility = View.GONE
//                                gitHubAdapter.updateUsers(it.items)
//                            },
//                            {
//                                searchProgress.visibility = View.GONE
//                                it.printStackTrace()
//                                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
//                            }
//                    )
        }

    }

    private fun hideKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }
}
