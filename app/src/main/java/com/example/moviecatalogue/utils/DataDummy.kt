package com.example.moviecatalogue.utils

import com.example.moviecatalogue.data.MovieEntity

object DataDummy {
    fun dataListMovie(): List<MovieEntity> {
        val dummyListMovie = ArrayList<MovieEntity>()
        dummyListMovie.add(
            MovieEntity(
                567189,
                "Tom Clancy's Without Remorse",
                "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
                false,
                "https://image.tmdb.org/t/p/w500/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                "7.3"
            )
        )
        return dummyListMovie
    }
    fun dataListTVShow(): List<MovieEntity> {
        val dummyListTVShow = ArrayList<MovieEntity>()
        dummyListTVShow.add(
            MovieEntity(
                88396,
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                true,
                "https://image.tmdb.org/t/p/w500/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "7.9"
            )
        )
        return dummyListTVShow
    }
}