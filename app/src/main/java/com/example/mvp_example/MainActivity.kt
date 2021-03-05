package com.example.mvp_example

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mvp_example.mvp.view.ExampleActivity
import java.util.Date

class MainActivity : AppCompatActivity(),
    View.OnClickListener {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var advertPhotoImageView: ImageView
    private lateinit var advertExpireDateTextView: TextView
    private lateinit var advertTitleTextView: TextView
    private lateinit var advertSubtitleTextView: TextView
    private lateinit var saveAdvertButton: Button
    private lateinit var deleteAdvertButton: Button
    private lateinit var loadAdvertButton: Button
    private lateinit var openNextScreenButton: Button

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        advertPhotoImageView = findViewById(R.id.main_activity_advert_imageview_photo)
        advertExpireDateTextView = findViewById(R.id.main_activity_advert_textview_expire_date)
        advertTitleTextView = findViewById(R.id.main_activity_advert_textview_title)
        advertSubtitleTextView = findViewById(R.id.main_activity_advert_textview_subtitle)
        saveAdvertButton = findViewById(R.id.main_activity_advert_button_save)
        deleteAdvertButton = findViewById(R.id.main_activity_advert_button_remove)
        loadAdvertButton = findViewById(R.id.main_activity_advert_button_load)
        openNextScreenButton = findViewById(R.id.main_activity_advert_button_next)

        saveAdvertButton.setOnClickListener(this)
        deleteAdvertButton.setOnClickListener(this)
        loadAdvertButton.setOnClickListener(this)
        openNextScreenButton.setOnClickListener(this)
        advertPhotoImageView.setOnClickListener(this)

        sharedPreferences = applicationContext.getSharedPreferences("non_mvp_example", MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onClick(
        veiw: View?
    ): Unit = when (veiw?.id) {
        saveAdvertButton.id -> {
            editor.putString(
                "title",
                advertTitleTextView.text as String?
            )
            editor.putString(
                "subtitle",
                advertSubtitleTextView.text as String?
            )
            editor.putString(
                "date",
                DateFormat.format("dd MMM HH:mm:ss", Date().time).toString()
            )
            editor.apply()
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
        }
        deleteAdvertButton.id -> {
            advertExpireDateTextView.text = ""
            advertTitleTextView.text = ""
            advertSubtitleTextView.text = ""
            advertPhotoImageView.setImageDrawable(
                resources.getDrawable(
                    R.drawable.ic_error_outline,
                    null
                )
            )
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
        }
        loadAdvertButton.id -> {
            advertExpireDateTextView.text = sharedPreferences.getString("date", "defaultTitle")
            advertTitleTextView.text = sharedPreferences.getString("title", "defaultSubtitle")
            advertSubtitleTextView.text = sharedPreferences.getString("subtitle", "defaultDate")
            advertPhotoImageView.setImageDrawable(
                resources.getDrawable(
                    R.drawable.ic_ok_filled_small_full,
                    null
                )
            )
            Toast.makeText(this, "Loaded", Toast.LENGTH_SHORT).show()
        }
        openNextScreenButton.id -> startActivity(Intent(this, ExampleActivity::class.java))
        else -> Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
    }
}