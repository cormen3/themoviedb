package com.example.data.datasource.movie.paging

import io.reactivex.Scheduler

interface Scheduler {
    fun mainThread():Scheduler
    fun io():Scheduler
}