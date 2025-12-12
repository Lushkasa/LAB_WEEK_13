package com.example.lab_week_13

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lab_week_13.api.MovieService
import com.example.lab_week_13.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(private val movieService: MovieService) {
    private val apiKey = "6e18febbf61a38c3ad00ac18076ff440"

    // LiveData that contains a list of movies
    private val movieLiveData = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = movieLiveData

    // LiveData that contains an error message
    private val errorLiveData = MutableLiveData<String>()
    val error: LiveData<String>
        get() = errorLiveData

    // fetch movies from the API
    // fetch movies from the API
// this function returns a Flow of Movie objects
// a Flow is a type of coroutine that can emit multiple values
// for more info, see: https://kotlinlang.org/docs/flow.html#flows
    fun fetchMovies(): Flow<List<Movie>> {
        return flow {
    // emit the list of popular movies from the API
            emit(movieService.getPopularMovies(apiKey).results)
    // use Dispatchers.IO to run this coroutine on a shared pool of threads
        }.flowOn(Dispatchers.IO)

    }
}