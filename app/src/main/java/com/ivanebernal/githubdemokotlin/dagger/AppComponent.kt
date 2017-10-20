package com.ivanebernal.githubdemokotlin.dagger

import com.ivanebernal.githubdemokotlin.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by rraya on 10/20/17.
 */

@Singleton
@Component(modules = arrayOf(
        RetrofitModule::class,
        AdapterModule::class))
interface AppComponent {

    fun inject(mainActivity: MainActivity)

}