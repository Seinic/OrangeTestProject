package com.orange.test.ui.movies.popular

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
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

class PopularMoviesViewModel : ViewModel(), LifecycleObserver {
    private val compositeDisposable = CompositeDisposable()
    val moviesList = ObservableArrayList<Movie>()
    val isLoading = ObservableBoolean(false)
    var page = 1
    var maxPages = 999

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        update()
    }

    fun onUpdateSuccess(result: MoviesResult) {
        moviesList.clear()
        moviesList.addAll(result.results)
        page = result.page
        maxPages = result.total_pages
        isLoading.set(false)
    }

    fun onPaginationSuccess(result: MoviesResult) {
        moviesList.addAll(result.results)
        page = result.page
        maxPages = result.total_pages
        isLoading.set(false)
    }

    fun onErrorResponse(t: Throwable) {
        println(t.message) //todo handle error
        isLoading.set(false)
    }

    fun update() {
        isLoading.set(true)
        compositeDisposable.add(
            ServiceBuilder.buildService().getPopularMovies(ServiceBuilder.API_KEY, 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response -> onUpdateSuccess(response) }, { t -> onErrorResponse(t) })
        )
    }

    fun loadNextPage() {
        isLoading.set(true)
        compositeDisposable.add(
            ServiceBuilder.buildService().getPopularMovies(ServiceBuilder.API_KEY, page + 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response -> onPaginationSuccess(response) },
                    { t -> onErrorResponse(t) })
        )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onStop() {
        compositeDisposable.clear()
    }
}