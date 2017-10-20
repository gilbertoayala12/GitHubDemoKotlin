package com.ivanebernal.githubdemokotlin

import android.app.Activity
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
import com.ivanebernal.githubdemokotlin.dagger.AdapterModule
import com.ivanebernal.githubdemokotlin.dagger.DaggerAppComponent
import com.ivanebernal.githubdemokotlin.dagger.RetrofitModule
import com.ivanebernal.githubdemokotlin.models.GitHubSearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    val searchField: EditText by lazy { findViewById<EditText>(R.id.repo_search) }
    val searchButton: Button by lazy { findViewById<Button>(R.id.button_search) }
    val searchProgress: ProgressBar by lazy { findViewById<ProgressBar>(R.id.progress_search) }
    val usersRV: RecyclerView by lazy { findViewById<RecyclerView>(R.id.users_rv) }

    @Inject
    protected lateinit var gitHubClient: GithubClient

    @Inject
    protected lateinit var gitHubAdapter: GitHubAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependencies()
        searchProgress.visibility = View.GONE
        usersRV.layoutManager = LinearLayoutManager(this)
        usersRV.adapter = gitHubAdapter

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
                            gitHubAdapter.updateUsers(response?.body()?.items ?: listOf())
                        }

                    }
            )
        }

    }

    private fun injectDependencies() {
        DaggerAppComponent.builder().retrofitModule(RetrofitModule()).build().inject(this)
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }
}
