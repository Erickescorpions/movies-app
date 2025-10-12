package com.erickvazquezs.practicamodulo7.application

import android.app.Application
import com.erickvazquezs.practicamodulo7.data.MovieRepository
import com.erickvazquezs.practicamodulo7.data.remote.RetrofitHelper
import retrofit2.Retrofit

class PracticaModulo7Application: Application() {
    private val retrofit by lazy {
        RetrofitHelper().getRetrofit()
    }

    // esta variable se crea hasta la primera vez que se accede
    val repository by lazy {
        MovieRepository(retrofit)
    }
}