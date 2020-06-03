package com.orange.test.ui.movies.popular

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.orange.test.data.api.ServiceBuilder
import com.orange.test.data.model.Movie
import com.orange.test.data.model.MoviesResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class PopularMoviesViewModel: ViewModel(), LifecycleObserver {
    private val compositeDisposable = CompositeDisposable()
    val moviesList = ObservableArrayList<Movie>()
    val isLoading = ObservableBoolean(true)

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart(){
        update()
    }

    fun onSuccess(result: MoviesResult){
        if (result.results != null){ //todo refactor
            moviesList.addAll(result.results)
        }
        isLoading.set(false)
    }

    fun onErrorResponse(t: Throwable){
        println(t.message) //todo handle error
        isLoading.set(false)
    }

    fun update(){ //todo fix swipe refresh layout update
        compositeDisposable.add(
            ServiceBuilder.buildService().getPopularMovies(ServiceBuilder.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onSuccess(response)}, {t-> onErrorResponse(t)})
        )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onStop(){
        compositeDisposable.dispose()
    }
}