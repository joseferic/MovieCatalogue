package com.example.moviecatalogue.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.databinding.ItemsTvshowBinding
import com.example.moviecatalogue.ui.detail.DetailActivity
import javax.security.auth.callback.Callback

class TVShowAdapter : RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder>() {
    private val listTVshow = ArrayList<MovieEntity>()

    fun setTVShow(tvshow: List<MovieEntity>?) {
        if (tvshow == null) return
        this.listTVshow.clear()
        this.listTVshow.addAll(tvshow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        val itemsTVShowBinding = ItemsTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowViewHolder(itemsTVShowBinding)
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        val tvshow = listTVshow[position]
        holder.bind(tvshow)
    }

    override fun getItemCount(): Int = listTVshow.size

    class TVShowViewHolder(private val binding: ItemsTvshowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: MovieEntity) {
            with(binding) {
                tvItemTitle.text = tvshow.title
                tvItemRating.text = tvshow.rating
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIES, tvshow)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                        .load(tvshow.imagePath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                        .error(R.drawable.ic_error)
                        .into(imgPoster)
            }
        }
    }

}