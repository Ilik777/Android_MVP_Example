package com.example.mvp_example.mvp.presenter

//аналог Model
interface Repository {

    fun saveAdvert(
        advertData: AdvertData
    )

    fun loadAdvert(callback: LoadAdvertCallback)

    fun clearData()
}