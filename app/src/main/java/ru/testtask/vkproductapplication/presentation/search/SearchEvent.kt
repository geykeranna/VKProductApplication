package ru.testtask.vkproductapplication.presentation.search

sealed class SearchEvent {

    data class UpdateSearchQuery(val searchQuery: String): SearchEvent()

    data object Search: SearchEvent()
}