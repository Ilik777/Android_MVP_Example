package com.example.mvp_example.mvp.model

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.mvp_example.mvp.presenter.AdvertData
import com.example.mvp_example.mvp.presenter.Repository

class DefaultRepository(
    applicationContext: Context
) : Repository {

    private var sharedPreferences: SharedPreferences =
        applicationContext.applicationContext.getSharedPreferences(
            "mvp_example",
            AppCompatActivity.MODE_PRIVATE
        )

    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    override fun saveAdvert(advertData: AdvertData) {
        editor.putString(
            "title",
            advertData.title
        )
        editor.putString(
            "subtitle",
            advertData.subtitle
        )
        editor.putString(
            "date",
            advertData.dateString
        )
        editor.apply()
    }

    override fun loadAdvert(): AdvertData = AdvertData(
        title = sharedPreferences.getString("title", "defaultTitle").orEmpty(),
        subtitle = sharedPreferences.getString("subtitle", "defaultSubtitle").orEmpty(),
        dateString = sharedPreferences.getString("date", "defaultDate").orEmpty()
    )

    override fun clearData() {
        editor.clear()
        editor.apply()
    }
}