package com.example.common

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

@Suppress("DataClassPrivateConstructor")
data class NetworkState (val status: Status, val msg: String? = null, override val id: String? = "") : RecyclerItem, AdapterClick {
    companion object {
        val LOADED =
            NetworkState(Status.SUCCESS)
        val LOADING =
            NetworkState(Status.RUNNING)

        fun error(msg: String?) = NetworkState(
            Status.FAILED,
            msg
        )
    }
}