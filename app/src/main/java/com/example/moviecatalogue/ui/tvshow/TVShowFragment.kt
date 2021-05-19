package com.example.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.databinding.FragmentTVShowBinding
import com.example.moviecatalogue.ui.movies.MoviesViewModel
import com.example.moviecatalogue.viewmodel.ViewModelFactory


class TVShowFragment : Fragment() {

    private lateinit var fragmentTVShowBinding: FragmentTVShowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTVShowBinding = FragmentTVShowBinding.inflate(layoutInflater, container, false)
        return fragmentTVShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TVShowViewModel::class.java]

            val tvshowadapter = TVShowAdapter()

            viewModel.getTVShow().observe(viewLifecycleOwner, { tvshow ->
                tvshowadapter.setTVShow(tvshow)
                tvshowadapter.notifyDataSetChanged()
                Log.d("Data TVShowFragment= ", tvshow.toString())
            })

            with(fragmentTVShowBinding.rvTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvshowadapter
            }
        }
    }
}