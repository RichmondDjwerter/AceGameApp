package com.richmondprojects.acegameapp.di

import android.content.Context
import com.richmondprojects.acegameapp.BaseApplication
import com.richmondprojects.acegameapp.data.remote.api.Api
import com.richmondprojects.acegameapp.data.remote.api.ApiBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApp(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Provides
    @Singleton
    fun providesRetrofit(apiBuilder: ApiBuilder): Api {
        return apiBuilder.builder(Api::class.java)
    }
}