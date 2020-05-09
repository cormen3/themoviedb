package com.example.common

interface RecyclerItem {
    val id: String?
    override fun equals(other: Any?): Boolean
}