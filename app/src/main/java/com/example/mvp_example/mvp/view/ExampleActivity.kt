package com.example.mvp_example.mvp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mvp_example.R
import com.example.mvp_example.mvp.model.DefaultRepository
import com.example.mvp_example.mvp.presenter.Contract
import com.example.mvp_example.mvp.presenter.Presenter
import com.google.android.material.snackbar.Snackbar
import java.util.Date

class ExampleActivity :
    AppCompatActivity(),
    Contract.View,
    View.OnClickListener {

    private lateinit var presenter: Contract.Presenter

    private lateinit var advertPhotoImageView: ImageView
    private lateinit var advertExpireDateTextView: TextView
    private lateinit var advertTitleTextView: TextView
    private lateinit var advertSubtitleTextView: TextView
    private lateinit var saveAdvertButton: Button
    private lateinit var deleteAdvertButton: Button
    private lateinit var loadAdvertButton: Button
    private lateinit var openNextScreenButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = Presenter(DefaultRepository())
        presenter.onAttachView(this)

        initViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach() //Memory Leak
    }

    override fun onClick(
        veiw: View?
    ): Unit = when (veiw?.id) {
        saveAdvertButton.id -> presenter.onSaveButtonClicked(
            title = advertTitleTextView.text.toString(),
            subtitle = advertSubtitleTextView.text.toString(),
            date = DateFormat.format("dd MMM HH:mm:ss", Date().time) as String
        )
        deleteAdvertButton.id -> presenter.onDeleteButtonClicked()
        loadAdvertButton.id -> presenter.onLoadButtonClicked()
        openNextScreenButton.id -> presenter.onNextButtonClicked()
        else -> presenter.onUnknownButtonClicked()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun showAdvertPhoto() {
        advertPhotoImageView.setImageDrawable(
            resources.getDrawable(
                R.drawable.ic_ok_filled_small_full,
                null
            )
        )
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun showNoAdvertPhoto() {
        advertPhotoImageView.setImageDrawable(
            resources.getDrawable(
                R.drawable.ic_error_outline,
                null
            )
        )
    }

    override fun updateAdvertTitle(title: String) {
        advertTitleTextView.text = title
    }

    override fun updateAdvertSubtitle(subtitle: String) {
        advertSubtitleTextView.text = subtitle
    }

    override fun updateAdvertDateText(dateText: String) {
        advertExpireDateTextView.text = dateText
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showError(errorText: String) {
        val snackBar: Snackbar = Snackbar.make(
            findViewById(android.R.id.content),
            errorText,
            Snackbar.LENGTH_LONG
        )
        snackBar.show()
    }

    private fun initViews() {
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
        advertPhotoImageView.setOnClickListener(this)
        openNextScreenButton.setOnClickListener(this)
    }
}