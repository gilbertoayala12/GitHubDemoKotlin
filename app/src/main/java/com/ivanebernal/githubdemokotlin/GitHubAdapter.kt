package com.ivanebernal.githubdemokotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ivanebernal.githubdemokotlin.models.GitHubUser

/**
 * Created by welcome1 on 10/13/17.
 */
class GitHubAdapter: RecyclerView.Adapter<GitHubAdapter.UserHolder>()  {

    val users: MutableList<GitHubUser> = mutableListOf()

    override fun onBindViewHolder(holder: UserHolder?, position: Int) {
        holder?.bindData(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UserHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater.inflate(R.layout.holder_user, parent, false)
        return UserHolder(view)
    }

    fun updateUsers(users: List<GitHubUser>){
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }


    inner class UserHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val nameTV: TextView by lazy { itemView.findViewById<TextView>(R.id.name) }
        val scoreTV: TextView by lazy { itemView.findViewById<TextView>(R.id.score) }

        fun bindData(user: GitHubUser){
            nameTV.text = user.login
            scoreTV.text = user.score.toString()
        }

    }
}