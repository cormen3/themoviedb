package com.example.data

import com.example.data.entity.EntityModule
import com.example.data.executor.ExecutionModule
import com.example.data.network.NetworkModule
import com.example.data.repository.RepositoryModule
import dagger.Module

@Module(
    includes = [
        EntityModule::class,
        NetworkModule::class,
        ExecutionModule::class,
        RepositoryModule::class
    ]
)
abstract class DataModule