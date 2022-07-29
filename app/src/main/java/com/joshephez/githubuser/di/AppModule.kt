package com.joshephez.githubuser.di

import com.joshephez.githubuser.BuildConfig
import com.joshephez.githubuser.config.Constant
import com.joshephez.githubuser.network.RetroServiceInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl()= Constant.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL:String): RetroServiceInstance {
        val logging = HttpLoggingInterceptor()
        val httpclient = OkHttpClient.Builder()
        httpclient.addInterceptor(logging)

        if (BuildConfig.DEBUG) {
            // development build
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            // production build
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }
      return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpclient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetroServiceInstance::class.java)
    }



//    @Singleton
//    @Provides
//    fun getRetroServiceInstance(retrofit: Retrofit):RetroServiceInstance{
//        return retrofit.create(RetroServiceInstance::class.java)
//    }
//    @Singleton
//    @Provides
//    fun getRetroInstance(): Retrofit {
//        val logging = HttpLoggingInterceptor()
//        val httpclient = OkHttpClient.Builder()
//        httpclient.addInterceptor(logging)
//
//        if (BuildConfig.DEBUG) {
//            // development build
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        } else {
//            // production build
//            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
//        }
//
//        return Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .client(httpclient.build())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }

}