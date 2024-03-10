package ru.testtask.vkproductapplication.presentation.detail

sealed class DetailEvent {
    data class GetOneProduct(val id: Int) : DetailEvent()
}