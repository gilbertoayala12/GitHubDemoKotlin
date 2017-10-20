package com.ivanebernal.githubdemokotlin.dagger

import com.ivanebernal.githubdemokotlin.GitHubAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by rraya on 10/20/17.
 */

@Module
class AdapterModule {

    @Provides
    @Singleton
    fun provideAdapter(): GitHubAdapter = GitHubAdapter()
}