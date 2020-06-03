package com.orange.test.ui.details

import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.orange.test.data.api.ServiceBuilder
import com.orange.test.data.model.MovieDetails
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsViewModel: ViewModel(), LifecycleObserver {
    var movieId = -1
    private val compositeDisposable = CompositeDisposable()
    val movie = ObservableField<MovieDetails>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(){
        compositeDisposable.add(
            ServiceBuilder.buildService().getMovieDetails(movieId, ServiceBuilder.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onSuccess(response)}, { t-> onErrorResponse(t)})
        )
    }

    fun onSuccess(movieResult: MovieDetails){
        println("resul: ${movieResult.title}")
        movie.set(movieResult)
    }

    fun onErrorResponse(throwable: Throwable){
        println("details request error: ${throwable.message}") //todo handle error
    }
}