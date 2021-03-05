package com.example.mvp_example.mvp.presenter

interface Contract {

    //EVENTS
    interface Presenter {

        fun onAttachView(view: View)

        fun onDetach()

        fun onSaveButtonClicked(
            title: String,
            subtitle: String,
            date: String
        )

        fun onDeleteButtonClicked()

        fun onLoadButtonClicked()

        fun onNextButtonClicked()

        fun onUnknownButtonClicked()
    }

    //ACTIONS
    interface View {

        fun showAdvertPhoto()

        fun showNoAdvertPhoto()

        fun updateAdvertTitle(title: String)

        fun updateAdvertSubtitle(subtitle: String)

        fun updateAdvertDateText(dateText: String)

        fun showMessage(message: String)

        fun showError(errorText: String)
    }
}