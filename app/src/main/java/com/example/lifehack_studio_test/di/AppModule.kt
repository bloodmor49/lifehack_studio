package com.example.lifehack_studio_test.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.lifehack_studio_test.R
import com.example.lifehack_studio_test.data.remote.ApiFactory
import com.example.lifehack_studio_test.data.repository.AppRepositoryImpl
import com.example.lifehack_studio_test.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    @Singleton
    fun bindAppRepository(appRepositoryImpl: AppRepositoryImpl): AppRepository

    companion object {
        @Singleton
        @Provides
        fun provideApiFactory() = ApiFactory

        @Singleton
        @Provides
        fun provideGlideInstance(
            @ApplicationContext context: Context,
        ) = Glide.with(context).setDefaultRequestOptions(
            RequestOptions()
//                .placeholder(com.google.android.material.R.drawable.material_ic_clear_black_24dp)
                .error(com.google.android.material.R.drawable.mtrl_ic_error)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
        )
    }
}