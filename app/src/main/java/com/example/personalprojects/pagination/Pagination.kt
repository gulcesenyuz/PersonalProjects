package com.example.personalprojects.pagination

interface Pagination<Key, Item> {
    suspend fun loadNextPage()
    fun reset()
}