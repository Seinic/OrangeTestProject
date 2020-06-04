package com.orange.test.util.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orange.test.R
import com.orange.test.data.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesRecyclerViewAdapter : RecyclerView.Adapter<MoviesRecyclerViewAdapter.MoviesHolder>(),
    BindableAdapter<List<Movie>> {

    var moviesList = emptyList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    inner class MoviesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener { onItemClick?.invoke(moviesList[adapterPosition]) }
        }

        fun bind(movie: Movie) {
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
                .into(itemView.image)
            itemView.name.text = movie.title
            itemView.description.text = movie.overview
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        return MoviesHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    override fun setData(data: List<Movie>) {
        moviesList = data
        notifyDataSetChanged()
    }

    override fun changedPositions(positions: Set<Int>) {
        positions.forEach(this::notifyItemChanged)
    }
}